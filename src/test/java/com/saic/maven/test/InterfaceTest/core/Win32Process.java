package com.saic.maven.test.InterfaceTest.core;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Kernel32Util;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Windows process. It uses JNA to access the Win32 API.
 * This code is based on
 * http://stackoverflow.com/questions/10124299/how-do-i-terminate-a-process-tree-from-java
 */
public class Win32Process {

    WinNT.HANDLE handle;
    int pid;

    /**
     * Create a Win32Process object for the given Process object. Reflection is
     * used to construct a Windows process handle.
     *
     * @param process A Java Process object
     *
     * @throws Exception
     */
    Win32Process(Process process) throws Exception {
        if (process.getClass().getName().equals("java.lang.Win32Process") || // NON-NLS
                process.getClass().getName().equals("java.lang.ProcessImpl")) { // NON-NLS
            try {
                Field f = process.getClass().getDeclaredField("handle"); // NON-NLS
                f.setAccessible(true);
                long handleVal = f.getLong(process);
                handle = new WinNT.HANDLE(Pointer.createConstant(handleVal));
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                throw new Exception(ex.getMessage()); // NON-NLS
            }
        }
        this.pid = Kernel32.INSTANCE.GetProcessId(handle);
    }

    /**
     * Create a Win32Process object for the given process id.
     *
     * @param pid Process Id
     *
     * @throws Exception
     */
    Win32Process(int pid) throws Exception {
        handle = Kernel32.INSTANCE.OpenProcess(
                0x0400
                | /*
                 * PROCESS_QUERY_INFORMATION
                 */ 0x0800
                | /*
                 * PROCESS_SUSPEND_RESUME
                 */ 0x0001
                | /*
                 * PROCESS_TERMINATE
                 */ 0x00100000 /*
                 * SYNCHRONIZE
                 */,
                false,
                pid);
        if (handle == null) {
            throw new Exception(Kernel32Util.formatMessageFromLastErrorCode(Kernel32.INSTANCE.GetLastError()));
        }
        this.pid = Kernel32.INSTANCE.GetProcessId(handle);
    }

    @Override
    protected void finalize() throws Throwable {
        Kernel32.INSTANCE.CloseHandle(handle);
        super.finalize();
    }

    /**
     * Kill the process. Note that this does not kill children.
     */
    public void terminate() {
        Kernel32.INSTANCE.TerminateProcess(handle, 0);
    }

    /**
     * Get children of current process object.
     *
     * @return list of child processes
     *
     * @throws IOException
     */
    public List<Win32Process> getChildren() throws Exception {
        ArrayList<Win32Process> result = new ArrayList<>();
        WinNT.HANDLE hSnap = Kernel32.INSTANCE.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new DWORD(0));
        Tlhelp32.PROCESSENTRY32.ByReference ent = new Tlhelp32.PROCESSENTRY32.ByReference();
        if (!Kernel32.INSTANCE.Process32First(hSnap, ent)) {
            return result;
        }
        do {
            if (ent.th32ParentProcessID.intValue() == pid) {
                result.add(new Win32Process(ent.th32ProcessID.intValue()));
            }
        } while (Kernel32.INSTANCE.Process32Next(hSnap, ent));
        Kernel32.INSTANCE.CloseHandle(hSnap);
        return result;
    }
}

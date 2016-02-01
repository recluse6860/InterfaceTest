package com.saic.maven.test.InterfaceTest.core;

import com.sun.jna.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenusAdaptor {
	
	static interface Kernel32 extends Library {

	    public static Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

	    public int GetProcessId(Long hProcess);
	}

	public static void main(String[] args) {
	    try {
	        Process p = null;
	        String cmd = "D:\\GitHub_Project\\InterfaceTest\\venus-http-adaptor\\bin\\launcher.bat";

	        if (Platform.isWindows())
	            p = Runtime.getRuntime().exec(cmd);
	        else if (Platform.isLinux())
	            p = Runtime.getRuntime().exec(cmd);

	        System.out.println("The PID: " + getPid(p));
            
            int PID = getPid(p);
            Win32Process process = new Win32Process(PID);
            kill(process);
            
            p.waitFor(); 

	    } catch (Exception ex) {
	        Logger.getLogger(VenusAdaptor.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	public static int getPid(Process p) {
	    Field f;

	    if (Platform.isWindows()) {
	        try {
	            f = p.getClass().getDeclaredField("handle");
	            f.setAccessible(true);
	            int pid = Kernel32.INSTANCE.GetProcessId((Long) f.get(p));
	            return pid;
	        } catch (Exception ex) {
	            Logger.getLogger(VenusAdaptor.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    } else if (Platform.isLinux()) {
	        try {
	            f = p.getClass().getDeclaredField("pid");
	            f.setAccessible(true);
	            int pid = (Integer) f.get(p);
	            return pid;
	        } catch (Exception ex) {
	            Logger.getLogger(VenusAdaptor.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    else{}
	    return 0;
	}
	
	public static void kill(Win32Process target) throws Exception
		{
		   List<Win32Process> children = target.getChildren ();
		   target.terminate();
		   for (Win32Process child : children) kill(child);
		}
	
	
//	public static void runVenusAdaptor
}



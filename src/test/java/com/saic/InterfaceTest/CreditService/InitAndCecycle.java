package com.saic.InterfaceTest.CreditService;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.saic.InterfaceTest.core.TestBase;
import com.saic.InterfaceTest.core.VenusAdaptor;
import com.saic.InterfaceTest.core.Win32Process;

public class InitAndCecycle extends TestBase{
	
	private Win32Process process;
	
	@BeforeSuite
    public void beforesuit() throws InterruptedException{  
		
		String BatFilePath = rootPath + "\\venus-http-adaptor\\bin\\launcher.bat";
		process = VenusAdaptor.runVenusAdaptor(BatFilePath);
		
		Thread.sleep(30000);
        System.out.println("in beforesuit");
        
    } 
	
	@AfterSuite
    public void afteresuit(){  
		
		VenusAdaptor.closeVenusAdaptor(process);
        System.out.println("in beforesuit");  
    } 	
}

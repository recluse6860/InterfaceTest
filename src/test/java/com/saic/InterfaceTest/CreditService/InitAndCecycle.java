package com.saic.InterfaceTest.CreditService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.saic.InterfaceTest.core.TestBase;
import com.saic.InterfaceTest.core.VenusAdaptor;
import com.saic.InterfaceTest.core.Win32Process;

public class InitAndCecycle extends TestBase{
	
	private Win32Process process;
	static Logger logger = LogManager.getLogger(VenusAdaptor.class.getName());
	
	@BeforeSuite
    public void BeforeSuite() throws InterruptedException{  
		
		String BatFilePath = rootPath + "\\venus-http-adaptor\\bin\\launcher.bat";
		logger.info("Run venus adaptor on" + BatFilePath);
		process = VenusAdaptor.runVenusAdaptor(BatFilePath);
		
		logger.info("wait 30s until VenusAdaptor is standby");
		Thread.sleep(30000);       
    } 
	
	@AfterSuite
    public void AfterSuite(){  
		
		logger.info("close VenusAdaptor");
		VenusAdaptor.closeVenusAdaptor(process);
		logger.info("Test finish");
    } 	
}

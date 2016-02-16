package com.saic.InterfaceTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	
	/* Get actual class name to be printed on */
	static Logger logger = LogManager.getLogger(App.class.getName());
	
    public static void main( String[] args ) throws Exception 
    {
    	Logger logger = LogManager.getLogger(App.class);
        System.out.println( "Hello World!" );
        logger.debug("Hello World!");
        logger.info("Info");
        logger.warn("warning!");
        logger.error("error");
    }
}

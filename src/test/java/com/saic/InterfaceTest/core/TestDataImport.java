package com.saic.InterfaceTest.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestDataImport {
	
	static Logger logger = LogManager.getLogger(BaseCase.class.getName());

	public static HashMap<String,String> TxtDataImport(String filePath) throws IOException
	{
	    HashMap<String, String> map = new HashMap<String, String>();

	    String line;
	    BufferedReader reader = new BufferedReader(new FileReader(filePath));
	    while ((line = reader.readLine()) != null)
	    {
	        String[] parts = line.split("=", 2);
	        if (parts.length >= 2)
	        {
	            String key = parts[0];
	            String value = parts[1];
	            map.put(key, value);
	        } else {
	            System.out.println("ignoring line: " + line);
	            logger.error("忽略不符合规则的数据行" + line);
	        }
	    }

	    for (String key : map.keySet())
	    {
	        System.out.println(key + "=" + map.get(key));
	    }
	    reader.close();
	    
		return map;
	}	
	
	public static void main(String[] args) throws IOException {
		TxtDataImport("D:\\GitHub_Project\\InterfaceTest\\src\\test\\resources\\CreditTradeService.txt");
	}
		
		
}

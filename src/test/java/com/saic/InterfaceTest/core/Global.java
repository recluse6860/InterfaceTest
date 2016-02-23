package com.saic.InterfaceTest.core;

import java.util.Map;

import com.saic.InterfaceTest.core.ParseXml;

public class Global {
	
	public static Map<String, String> global;
	
	static{
		ParseXml px = new ParseXml("src/test/resources/test-data/global.xml");
		global = px.getChildrenInfoByElement(px.getElementObject("/*"));
	}
	
}

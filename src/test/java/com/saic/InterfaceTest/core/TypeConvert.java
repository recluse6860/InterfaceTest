package com.saic.InterfaceTest.core;

import org.json.JSONException;
import org.json.JSONObject;

public class TypeConvert {
	
	public static JSONObject StringToJsonObj(String str) throws JSONException{
		JSONObject json = new JSONObject(str);
		return json;
	}
}

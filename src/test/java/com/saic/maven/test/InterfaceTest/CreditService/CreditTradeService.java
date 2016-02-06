package com.saic.maven.test.InterfaceTest.CreditService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.saic.maven.test.InterfaceTest.core.BaseCase;
import com.saic.maven.test.InterfaceTest.core.HttpClientUtil;

import org.apache.http.client.ClientProtocolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreditTradeService extends BaseCase {
	
	CreditTradeService cts = new CreditTradeService();
	String ClassName = cts.getClass().getName();
	final String ServiceUrl = BaseUrl + ClassName + "/";	
	
	@BeforeClass
	public void init()
	{
		
	}
	
	@Test
	public void creditAdd() throws ClientProtocolException, 
				URISyntaxException, IOException
	{
		String MethodName = "creditAdd";
		HttpClientUtil creditadd_post = new HttpClientUtil();
//		logger.
		String RequestUrl = ServiceUrl + MethodName;
		JSON.toJSONString(object);
//		JSONPath.
		Map<String, String> RequestBody = new HashMap<String, String>(); 
		creditadd_post.simplePostInvoke(RequestUrl, RequestBody , "UTF-8");
	}
}

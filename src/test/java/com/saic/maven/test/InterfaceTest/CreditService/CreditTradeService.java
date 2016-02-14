package com.saic.maven.test.InterfaceTest.CreditService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.saic.maven.test.InterfaceTest.core.BaseCase;
import com.saic.maven.test.InterfaceTest.core.HttpClientUtil;

public class CreditTradeService extends BaseCase{

		private final String ClassName = this.getClass().getSimpleName();
	    private final String ServiceUrl = BaseUrl + ClassName + "/";

		@BeforeClass
	    public void beforeClass() {	    	
	        System.out.println("this is before class");
	    }

	    @Test
	    public void TestNgLearn() throws ClientProtocolException, URISyntaxException, IOException {
	    	String MethodName = "creditAdd";
			HttpClientUtil creditadd_post = new HttpClientUtil();
			String RequestUrl = ServiceUrl + MethodName;
			String RequestBody = "{\"creditTradeDto\":{\"userId\":560141,\"tradeAmount\":1,\"acctType\":1,\"payType\":1,\"channelNo\":\"INT\",\"tradeContent\":\"StTest\",\"refId\":\"recluse005\",\"agencyNo\":\"CX\",\"chargeTime\":\"2016-02-05\",\"channelSource\":\"30305\"}}";
//			JsonArray jsonObject = JsonArray.fromObject(RequestBody);
//			Map RequestBodyMap = new HashMap();
//			RequestBodyMap = transStringToMap(RequestBody);
			String returnStr;
			returnStr =creditadd_post.simplePostInvoke(RequestUrl, RequestBody , "UTF-8");
			System.out.println("this is TestNG test case");
//			return returnStr;	    	
	        
	    }
	    
	  public static Map transStringToMap(String mapString){  
	      Map map = new HashMap();  
	      java.util.StringTokenizer items;  
	      for(StringTokenizer entrys = new StringTokenizer(mapString, "^");entrys.hasMoreTokens();   
	        map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))  
	          items = new StringTokenizer(entrys.nextToken(), "'");  
	      return map;  
    }  	    

	    @AfterClass
	    public void afterClass() {
	        System.out.println("this is after class");
	    }
}

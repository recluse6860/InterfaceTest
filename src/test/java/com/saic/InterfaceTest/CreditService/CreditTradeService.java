package com.saic.InterfaceTest.CreditService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.saic.InterfaceTest.core.Assertion;
import com.saic.InterfaceTest.core.BaseCase;
import com.saic.InterfaceTest.core.HttpClientUtil;
import com.saic.InterfaceTest.core.TestDataImport;
import com.saic.InterfaceTest.core.TypeConvert;
import com.saic.InterfaceTest.core.TestBase;

public class CreditTradeService extends TestBase{

		private final String ClassName = this.getClass().getSimpleName();
	    private final String ServiceUrl = BaseUrl + ClassName + "/";
	    private HashMap<String, String> TestDataMap = new HashMap<String, String>();
	    private final String TestDataPath = rootPath + "\\src\\test\\resources\\com\\saic\\InterfaceTest\\TestData\\CreditService\\" + ClassName + ".txt";
	    private final String VerifycreditAdd = "creditAdd";
	    
	    
		@BeforeClass
	    public void beforeClass() throws IOException {	    	
	        System.out.println("this is before class");
	        TestDataImport TestDataImport = new TestDataImport();
	        TestDataMap = TestDataImport.TxtDataImport(TestDataPath);
	        System.out.println("debug");
	    }

		@Test(dataProvider="providerMethod")
	    public void creditAdd_all_ok(Map<String, String> param) throws ClientProtocolException,
	    		URISyntaxException, IOException, JSONException {
	    	
	    	String MethodName = Thread.currentThread().getStackTrace()[1].getMethodName(); 
			HttpClientUtil creditadd_post = new HttpClientUtil();
			String RequestUrl = ServiceUrl + VerifycreditAdd;
//			String RequestBody = TestDataMap.get(MethodName);
			String RequestBody = param.get(MethodName);
			String returnStr;
			returnStr =creditadd_post.simplePostInvoke(RequestUrl, RequestBody , "UTF-8");
			JSONObject returnJsonObj = TypeConvert.StringToJsonObj(returnStr);
			Assertion.verifyEquals(returnJsonObj.get("errorCode"), 0, "Error Code 值错误");
			System.out.println("this is TestNG test case");	        
	    }
	      	    

	    @AfterClass
	    public void afterClass() {
	        System.out.println("this is after class");
	    }
}

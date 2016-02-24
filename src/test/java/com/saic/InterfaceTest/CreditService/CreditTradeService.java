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
	    private final String VerifycreditAdd = "creditAdd";
	    private HttpClientUtil creditadd_post = new HttpClientUtil();
	    
	    
		@BeforeClass
	    public void beforeClass() throws IOException {	    	
	        System.out.println("this is before class");
	    }

		@Test(dataProvider="providerMethod")
	    public void creditAdd_all_ok(Map<String, String> param) throws ClientProtocolException,
	    		URISyntaxException, IOException, JSONException {
	    	
	    	String MethodName = Thread.currentThread().getStackTrace()[1].getMethodName(); 
			String RequestUrl = ServiceUrl + VerifycreditAdd;
			String RequestBody = param.get(MethodName);
			String returnStr;
			returnStr =creditadd_post.simplePostInvoke(RequestUrl, RequestBody , "UTF-8");
			JSONObject returnJsonObj = TypeConvert.StringToJsonObj(returnStr);
			Assertion.verifyEquals(returnJsonObj.get("errorCode"), 0, "Error Code 值错误");
			System.out.println("this is TestNG test case");	        
	    }
	 
		@Test(dataProvider="providerMethod")
	    public void creditAdd_userid_null(Map<String, String> param) throws ClientProtocolException,
	    		URISyntaxException, IOException, JSONException {
	    	
	    	String MethodName = Thread.currentThread().getStackTrace()[1].getMethodName(); 
			String RequestUrl = ServiceUrl + VerifycreditAdd;
			String RequestBody = param.get(MethodName);
			String returnStr;
			returnStr =creditadd_post.simplePostInvoke(RequestUrl, RequestBody , "UTF-8");
			JSONObject returnJsonObj = TypeConvert.StringToJsonObj(returnStr);
			Assertion.verifyEquals(returnJsonObj.get("errorCode"), 20107101, "Error Code 值错误");
			Assertion.verifyEquals(returnJsonObj.get("errorMessage"), "参数校验异常：用户ID、投放机构、渠道 、来源、关联业务号、交易积分数 都不能为空", "Error Message 值错误");
			System.out.println("this is TestNG test case");	        
	    }		

	    @AfterClass
	    public void afterClass() {
	        System.out.println("this is after class");
	    }
}

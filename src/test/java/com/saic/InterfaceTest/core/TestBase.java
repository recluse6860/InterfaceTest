package com.saic.InterfaceTest.core;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.testng.annotations.DataProvider;

import com.saic.InterfaceTest.core.ParseXml;

public class TestBase {	
	
	private ParseXml px;
	
	private Map<String, String> commonMap;
	
	protected static final File FILEROOT = (new File(Class.class.getClass().getResource("/").
			getPath()).getParentFile().getParentFile());
	
	protected static final String BaseUrl = "http://127.0.0.1:8080/services/";
	protected static final String rootPath = FILEROOT.getPath(); 
	
	
	private void initialPx(){
		if(px==null){			
			px = new ParseXml("src/test/resources/test-data/"+this.getClass().getSimpleName()+".xml");
		}
	}
	
	private void getCommonMap(){
		if(commonMap==null){			
			Element element = px.getElementObject("/*/common");
			commonMap = px.getChildrenInfoByElement(element);
		}
	}
	
	private Map<String, String> getMergeMapData(Map<String, String> map1, Map<String, String> map2){
		Iterator<String> it = map2.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = map2.get(key);
			if(!map1.containsKey(key)){
				map1.put(key, value);
			}
		}
		return map1;
	}
	
	@DataProvider
    public Object[][] providerMethod(Method method){	
		this.initialPx();
		this.getCommonMap();
		String methodName = method.getName();
		List<Element> elements = px.getElementObjects("/*/"+methodName);
		Object[][] object = new Object[elements.size()][];
		for (int i =0; i<elements.size(); i++) {
			Map<String, String> mergeCommon = this.getMergeMapData(px.getChildrenInfoByElement(elements.get(i)), commonMap);
			Map<String, String> mergeGlobal = this.getMergeMapData(mergeCommon, Global.global);
			Object[] temp = new Object[]{mergeGlobal};
			object[i] = temp;
		}
		return object;
	}
	
}

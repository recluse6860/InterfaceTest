package com.saic.InterfaceTest.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseCase {

	// 项目文件夹
	protected static final File FILEROOT = (new File(Class.class.getClass().getResource("/").
			getPath()).getParentFile().getParentFile());
	
	protected static final String BaseUrl = "http://127.0.0.1:8080/services/";
	protected static final String rootPath = FILEROOT.getPath(); 

	static Logger logger = LogManager.getLogger(BaseCase.class.getName());
	
	public HashMap<String, String> EnvParam = new HashMap<String, String>();
	
	/**
	 * @Title: readConst
	 * @Description:读取每个case的常量文件
	 * @param void
	 * @return void
	 * @date 
	 * 
	 *       每个case都代表一个项目的用例集 每个项目都有一些统一的配置参数，例如url和数据库连接字符串
	 *       这些常量放在const.properties中
	 */

	private Properties readConst() {
		Properties properties = new Properties();
		try {
			// 加载各项目下的资源文件，个项目的常量资源文件都应该在case目录下
			properties.load(new FileInputStream(getClass().getResource("const.properties").getFile()));
		} catch (IOException e) {
			logger.error("未在路径" + getClass().getResource("") + "下找到资源文件" + "const.properties");
			e.printStackTrace();
		}
		return properties;
	}	
	
}

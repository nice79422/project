package com.test.common.servlet.mapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.test.common.servlet.context.ApplicationContext;
import com.test.common.servlet.mvc.Controller;

public class SimpleUrlHandlerMapping {
	private HashMap<String,String> map;
	private static SimpleUrlHandlerMapping instance;
	public static SimpleUrlHandlerMapping getInstance(ServletContext application) {
		// TODO Auto-generated method stub
		if(instance==null) instance=new SimpleUrlHandlerMapping(application);
		return instance;
	}
	private SimpleUrlHandlerMapping(ServletContext application){
		map=new HashMap<String,String>();
		String path=application.getInitParameter("urlmappingFile");
		String rPath=application.getRealPath(path);
		Properties properties=new Properties();
		try {
			properties.load(new FileReader(rPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<String> set=properties.stringPropertyNames();
		for(String key: set){
			String value=properties.getProperty(key);
			map.put(key,value);
		}
	}
	public Controller getController(ApplicationContext applicationContext, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI(); // /mvc/menu.html
		String contextPath=request.getContextPath(); // /mvc
		int sIndex=contextPath.length();  // /4
		String key=uri.substring(sIndex); // /menu.html
		System.out.println(key);
		String beanName=map.get(key);
		return (Controller)(applicationContext.getBean(beanName));
	}
}

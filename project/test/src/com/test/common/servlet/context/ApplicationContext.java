package com.test.common.servlet.context;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

public class ApplicationContext {
	private HashMap<String, Object> map;

	public ApplicationContext(ServletConfig config, ServletContext application) {
		map = new HashMap<String, Object>();
		String path = config.getInitParameter("configFile");
		System.out.println("configFile:" + path); // 콘솔창에 뿌리는거
		String rPath = application.getRealPath(path); // c:\~프로젝트+config파일 경로
		Properties properties = new Properties();
		System.out.println(rPath); // 콘솔창에 뿌리는거
		try {
			properties.load(new FileReader(rPath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set<String> set = properties.stringPropertyNames();
		for (String key : set) {
			String value = properties.getProperty(key);
			Object obj = null;
			try {
				obj = Class.forName(value).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put(key, obj);
		}
	}

	public Object getBean(String beanName) {
		// TODO Auto-generated method stub
		return map.get(beanName);
	}
}

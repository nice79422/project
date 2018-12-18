package com.test.common.servlet.view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.common.servlet.ModelAndView;

public class InternalResourceViewResolver {
	private String prefix,postfix;
	private static InternalResourceViewResolver instance;
	public InternalResourceViewResolver(ServletContext application) {
		// TODO Auto-generated constructor stub
		String path=application.getInitParameter("pathFile");
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
		
		prefix=properties.getProperty("prefix");
		postfix=properties.getProperty("postfix");
	}
	public static InternalResourceViewResolver getInstance(ServletContext application) {
		// TODO Auto-generated method stub
		if(instance==null) instance=new InternalResourceViewResolver(application);
		return instance;
	}
	public void resolveView(ModelAndView modelAndView,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String viewName=modelAndView.getViewName();
		if(viewName.lastIndexOf("redirect:")==-1){
			HashMap<String,Object> modelObject=modelAndView.getModelObject();
			if(modelObject!=null){
				for(String key:modelObject.keySet())
						request.setAttribute(key, modelObject.get(key));
				
			}
			RequestDispatcher rd=request.getRequestDispatcher(prefix+viewName+postfix);
			rd.forward(request, response);
		}else{
			int index=viewName.indexOf(":");
			String path=viewName.substring(index+1);
			response.sendRedirect(path);
		}
	}
}

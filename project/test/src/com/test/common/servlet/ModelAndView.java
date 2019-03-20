package com.test.common.servlet;

import java.util.HashMap;

public class ModelAndView {
	private String viewName;
	private HashMap<String,Object> modelObject;
	public ModelAndView(String viewName,HashMap<String,Object> modelObject){
		this.viewName=viewName;
		this.modelObject=modelObject;
	}
	public String getViewName(){
		return viewName;
	} 
	public HashMap<String,Object> getModelObject(){
		return modelObject;
	}
}

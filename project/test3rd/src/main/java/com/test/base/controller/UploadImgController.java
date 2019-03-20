package com.test.base.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;




public class UploadImgController extends MultiActionController {
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();


	public ModelAndView uploadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//String uploadPath = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/yoogle/images/empPhoto/";
		//String uploadPath = "C:/workspacepro4/yoogle/images/empPhoto/";
		  String uploadPath = "C:/Users/hello/Documents/workspace-sts-3.9.6.RELEASE/test3rd/src/main/webapp/images/";
		System.out.println("uploadPath:"+uploadPath);
		
		String empId = request.getParameter("empId");
		System.out.println("empId:"+empId);
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile((iterator.next()));
			if (!multipartFile.isEmpty()) {
				String fileName = multipartFile.getOriginalFilename();
				String fileExtension = fileName.substring(fileName.lastIndexOf("."));
				File file = new File(uploadPath + empId + fileExtension);
				try {
					multipartFile.transferTo(file);
					modelMap.put("url", "/test3rd/images/" + empId + fileExtension);
					modelMap.put("errorCode", 1);
					modelMap.put("errorMsg", "성공");
				} catch (IllegalStateException | IOException e) {
					modelMap.put("errorCode", -1);
					modelMap.put("errorMsg", "실패");
				}
			}
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
}
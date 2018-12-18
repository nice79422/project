package com.test.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.util.FileUploader;

import net.sf.json.JSONObject;

public class UploadImgController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(this.getClass());
	ModelAndView modelAndView;


	public ModelAndView uploadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("UploadImgController START");
			logger.debug("Method : handleRequest ");
		}
		response.setContentType("application/json; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			RequestContext rc = new ServletRequestContext(request);

			String empId = null;
			String imgUrl = null;

			List<FileItem> items = upload.parseRequest(rc);
			
			for (FileItem item : items) {
				if (item.isFormField()) {
					if ("empId".equals(item.getFieldName()))
						empId = item.getString();
					
				} else {
					String fileName = item.getName();
					if ((fileName != null) && (item.getSize() > 0)) {
						imgUrl = FileUploader.doFileUpload(item, empId);
					}
				}
			}
			
			json.put("url", imgUrl);
			json.put("errorCode", 1);
			json.put("errorMsg", "저장완료되었습니다.");
		} catch (Exception error) {
			logger.fatal(error.getMessage());
			json.put("errorCode", -1);
			json.put("errorMsg", "SAD");
			error.printStackTrace();
		}
		out.println(json);
		out.close();
		if (logger.isDebugEnabled()) {
			logger.debug("UploadImgController END");
			logger.debug("Method : handleRequest ");
		}
		return null;
	}
}
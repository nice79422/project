 package com.test.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class FileUploader {

    public static String doFileUpload(FileItem fileItem, String empId) throws IOException {
        InputStream in = fileItem.getInputStream();
        //fileItem.getName() -> E0001.jsg
        String realFileName = fileItem.getName().substring(fileItem.getName().lastIndexOf("//") + 1);
        String fileExt = realFileName.substring(realFileName.lastIndexOf("."));
        //fileExt -> .jpg

        //String saveFileName = UUID.randomUUID().toString();
        String saveFileName = empId + fileExt;
        //empId+.jsp       
               
      /*String uploadPath = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/hcompany/images/";*/
        String uploadPath = "C:/Users/hello/eclipse-workspace0/test/WebContent/images/";
        FileOutputStream fout = new FileOutputStream(uploadPath + saveFileName);

        
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = in.read(buffer, 0, 8192)) != -1)
            fout.write(buffer, 0, bytesRead);
 

        in.close();
        fout.close();

        return "/test/images/" + saveFileName;
    }
}



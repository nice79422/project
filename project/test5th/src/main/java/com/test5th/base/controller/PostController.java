package com.test5th.base.controller;



import com.test5th.base.to.AddressBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
public class PostController{
   
	
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("base/findPostList.do")
   public void findPostList (HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
	  String dong = inData.getVariable("dong").getString();
      String page = inData.getVariable("page").getString();
      List<AddressBean> postList = searchAddress(dong,page);
      
      datasetBeanMapper.beansToDataset(outData, postList, AddressBean.class); 
    
      
   }
   
   
   public List<AddressBean> searchAddress(String dong, String page) throws Exception {
      List<AddressBean> postList = new ArrayList<>();
         String reqUrl = "http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdSearchAllService/retrieveNewAdressAreaCdSearchAllService/"
               + "getNewAddressListAreaCdSearchAll?"
               + "serviceKey=7UDioisadrPwOhOcMpymdr70TFrfDevJ1YTcmNhuSaN3yD8dIZkSUM38AnRvXG08wXDvUVsaV7OjhcJvHqbzWA%3D%3D"
               + "&srchwrd="+URLEncoder.encode(dong, "utf-8")+"&countPerPage=10&currentPage="+page;
                  
         URL url = new URL(reqUrl);
         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-type", "application/json");
         BufferedReader rd;
         if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
         } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
         }
         StringBuilder sb = new StringBuilder();
         String line;
         while ((line = rd.readLine()) != null) {
            sb.append(line);
         }
         rd.close();
         conn.disconnect();

         // XML파싱용 도큐먼트빌드팩토리 생성
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();

         // 도큐먼트빌트팩토리에 응답 문자열을 넣어줌
         Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
         
         // 주소노드 리스트 받기
         NodeList nodelist = document.getElementsByTagName("newAddressListAreaCdSearchAll");
         for(int i = 0; i < nodelist.getLength() ; i++) {
            AddressBean AddressBean = new AddressBean();
            
            Element address = (Element)nodelist.item(i);
            NodeList zipNoNode = address.getElementsByTagName("zipNo");
            NodeList lnmAdresNode = address.getElementsByTagName("lnmAdres");
            NodeList rnAdresNode = address.getElementsByTagName("rnAdres");
            
            Element zipNoEl = (Element)zipNoNode.item(0);
            Element lnmAdresEl = (Element)lnmAdresNode.item(0);
            Element rnAdresEl = (Element)rnAdresNode.item(0);
            
            String zipNo = zipNoEl.getFirstChild().getNodeValue();
            String lnmAdres = lnmAdresEl.getFirstChild().getNodeValue();
            String rnAdres = rnAdresEl.getFirstChild().getNodeValue();
            
            
            AddressBean.setZipNO(zipNo);
            AddressBean.setLnmAdres(lnmAdres);
            AddressBean.setRnAdres(rnAdres);
            
            postList.add(AddressBean);
            
         }
         
         
       return postList; 
      }


}
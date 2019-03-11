package kr.co.seoulit.common.util;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.common.to.HolidayInfoTO;

@Controller
public class HolidayInfoController {
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("common/util/findHolidayInfo.do")
	public void findHolidayInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("findHolidayInfo");
		//request.getAttribute("inData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String year_spin = inData.getVariable("year_spin").getString();
		
		String month_combo = inData.getVariable("month_combo").getString();

		if(month_combo.length()==1){
			month_combo="0"+month_combo;
		}
		
		new ArrayList<>();
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo"); /*
																														 * URL
																														 */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=2qFD7XPHlZl4%2FFL4C9PJ5rsnByPnPfrKl3rpUKUwshFyE9V5Qe2R2LDmwNW%2BcS9MbOvj9XeifN7wtTxpVZt7Nw%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder.append("&" + URLEncoder.encode("solYear", "UTF-8") + "=" + URLEncoder.encode(year_spin,"UTF-8")); 
		urlBuilder.append("&" + URLEncoder.encode("solMonth", "UTF-8") + "=" + URLEncoder.encode(month_combo,"UTF-8")); 


		Document doc = null;
		List<HolidayInfoTO> holidayInfoList = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(urlBuilder.toString());
			NodeList descNodes = doc.getElementsByTagName("item");
			for (int i = 0; i < descNodes.getLength(); i++) {
				HolidayInfoTO holydayInfoTO = new HolidayInfoTO();
				for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행

	                if(node.getNodeName().equals("locdate")){ // 날짜

	                    System.out.println(node.getTextContent());
	                    holydayInfoTO.setLocdate(node.getTextContent());
	                }
	                if(node.getNodeName().equals("seq")){ // 순번

	                    System.out.println(node.getTextContent());
	                    holydayInfoTO.setSeq(node.getTextContent());
	                }
	                if(node.getNodeName().equals("dateKind")){ // 종류

	                    System.out.println(node.getTextContent());
	                    holydayInfoTO.setDateKind(node.getTextContent());
	                }
	                if(node.getNodeName().equals("isHoliday")){ // 공공기관 휴일여부
 
	                    System.out.println(node.getTextContent());
	                    holydayInfoTO.setIsHoliday(node.getTextContent());
	                }
	                if(node.getNodeName().equals("dateName")){ // 명칭

	                    System.out.println(node.getTextContent());
	                    holydayInfoTO.setDateName(node.getTextContent());
	                }
	                
				}
				holidayInfoList.add(holydayInfoTO);
			}
		} catch (Exception ex) {
			throw ex;
		}

		datasetBeanMapper.beansToDataset(outData, holidayInfoList, HolidayInfoTO.class);
	}

}


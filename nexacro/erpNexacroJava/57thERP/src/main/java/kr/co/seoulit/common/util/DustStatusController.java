package kr.co.seoulit.common.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.sys.to.DustStatusTO;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nexacro.xapi.data.PlatformData;

@Controller
public class DustStatusController {
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("base/findDustStatus.do")
	public void findDustStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("findDustStatus");
		request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		new ArrayList<>();
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"); /*
																														 * URL
																														 */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=nRV0AEg5RGOwvwU5%2Blqcye0CUldYPkU584voPxLgOby2avVlvTZvjniEjr8PQCQbtHJYjqJvjsEyLvmXUmUQ%2Bw%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		 urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" +
		 URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
		 urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" +
		 URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
		 urlBuilder.append("&" + URLEncoder.encode("sidoName", "UTF-8") + "=" + URLEncoder.encode("경남","UTF-8")); /* 시도 이름 (서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종) */
		 urlBuilder.append("&" + URLEncoder.encode("dataTerm","UTF-8") + "=" +
		 URLEncoder.encode("DAILY", "UTF-8")); /*요청 데이터기간 (시간 : HOUR, 하루 : DAILY)*/
		 urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" +
		 URLEncoder.encode("1.3", "UTF-8")); /*오퍼레이션 버전*/

		Document doc = null;
		List<DustStatusTO> dustStatusList = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(urlBuilder.toString());
			NodeList descNodes = doc.getElementsByTagName("item");
			for (int i = 0; i < descNodes.getLength(); i++) {
				DustStatusTO dustTO = new DustStatusTO();



				for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행

	                if(node.getNodeName().equals("stationName")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setStationName(node.getTextContent());
	                }
	                if(node.getNodeName().equals("dataTime")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setDataTime(node.getTextContent());

	                }
	                if(node.getNodeName().equals("pm10Grade")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setPm10Grade(node.getTextContent());
	                }
	                if(node.getNodeName().equals("so2Value")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setSo2Value(node.getTextContent());
	                }
	                if(node.getNodeName().equals("coValue")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setCoValue(node.getTextContent());
	                }
	                if(node.getNodeName().equals("pm25Grade")){

	                    System.out.println(node.getTextContent());
	                    dustTO.setPm25Grade(node.getTextContent());
	                }
				}
				  dustStatusList.add(dustTO);
			}
		} catch (Exception ex) {
			throw ex;
		}

		datasetBeanMapper.beansToDataset(outData, dustStatusList, DustStatusTO.class);
	}

}
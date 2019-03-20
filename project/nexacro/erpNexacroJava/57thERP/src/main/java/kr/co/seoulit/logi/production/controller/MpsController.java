package kr.co.seoulit.logi.production.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logi.production.to.MpsTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.controller
@Class			MpsController.java
@Author			wonminlee, kong
@Description	주생산계획 관련 컨트롤러
@Create			2019.02.11
@Last Update    2019.02.15 : 주생산계획 조회 메서드생성 (wonminlee)
				2019.02.20 : MPS적용메서드 추가(kong)
************************************************************************/
@Controller
public class MpsController{
	@Autowired
	private ProductionServiceFacade productionServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//주생산계획 조회메서드
	@RequestMapping("logi/production/findMpsList.do")
	public void findMpsList (HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> searchDate=new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String fromDate = inData.getVariable("fromDate").getString();  //mrpSelect
		String toDate = inData.getVariable("toDate").getString(); //오늘날짜
		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);
		List<MpsTO> mpsList = productionServiceFacade.findMpsList(searchDate);
		datasetBeanMapper.beansToDataset(outData, mpsList, MpsTO.class);


	}
	
	@RequestMapping("logi/production/registerMps.do")
	public void registerMps(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<MpsTO> mpsList = datasetBeanMapper.datasetToBeans(inData, MpsTO.class);

		List<ContractDetailTO> contractDetailList = datasetBeanMapper.datasetToBeans(inData, ContractDetailTO.class);

		productionServiceFacade.registMps(mpsList,contractDetailList);
	}
}

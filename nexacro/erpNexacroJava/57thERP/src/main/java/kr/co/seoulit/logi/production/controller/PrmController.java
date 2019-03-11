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
import kr.co.seoulit.logi.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.controller
@Class			PrmController.java
@Author			wonminlee
@Description	소요량 전개 취합 관련 컨트롤러
@Create			2019.02.11
@Last Update    2019.02.26 메서드추가
************************************************************************/

@Controller
public class PrmController {
	@Autowired
	private ProductionServiceFacade productionServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


	@RequestMapping("logi/production/findPrmList.do")
	public void findPrmList (HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<PrmTO> mrpList = productionServiceFacade.findPrmList();

		datasetBeanMapper.beansToDataset(outData, mrpList, PrmTO.class);


	}
	@RequestMapping("logi/production/registPrm.do")
	public void registPrm(HttpServletRequest request, HttpServletResponse response) throws Exception{
//창고업무

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		request.getAttribute("outData");

		List<WorkInstructionTO> workInstructionList = datasetBeanMapper.datasetToBeans(inData, WorkInstructionTO.class);

		List<PrmTO> prmList = datasetBeanMapper.datasetToBeans(inData, PrmTO.class);

		List<StockTO> stockList = datasetBeanMapper.datasetToBeans(inData, StockTO.class);

		List<WarehousingTO> warehousingList = datasetBeanMapper.datasetToBeans(inData, WarehousingTO.class);

		productionServiceFacade.registPrm(workInstructionList, prmList, stockList, warehousingList);

		findPrmList(request, response);


	}




}


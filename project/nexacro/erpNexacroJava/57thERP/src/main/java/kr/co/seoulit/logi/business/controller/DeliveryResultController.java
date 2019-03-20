package kr.co.seoulit.logi.business.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.serviceFacade.BusinessServiceFacade;
import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;
import kr.co.seoulit.logi.purchase.to.StockTO;

@Controller
public class DeliveryResultController{
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("logi/business/findDeliveryResultList.do")
	public void findDeliveryResultList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<DeliveryResultTO> deliveryResultList= businessServiceFacade.findDeliveryResultList();

        datasetBeanMapper.beansToDataset(outData, deliveryResultList, DeliveryResultTO.class);

	}
	@RequestMapping("logi/business/registDeliveryResult.do")
	public void registDeliveryResult(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<ContractTO> contractList = datasetBeanMapper.datasetToBeans(inData, ContractTO.class);
		List<ContractDetailTO> contractDetailList = datasetBeanMapper.datasetToBeans(inData, ContractDetailTO.class);
		List<StockTO> stockList = datasetBeanMapper.datasetToBeans(inData, StockTO.class);
		List<DeliveryResultTO> deliveryResultList = datasetBeanMapper.datasetToBeans(inData, DeliveryResultTO.class);

		businessServiceFacade.registDeliveryResult(contractList,contractDetailList,stockList,deliveryResultList);

		findDeliveryResultList(request, response);


	}

	@RequestMapping("logi/business/findCompleteDeliveryResultList.do")
	public void findCompleteDeliveryResultList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> searchDate=new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);


		List<CompleteDeliveryResultTO> CompleteDeliveryResultList= businessServiceFacade.findCompleteDeliveryResultList(searchDate);

        datasetBeanMapper.beansToDataset(outData, CompleteDeliveryResultList, CompleteDeliveryResultTO.class);

	}
}

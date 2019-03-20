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
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;

@Controller
public class EstimateController{
	@Autowired
	private BusinessServiceFacade businessServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("logi/business/findEstimateList.do")
	public void findEstimateList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> searchDate=new HashMap<>();
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String fromDate = inData.getVariable("fromDate").getString();
		
		String toDate = inData.getVariable("toDate").getString();
		
		searchDate.put("fromDate", fromDate);
		
		searchDate.put("toDate", toDate);
		
		List<EstimateTO> estimateList = businessServiceFacade.findEstimateList(searchDate);

		datasetBeanMapper.beansToDataset(outData, estimateList, EstimateTO.class);

	}
	@RequestMapping("logi/business/findEstimateDialog.do")
	public void findEstimateDialog(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<EstimateTO> estimateList = businessServiceFacade.findEstimateDialog();

		datasetBeanMapper.beansToDataset(outData, estimateList, EstimateTO.class);

	}

	@RequestMapping("logi/business/findEstimateDetailList.do")
	public void findEstimateDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String estimateNo=null;
		
		if(inData.getVariableCount()>0) {
			estimateNo = inData.getVariable("estimateNo").getString();
		}
		
		List<EstimateDetailTO> estimateDetailList = businessServiceFacade.findEstimateDetailList(estimateNo);

		datasetBeanMapper.beansToDataset(outData, estimateDetailList, EstimateDetailTO.class);

	}

	@RequestMapping("logi/business/registerEstimateDetail.do")
	public void registerEstimateDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<EstimateTO> estimateList = datasetBeanMapper.datasetToBeans(inData, EstimateTO.class);

		List<EstimateDetailTO> estimateDetailList = datasetBeanMapper.datasetToBeans(inData, EstimateDetailTO.class);
		System.out.println("a");
		businessServiceFacade.registEstimateDetail(estimateList,estimateDetailList);

	}

}
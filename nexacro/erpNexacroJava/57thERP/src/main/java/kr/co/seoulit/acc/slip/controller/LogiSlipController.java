package kr.co.seoulit.acc.slip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.acc.slip.serviceFacade.SlipServiceFacade;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.sys.applicationService.BaseApplicationService;
import kr.co.seoulit.sys.dao.SequenceDAO;

@Controller
public class LogiSlipController{

	@Autowired
	private SlipServiceFacade slipServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


	@RequestMapping("acc/slip/findContractSlipDetailList.do")
	public void findContractSlipDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String,Object> logiSlipReq=new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String requestStatus = inData.getVariable("requestStatus").getString();
		String value = inData.getVariable("value").getString();
		System.out.println(value);
		String[] contractArrayList=value.split(",");

		logiSlipReq.put("requestStatus", requestStatus);
		logiSlipReq.put("value", contractArrayList);
		List<ContractDetailTO> contractDetailList = slipServiceFacade.findContractSlipDetailList(logiSlipReq);

		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);

	}
	@RequestMapping("acc/slip/findOrderSlipDetailList.do")
	public void findOrderSlipDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String,Object> logiSlipReq=new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String requestStatus = inData.getVariable("requestStatus").getString();
		String value = inData.getVariable("value").getString();
		System.out.println(value);
		String[] orderArrayList=value.split(",");

		logiSlipReq.put("requestStatus", requestStatus);
		logiSlipReq.put("value", orderArrayList);
		List<OrderDetailTO> orderDetailList = slipServiceFacade.findOrderSlipDetailList(logiSlipReq);

		datasetBeanMapper.beansToDataset(outData, orderDetailList, OrderDetailTO.class);

	}

	@RequestMapping("acc/slip/registLogiSlipList.do")
	public void registLogiSlipList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		HashMap<String,Object> logiSlipMap=new HashMap<>();
		String deptCode = inData.getVariable("deptCode").getString();
		String empCode = inData.getVariable("empCode").getString();
		String reportingDate = inData.getVariable("reportingDate").getString();
		String businessCode = inData.getVariable("businessCode").getString();
		String req = inData.getVariable("req").getString();

		if(req.equals("수주선급") || req.equals("납품완료")){
			List<ContractTO> contractTOList = datasetBeanMapper.datasetToBeans(inData, ContractTO.class);
			List<ContractDetailTO> logiSlipList = datasetBeanMapper.datasetToBeans(inData, ContractDetailTO.class);
			logiSlipMap.put("logiSlipList", logiSlipList);
			logiSlipMap.put("contractTOList", contractTOList);
			logiSlipMap.put("class", ContractDetailTO.class);
		}else if(req.equals("발주선급") || req.equals("입고완료")){
			List<OrderInfoTO> orderInfoList = datasetBeanMapper.datasetToBeans(inData, OrderInfoTO.class);
			List<OrderDetailTO> logiSlipList = datasetBeanMapper.datasetToBeans(inData, OrderDetailTO.class);
			logiSlipMap.put("logiSlipList", logiSlipList);
			logiSlipMap.put("orderInfoList", orderInfoList);
			logiSlipMap.put("class", OrderDetailTO.class);
		}
		logiSlipMap.put("deptCode", deptCode);
		logiSlipMap.put("empCode", empCode);
		logiSlipMap.put("reportingDate", reportingDate);
		logiSlipMap.put("businessCode", businessCode);
		logiSlipMap.put("req", req);

		slipServiceFacade.registLogiSlipList(logiSlipMap);
	}

}
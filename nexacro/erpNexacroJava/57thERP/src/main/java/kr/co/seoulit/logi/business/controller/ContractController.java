package kr.co.seoulit.logi.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.business.exception.ContractNotFoundException;
import kr.co.seoulit.logi.business.serviceFacade.BusinessServiceFacade;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;

@Controller
public class ContractController {
	@Autowired
	private BusinessServiceFacade businessServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@Autowired
	private DataSource dataSource;

	@RequestMapping("logi/business/findContractList.do")
	public void findContractList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HashMap<String, Object> searchDate = new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		searchDate.put("fromDate", fromDate);
		searchDate.put("toDate", toDate);
		List<ContractTO> contractList = businessServiceFacade.findContractList(searchDate);
		List<ContractDetailTO> contractDetailList=new ArrayList<>();
		for(ContractTO contractTO:contractList) {
			for(ContractDetailTO contractDetailTO:contractTO.getContractDetailList()) {
				contractDetailList.add(contractDetailTO);
			}
		}
		datasetBeanMapper.beansToDataset(outData, contractList, ContractTO.class);
		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);

	}

	@RequestMapping("logi/business/findContractDetailList.do")
	public void findContractDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String contractNo=null;
		
		if(inData.getVariableCount()>0) {
			contractNo = inData.getVariable("contractNo").getString();
		}
		
		List<ContractDetailTO> contractDetailList = businessServiceFacade.findContractDetailList(contractNo);

		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);

	}
	
	@RequestMapping("logi/business/findAllContractDetailList.do")
	public void findAllContractDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<ContractDetailTO> contractDetailList = businessServiceFacade.findAllContractDetailList();

		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);

	}

	@RequestMapping("logi/business/findRangedContractDetailList.do")
	public void findRangedContractDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> searchDate = new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		HashMap<String,Object> value=new HashMap<>();

		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		value.put("fromDate", fromDate);
		value.put("toDate", toDate);
		
		System.out.println(value.get("fromDate"));
	
		if(value.get("fromDate").equals("mpsSelect")) {
			System.out.println(fromDate +" and "+toDate);
			searchDate.put("requestStatus", "mpsSelect");
			searchDate.put("value", "unDelivery");
		} else {
			System.out.println(" aaaaaaaaa ");
			searchDate.put("requestStatus", "date");
			searchDate.put("value", value);
		}
		List<ContractDetailTO> contractDetailList = businessServiceFacade.findRangedContractDetailList(searchDate);
/*		if(contractDetailList.size()<1){
			throw new ContractNotFoundException("해당하는 수주목록을 찾지 못했습니다");
		}*/
		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);

	}

	@RequestMapping("logi/business/registerContract.do")
	public void registerContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<ContractTO> contractList = datasetBeanMapper.datasetToBeans(inData, ContractTO.class);

		List<ContractDetailTO> contractDetailList = datasetBeanMapper.datasetToBeans(inData, ContractDetailTO.class);

		businessServiceFacade.registContract(contractList, contractDetailList);

	}

	@RequestMapping("logi/business/pdfPrint.do")
	public ModelAndView pdfPrintEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String contract_no = request.getParameter("contractNo");
		//response.getOutputStream().flush();

		ModelMap modelMap = new ModelMap();
		modelMap.put("format", "pdf");
		modelMap.put("contract_no", contract_no);
		modelMap.put("jdbcDataSource", dataSource);
		ModelAndView modelAndView = new ModelAndView("multiformat-view", modelMap);

		return modelAndView;
	}

	@RequestMapping("logi/business/findContractTo.do")
	public void findContractTo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		PlatformData outData = (PlatformData) request.getAttribute("outData");

		String contractNo=null;

		if(inData.getVariableCount()>0) {
			contractNo = inData.getVariable("contractNo").getString();
		}

		List<ContractTO> contractTo = businessServiceFacade.findContractTo(contractNo);

		datasetBeanMapper.beansToDataset(outData, contractTo, ContractTO.class);

	}

/*	@RequestMapping("logi/business/sumLeadTime.do")
	public void sumLeadTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Object> searchItem = new HashMap<>();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		String fromDate = inData.getVariable("fromDate").getString();
		searchItem.put("fromDate", fromDate);
		List<ContractDetailTO> contractDetailList = businessServiceFacade.findRangedContractDetailList(searchDate);

		datasetBeanMapper.beansToDataset(outData, contractDetailList, ContractDetailTO.class);
	}*/
}
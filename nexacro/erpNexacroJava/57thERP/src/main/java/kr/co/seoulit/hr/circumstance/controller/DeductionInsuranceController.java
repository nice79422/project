package kr.co.seoulit.hr.circumstance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import kr.co.seoulit.hr.circumstance.to.DeductionTaxTO;
import kr.co.seoulit.hr.circumstance.to.IncomeTaxTO;

@Controller
public class DeductionInsuranceController {
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/* 보험공제 목록을 가져오는 메서드 */
	@RequestMapping("hr/circumstance/findDeductionInsuranceList.do")
	public void findDeductionInsuranceList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<DeductionTaxTO> deductionsTaxList=circumstanceServiceFacade.findDeductionTaxList();
		List<IncomeTaxTO> incomeTaxList=circumstanceServiceFacade.findIncomeTaxList();
		datasetBeanMapper.beansToDataset(outData, deductionsTaxList, DeductionTaxTO.class);
		datasetBeanMapper.beansToDataset(outData, incomeTaxList, IncomeTaxTO.class);
    }
	
	// 보험공제, 소득세를 삭제하는 메서드 
	@RequestMapping("hr/circumstance/removeDeductionTax.do")
	public void removeDeductionTax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		DeductionTaxTO deductionTaxTO=datasetBeanMapper.datasetToBean(inData, DeductionTaxTO.class);
		List<IncomeTaxTO> incomeTaxList=datasetBeanMapper.datasetToBeans(inData, IncomeTaxTO.class);
		circumstanceServiceFacade.removeDeductionTax(deductionTaxTO);
		circumstanceServiceFacade.removeIncomeTaxList(incomeTaxList);
		findDeductionInsuranceList(request,response);
	}
	
	// 보험공제, 소득세 관련부분을 일괄적으로 처리하는 메서드 
	@RequestMapping("hr/circumstance/batchDeductionTax.do")
	public void batchDeductionTax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		DeductionTaxTO deductionTaxBean=datasetBeanMapper.datasetToBean(inData, DeductionTaxTO.class);
		List<IncomeTaxTO> incomeTaxList=datasetBeanMapper.datasetToBeans(inData, IncomeTaxTO.class);
		circumstanceServiceFacade.addDeductionTax(deductionTaxBean);
		circumstanceServiceFacade.addIncomeTaxList(incomeTaxList);
		findDeductionInsuranceList(request,response);
	}
}

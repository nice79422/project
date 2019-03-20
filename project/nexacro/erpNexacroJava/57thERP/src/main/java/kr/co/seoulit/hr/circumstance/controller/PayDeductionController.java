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
import kr.co.seoulit.hr.circumstance.to.PayDeductionItemTO;

@Controller
public class PayDeductionController {

	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	// 지급/공제항목을 조회하는 메서드 
	@RequestMapping("hr/circumstance/findPayDeductionList.do")
	public void findPayDeductionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<PayDeductionItemTO> payDeductionList = circumstanceServiceFacade.findPayDeductionList();
		datasetBeanMapper.beansToDataset(outData, payDeductionList, PayDeductionItemTO.class);
    }
	
	// 지급/공제항목에 관한 일괄처리하는 메서드 
	@RequestMapping("hr/circumstance/batchPayDeduction.do")
	public void batchPayDeduction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		 
		List<PayDeductionItemTO> payDeductionList=datasetBeanMapper.datasetToBeans(inData, PayDeductionItemTO.class);
		   circumstanceServiceFacade.batchPayDeduction(payDeductionList);
		   findPayDeductionList(request,response);
	}
	
}
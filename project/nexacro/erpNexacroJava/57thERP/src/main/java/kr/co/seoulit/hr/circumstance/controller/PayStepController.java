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
import kr.co.seoulit.hr.circumstance.to.PayStepTO;


@Controller
public class PayStepController{
	@Autowired
	private CircumstanceServiceFacade circumstanceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/* 호봉목록을 가져오는 메서드 */
	@RequestMapping("hr/circumstance/findPayStepList.do")
	public void findPayStepList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<PayStepTO> payStepList=circumstanceFacade.findPayStepList();
		datasetBeanMapper.beansToDataset(outData, payStepList, PayStepTO.class);
    }

	/* 호봉관련처리를 일괄적으로 하는 메서드 */
	@RequestMapping("hr/circumstance/batchPayStep.do")
	public void batchPayStep(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<PayStepTO> payStepList=datasetBeanMapper.datasetToBeans(inData, PayStepTO.class);
		circumstanceFacade.batchPayStepList(payStepList);
		findPayStepList(request,response);
	}
	
	
	
}

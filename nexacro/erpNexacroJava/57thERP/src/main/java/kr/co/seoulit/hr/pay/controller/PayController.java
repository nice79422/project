package kr.co.seoulit.hr.pay.controller;
/**
 * @Package  com.seoul.erp.common.hr
 * @Class    PayController.java
 * @Create   2019. 02. 15.
 * @Author   허용석
 * @Description 급여계산 프로시저 호출 컨트롤러 클래스
 *
 * @LastUpdated
 *      변수를 MAP으로 담아 보내도록 수정 : 2019. 02. 15. by 허용석
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.pay.serviceFacade.PayServiceFacade;
import kr.co.seoulit.hr.pay.to.PayDeductionTO;
import kr.co.seoulit.hr.pay.to.SalaryInputTO;



@Controller
public class PayController {

	/* PayServiceFacade SETTING */
	@Autowired
	private PayServiceFacade payServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 급여를 계산하는 메서드 */
	@RequestMapping("hr/pay/payCalculateProcess.do")
	public void payCalculateProcess(HttpServletRequest request, HttpServletResponse response) throws ProcedureException,Exception{

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String paymentDate = inData.getVariable("paymentDate").getString();
		String standardDate = inData.getVariable("standardDate").getString();

		Map<String,Object> vList=new HashMap<>();
		vList.put("paymentDate", paymentDate);
		vList.put("standardDate", standardDate);

		List<SalaryInputTO> salaryInputList = payServiceFacade.payCalculate(vList);
		List<PayDeductionTO> payDeductionList=new ArrayList<PayDeductionTO>();
		for(SalaryInputTO salaryInputBean:salaryInputList){
			payDeductionList.addAll(salaryInputBean.getPayDeductionList());
		}
		datasetBeanMapper.beansToDataset(outData, salaryInputList, SalaryInputTO.class);
		datasetBeanMapper.beansToDataset(outData, payDeductionList, PayDeductionTO.class);

	}

}
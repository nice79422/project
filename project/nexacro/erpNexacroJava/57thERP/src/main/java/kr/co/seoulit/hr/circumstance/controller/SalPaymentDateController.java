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
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;

@Controller
public class SalPaymentDateController {

	@Autowired
	private CircumstanceServiceFacade circumstanceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("hr/circumstance/findSalPaymentDateList.do")
	public void findSalPaymentDateList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String inputedYearMonth = inData.getVariable("inputedYearMonth").getString();
		List<SalPaymentDateTO> salPaymentsDateList = circumstanceFacade.findSalPaymentDateList(inputedYearMonth);
		datasetBeanMapper.beansToDataset(outData, salPaymentsDateList, SalPaymentDateTO.class);
    }


	// 귀속년월의 급/상여 지급일 및 관련정보를 등록및 수정,삭제한 내용을 일괄처리하는 메서드 
	@RequestMapping("hr/circumstance/batchSalPaymentDate.do")
	public void batchSalPaymentDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<SalPaymentDateTO> salPaymentDateList = datasetBeanMapper.datasetToBeans(inData, SalPaymentDateTO.class);
		circumstanceFacade.batchSalPaymentDate(salPaymentDateList);
    }
	
}

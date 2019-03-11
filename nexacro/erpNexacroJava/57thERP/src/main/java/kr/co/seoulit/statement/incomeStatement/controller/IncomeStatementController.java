package kr.co.seoulit.statement.incomeStatement.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.statement.incomeStatement.serviceFacade.IncomeStatementServiceFacade;
import kr.co.seoulit.statement.incomeStatement.to.IncomeStatementTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class IncomeStatementController {

	@Autowired
	private IncomeStatementServiceFacade incomeStatementServiceFacade;
	
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	

	@RequestMapping("statement/incomeStatement/findIncomeStatement.do")		
	public void findIncomeStatement(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findIncomeStatement 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String approvalDate = inData.getVariable("approvalDate").getString();
		HashMap<String,String> approvalDateMap= new HashMap<>();
		approvalDateMap.put("approvalDate", approvalDate);
		System.out.println(" approvalDate : " + approvalDate);

		List<IncomeStatementTO> incomeStatementBeanList = incomeStatementServiceFacade.findIncomeStatement(approvalDateMap);
		System.out.println(" sf -> findIncomeStatement ");

		datasetBeanMapper.beansToDataset(outData, incomeStatementBeanList, IncomeStatementTO.class);
	}
	
}

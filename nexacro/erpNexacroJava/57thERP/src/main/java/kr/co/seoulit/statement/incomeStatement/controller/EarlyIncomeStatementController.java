package kr.co.seoulit.statement.incomeStatement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.statement.incomeStatement.serviceFacade.IncomeStatementServiceFacade;
import kr.co.seoulit.statement.incomeStatement.to.EarlyIncomeStatementTO;
import kr.co.seoulit.statement.incomeStatement.to.EarlyrevenuesAndExpenseTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class EarlyIncomeStatementController {
	
	@Autowired
	private IncomeStatementServiceFacade incomeStatementServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	
	@RequestMapping("statement/incomeStatement/findIncomeStatementOnLastYear.do")		
	public void findPreIncomeStatementList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findPreIncomeStatementList 시작 ");

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		List<EarlyrevenuesAndExpenseTO> earlyrevenuesAndExpenseTO = incomeStatementServiceFacade.findRevenuesAndExpenseOnLastYear();
		List<EarlyIncomeStatementTO> earlyIncomeStatementTO = incomeStatementServiceFacade.findIncomeStatementOnLastYear();
		System.out.println(" sf -> findPreIncomeStatementList ");

		datasetBeanMapper.beansToDataset(outData, earlyrevenuesAndExpenseTO, EarlyrevenuesAndExpenseTO.class);	
		datasetBeanMapper.beansToDataset(outData, earlyIncomeStatementTO, EarlyIncomeStatementTO.class);	

		}

}


package kr.co.seoulit.statement.financialStatement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.statement.financialStatement.applicationService.FinancialStatementApplicationService;
import kr.co.seoulit.statement.financialStatement.serviceFacade.FinancialStatementServiceFacade;
import kr.co.seoulit.statement.financialStatement.to.EarlyAssetsCountTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyFinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyLiabilitiesAndCapitalCountTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class EarlyFinancialStatementsController {
	
	@Autowired
	private FinancialStatementServiceFacade financialStatementServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	
	@RequestMapping("statement/financialStatement/findFinancialStatementsOnLastYear.do")	
	public void findFinancialStatementsOnLastDay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findFinancialStatementsOnLastYear 시작 ");

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		List<EarlyAssetsCountTO> earlyAssetsCountTO = financialStatementServiceFacade.findAssetsOnLastYear();
		List<EarlyLiabilitiesAndCapitalCountTO> earlyLiabilitiesAndCapitalCountTO = financialStatementServiceFacade.findLiabilitiesAndCapitalOnLastYear();
		List<EarlyFinancialStatementsTO> earlyFinancialStatementsTO = financialStatementServiceFacade.findFinancialStatementsOnLastYear();
		System.out.println("SF -> findFinancialStatementsOnLastYear");
		
		datasetBeanMapper.beansToDataset(outData, earlyAssetsCountTO, EarlyAssetsCountTO.class);	
		datasetBeanMapper.beansToDataset(outData, earlyLiabilitiesAndCapitalCountTO, EarlyLiabilitiesAndCapitalCountTO.class);	
		datasetBeanMapper.beansToDataset(outData, earlyFinancialStatementsTO, EarlyFinancialStatementsTO.class);
		}
}

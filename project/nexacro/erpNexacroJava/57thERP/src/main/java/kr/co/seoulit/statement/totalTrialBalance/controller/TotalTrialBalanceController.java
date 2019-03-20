package kr.co.seoulit.statement.totalTrialBalance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.statement.totalTrialBalance.serviceFacade.TotalTrialBalanceServiceFacade;
import kr.co.seoulit.statement.totalTrialBalance.to.TotalTrialBalanceTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class TotalTrialBalanceController {

	@Autowired
	private TotalTrialBalanceServiceFacade statementServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	

	@RequestMapping("statement/totalTrialBalance/findTotalTrialBalanceList.do")		
	public void findTotalTrialBalanceList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findTotalTrialBalanceList 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String approvalDate = inData.getVariable("approvalDate").getString();
		if(approvalDate.equals("")) {
			
		} else {
	    	String a1=approvalDate.substring(0,4);
	    	String a2=approvalDate.substring(4,6);
	    	String a3=approvalDate.substring(6,8);
	    	approvalDate=a1+"-"+a2+"-"+a3;
	    	System.out.println("날짜확인"+approvalDate);
		}
		
		System.out.println(" approvalDate : " + approvalDate);

		HashMap<String,String> approvalDateMap= new HashMap<>();
		approvalDateMap.put("approvalDate", approvalDate);
		List<TotalTrialBalanceTO> totalTrialBalanceList = statementServiceFacade.findTotalTrialBalanceList(approvalDateMap);
		System.out.println(" sf -> findTotalTrialBalanceList ");

		datasetBeanMapper.beansToDataset(outData, totalTrialBalanceList, TotalTrialBalanceTO.class);
	}

}

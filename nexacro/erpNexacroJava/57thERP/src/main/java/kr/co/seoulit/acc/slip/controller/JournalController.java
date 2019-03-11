package kr.co.seoulit.acc.slip.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.acc.slip.serviceFacade.SlipServiceFacade;
import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;


@Controller
public class JournalController{

	@Autowired
	private SlipServiceFacade slipServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


/*	@RequestMapping("acc/slip/getJournalListForSlip.do")
	//Journal
	public void getJournalListForSlip(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" getSlipCount 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String slipNo= inData.getVariable("slipNo").getString();
		System.out.println("slipNo : " + slipNo);
		
		List<JournalTO> journalList=slipServiceFacade.getJournalListForSlip(slipNo);
		System.out.println(" sf -> getSlipCount ");
		
		datasetBeanMapper.beansToDataset(outData, journalList, JournalTO.class);
	}
*/

	@RequestMapping("acc/slip/findJournalList.do")
	public void findJournalList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findJournalList 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		HashMap<String, Object> journalDate = new HashMap<>();
		
		String startDate= inData.getVariable("startDate").getString();
		String endDate= inData.getVariable("endDate").getString();
		
		if(startDate.equals("")) {
			
		} else {
	    	String a1=startDate.substring(0,4);
	    	String a2=startDate.substring(4,6);
	    	String a3=startDate.substring(6,8);
	    	startDate=a1+"-"+a2+"-"+a3;
	    	System.out.println("날짜확인"+startDate);
	    	  
	    	String b1=endDate.substring(0,4);
	    	String b2=endDate.substring(4,6);
	    	String b3=endDate.substring(6,8);
	    	endDate=b1+"-"+b2+"-"+b3;
		}
		
		journalDate.put("startDate", startDate);
		journalDate.put("endDate", endDate);
		
		System.out.println(" startDate : " + startDate);
		System.out.println(" endDate : " + endDate);
		
		
		List<JournalTO> journalList=slipServiceFacade.findJournalList(journalDate);
		System.out.println(" sf -> findJournalList ");
		System.out.println(" JournalNo : " + journalList.get(0).getJournalNo());
		
		datasetBeanMapper.beansToDataset(outData, journalList, JournalTO.class);
	}
/*	
	//JournalDetail
	@RequestMapping("acc/slip/getJournalDetailList.do")
	public void getJournalDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" getJournalDetailList 시작 ");

		PlatformData outData = (PlatformData) request.getAttribute("outData");
			
		List<JournalDetailTO> journalDetailList=slipServiceFacade.getJournalDetailList();
		System.out.println(" JournalNo : " +journalDetailList.get(0).getJournalNo());
		System.out.println(" sf -> getJournalDetailList ");
		
		datasetBeanMapper.beansToDataset(outData, journalDetailList, JournalDetailTO.class);
	}*/
    //journalDetail dialog
	@RequestMapping("acc/slip/findJournalDetailByJournalNo.do")
	public void findJournalDetailByJournalNo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findJournalDetailByJournalNo 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		String journalNo= inData.getVariable("journalNo").getString();
		
		List<JournalDetailTO> journalDetail=slipServiceFacade.findJournalDetailByJournalNo(journalNo);
		System.out.println(" sf -> findJournalDetailByJournalNo ");
		
		datasetBeanMapper.beansToDataset(outData, journalDetail, JournalDetailTO.class);
	}

	@RequestMapping("acc/slip/findAccountControlDetailList.do")
	public void  findAccountControlDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findAccountControlDetailList 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String accountCode=inData.getVariable("accountCode").getString();
		System.out.println("accountCode : " + accountCode);
		
		List<AccountControlDetailTO> accountControlDetailList=slipServiceFacade.findAccountControlDetailList(accountCode);
		System.out.println(" sf -> findAccountControlDetailList ");
		for(AccountControlDetailTO an:accountControlDetailList)
			System.out.println("ControlName : "+an.getAccountcontrolName());
		
		datasetBeanMapper.beansToDataset(outData, accountControlDetailList, AccountControlDetailTO.class);
	}

/*	@RequestMapping("acc/slip/batchJournalDetailList.do")
	public void batchJournalDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" batchJournalDetailList 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		List<JournalDetailTO> journalDetailList = datasetBeanMapper.datasetToBeans(inData, JournalDetailTO.class);
		System.out.println(" sf -> batchJournalDetailList ");
		
		slipServiceFacade.batchJournalDetailInfo(journalDetailList);
	}
*/



}
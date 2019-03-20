package kr.co.seoulit.acc.accSubMgt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.acc.accSubMgt.serviceFacade.AcBaseServiceFacade;
import kr.co.seoulit.acc.accSubMgt.to.ParentAccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class AccountController {

	@Autowired
	private AcBaseServiceFacade acBaseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//계정부모코드
	@RequestMapping("acc/accSubMgt/findParentAccountList.do")
	public void findParentAccountList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findParentAccountList 시작 ");

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		System.out.println(" sf -> findParentAccountList ");
		List<ParentAccountSummaryTO> parentAccountSummaryList = acBaseServiceFacade.findParentAccountList();
	   
		List<AccountSummaryTO> accountList=new ArrayList<>();
		
		for(ParentAccountSummaryTO parentAccountTOList:parentAccountSummaryList) {
			accountList.addAll(parentAccountTOList.getAccountList());
		}
   
		datasetBeanMapper.beansToDataset(outData, parentAccountSummaryList, ParentAccountSummaryTO.class);
		datasetBeanMapper.beansToDataset(outData, accountList, AccountSummaryTO.class);
	}


	@RequestMapping("acc/accSubMgt/findAccountList.do")
	public void findAccountList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" findAccountList 시작 ");

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		List<AccountTO> accountTO = acBaseServiceFacade.findAccountList();
		List<AccountSummaryTO> accountSummaryTO = acBaseServiceFacade.findAccountSummaryList();
		System.out.println(" sf -> findAccountList ");
		
		datasetBeanMapper.beansToDataset(outData, accountTO, AccountTO.class);
		datasetBeanMapper.beansToDataset(outData, accountSummaryTO, AccountSummaryTO.class);
	}

	@RequestMapping("acc/accSubMgt/modifyAccountDetailList.do")
	public void modifyAccountDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" modifyAccountDetailList 시작 ");		

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
        List<AccountSummaryTO> accountSummaryList=datasetBeanMapper.datasetToBeans(inData , AccountSummaryTO.class );
		System.out.println(" getAccountInnerCode : " + accountSummaryList.get(0).getAccountInnerCode());	
		System.out.println(" getAccountSummaryDescription : " + accountSummaryList.get(0).getAccountDescription());	
		System.out.println(" getStatus : " + accountSummaryList.get(0).getStatus());	
		System.out.println(" sf -> modifyAccountDetailList ");
		
        acBaseServiceFacade.modifyAccountSummaryList(accountSummaryList);
	}

}

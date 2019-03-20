package kr.co.seoulit.acc.slip.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.acc.slip.applicationService.OtherDeptSlipApplicationService;
import kr.co.seoulit.acc.slip.applicationService.SlipApplicationService;
import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;


@Service
public class SlipServiceFacadeImpl implements SlipServiceFacade{


    @Autowired
	private SlipApplicationService slipApplicationService;
    @Autowired
	private OtherDeptSlipApplicationService otherDeptslipApplicationService;


	@Override
	public List<SlipTO> findSlipListByCondition(HashMap<String, Object> slipMap) {
		// TODO Auto-generated method stub
		List<SlipTO> slipList=slipApplicationService.findSlipListByCondition(slipMap);
		return slipList;
	}


/*	@Override
	public List<SlipTO> findSlipByReportingDate(String reportingDate) {
		// TODO Auto-generated method stub
		List<SlipTO> slipRowCount=slipApplicationService.findSlipByReportingDate(reportingDate);
		return slipRowCount;
	}
*/
	@Override
	public void batchSlipAndJournalAndJournalDetailInfo(List<SlipTO> slipInfo, List<JournalTO> journalInfo, List<JournalDetailTO> journalDetailInfo) {
		// TODO Auto-generated method stub
		slipApplicationService.batchSlipAndJournalAndJournalDetailInfo(slipInfo,journalInfo,journalDetailInfo);
	}


/*	@Override
	public List<JournalTO> getJournalListForSlip(String slipNo) {
		// TODO Auto-generated method stub
		System.out.println("aaa");
		List<JournalTO> journalListForSlip = slipApplicationService.getJournalListForSlip(slipNo);
		return journalListForSlip;
	}
*/

	@Override
	public List<JournalTO> findJournalList(HashMap<String, Object> journalDate) {
		// TODO Auto-generated method stub
		List<JournalTO> journalList = slipApplicationService.findJournalList(journalDate);
		return journalList;
	}

/*
	@Override
	public List<JournalDetailTO> getJournalDetailList() {
		// TODO Auto-generated method stub
		List<JournalDetailTO> journalDetailList=slipApplicationService.getJournalDetailList();
		return journalDetailList;
	}*/


	@Override
	public List<JournalDetailTO> findJournalDetailByJournalNo(String journalNo)  {
		// TODO Auto-generated method stub
		List<JournalDetailTO> journalDetail=slipApplicationService.findJournalDetailByJournalNo(journalNo);
		return journalDetail;
	}


	@Override
	public List<AccountControlDetailTO> findAccountControlDetailList(String accountCode) {
		// TODO Auto-generated method stub
		List<AccountControlDetailTO> accountControlDetailList=slipApplicationService.findAccountControlDetailList(accountCode);
		return accountControlDetailList;
	}


/*	@Override
	public void batchJournalDetailInfo(List<JournalDetailTO> journalDetailList) {
		// TODO Auto-generated method stub
		slipApplicationService.batchJournalDetailInfo(journalDetailList);
	}*/

	@Override
	public List<ContractDetailTO> findContractSlipDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return otherDeptslipApplicationService.findContractSlipDetailList(logiSlipReq);
	}

	@Override
	public void registLogiSlipList(HashMap<String,Object> logiSlipMap) {
		// TODO Auto-generated method stub
		otherDeptslipApplicationService.registLogiSlipList(logiSlipMap);
	}


	@Override
	public void modifyApprovalSlipList(List<SlipTO> slipInfo) {
		// TODO Auto-generated method stub
	      slipApplicationService.modifyApprovalSlipList(slipInfo);
	}


	@Override
	public List<OrderDetailTO> findOrderSlipDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return otherDeptslipApplicationService.findOrderSlipDetailList(logiSlipReq);
	}





}

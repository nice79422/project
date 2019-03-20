package kr.co.seoulit.acc.slip.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;


public interface SlipApplicationService {
	
	//slip
	public List<SlipTO> findSlipListByCondition(HashMap<String, Object> slipMap);
	  //public List<SlipTO> findSlipByReportingDate( String reportingDate);
	public void batchSlipAndJournalAndJournalDetailInfo(List<SlipTO> slipInfo, List<JournalTO> journalInfo, List<JournalDetailTO> journalDetailInfo);
	
//journal
	//public List<JournalTO> getJournalListForSlip(String slipNo);
	
	public List<JournalTO> findJournalList(HashMap<String, Object> journalDate);

	//JournalDetail
		//public List<JournalDetailTO> getJournalDetailList();
	public List<JournalDetailTO> findJournalDetailByJournalNo(String journalNo);
	public List<AccountControlDetailTO> findAccountControlDetailList(String accountCode);
		//public void batchJournalDetailInfo(List<JournalDetailTO> journalDetailList);
	public void modifyApprovalSlipList(List<SlipTO> slipInfo);
}

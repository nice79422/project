package kr.co.seoulit.acc.slip.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.acc.slip.dao.JournalDAO;
import kr.co.seoulit.acc.slip.dao.SlipDAO;
import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.sys.dao.SequenceDAO;


@Component
public class SlipApplicationServiceImpl implements SlipApplicationService{

	@Autowired
	private SlipDAO slipDAO;
	@Autowired
	private JournalDAO journalDAO;
	@Autowired
	private SequenceDAO sequenceDAO;

	@Override
	public List<SlipTO> findSlipListByCondition(HashMap<String, Object> slipMap) {
		// TODO Auto-generated method stub
		List<SlipTO> slipList=slipDAO.selectSlipListByCondition(slipMap);
		return slipList;
	}



	/*@Override
	public List<SlipTO> findSlipByReportingDate(String reportingDate) {
		// TODO Auto-generated method stub
		List<SlipTO> slipRowCount=slipDAO.selectSlipByReportingDate(reportingDate);
		return slipRowCount;
	}*/

	@Override
	public void batchSlipAndJournalAndJournalDetailInfo(List<SlipTO> slipInfo, List<JournalTO> journalInfo, List<JournalDetailTO> journalDetailInfo) {
		// TODO Auto-generated method stub
		for (SlipTO slipTo : slipInfo) {
			switch (slipTo.getStatus()) {
			case "inserted":
				slipDAO.insertSlipInfo(slipTo);
				break;
			case "deleted":
				slipDAO.deleteSlipInfo(slipTo);
				break;
			case "updated":
				slipDAO.updateSlipInfo(slipTo);
				break;
			}
		}

		batchJournalInfo(journalInfo);
		batchJournalDetailInfo(journalDetailInfo);
	}


	/*@Override
	public List<JournalTO> getJournalListForSlip(String slipNo) {
		// TODO Auto-generated method stub
		System.out.println("nnn");
		List<JournalTO> journalListForSlip=journalDAO.getJournalListForSlip(slipNo);
		return journalListForSlip;
	}*/


	public void batchJournalInfo(List<JournalTO> journalInfo) {
		// TODO Auto-generated method stub
		for (JournalTO journalTo : journalInfo) {
			switch (journalTo.getStatus()) {
			case "inserted":
				journalDAO.insertJournalInfo(journalTo);
				break;
			case "deleted":
				journalDAO.deleteJournalInfo(journalTo);
				break;
			case "updated":
				journalDAO.updateJournalInfo(journalTo);
				break;
			}
		}
	}

@Override
	public List<JournalTO> findJournalList(HashMap<String, Object> journalDate) {
		// TODO Auto-generated method stub
		List<JournalTO> journalList=journalDAO.findJournalList(journalDate);
		return journalList;
	}
/*
	@Override
	public List<JournalDetailTO> getJournalDetailList() {
		// TODO Auto-generated method stub
		List<JournalDetailTO> journalDetailList=journalDAO.getJournalDetailList();
		return journalDetailList;
	}
	*/
	@Override
	public List<JournalDetailTO> findJournalDetailByJournalNo(String journalNo) {
		// TODO Auto-generated method stub
		List<JournalDetailTO> journalDetail=journalDAO.selectJournalDetailList(journalNo);
		return journalDetail;
	}

	@Override
	public List<AccountControlDetailTO> findAccountControlDetailList(String accountCode) {
		// TODO Auto-generated method stub
		List<AccountControlDetailTO> accountControlDetailList=journalDAO.selectAccountControlDetailList(accountCode);
		return accountControlDetailList;
	}


	public void batchJournalDetailInfo(List<JournalDetailTO> journalDetailList) {
		// TODO Auto-generated method stub
		for (JournalDetailTO journalDetailTO : journalDetailList) {
			switch (journalDetailTO.getStatus()) {
			case "inserted":
				HashMap<String, Object> takeSeq = new HashMap<String, Object>();
				takeSeq.put("findSeq", "JOURNAL_DETAIL_SEQ");
				String journalDetailSeq=sequenceDAO.selectSequenceNo(takeSeq);
				System.out.println("dddddddd"+journalDetailSeq);
				journalDetailTO.setJournaldetailNo(journalDetailSeq);
				journalDAO.insertJournalDetailInfo(journalDetailTO);
				break;
			case "deleted":
				journalDAO.deleteJournalDetailInfo(journalDetailTO);
				break;
			case "updated":
				journalDAO.updateJournalDetailInfo(journalDetailTO);
				break;
			}
		}
	}



	@Override
	public void modifyApprovalSlipList(List<SlipTO> slipInfo) {
		// TODO Auto-generated method stub
		for(SlipTO slipTo:slipInfo) {
		slipDAO.updateSlipInfo(slipTo);

		}
	}



}

package kr.co.seoulit.acc.slip.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;


public interface JournalDAO {

	//Journal
	public List<JournalTO> getJournalListForSlip(String slipNo);
	public void insertJournalInfo(JournalTO journalTo);
	public void deleteJournalInfo(JournalTO journalTo);
	public void updateJournalInfo(JournalTO journalTo);
	public List<JournalTO> findJournalList(HashMap<String, Object> journalDate);
	
	//JournalDetail
	public List<JournalDetailTO> getJournalDetailList();
	public List<JournalDetailTO> selectJournalDetailList(String journalNo);
	public List<AccountControlDetailTO> selectAccountControlDetailList(String accountCode);
	public void insertJournalDetailInfo(JournalDetailTO journalDetailTo);
	public void deleteJournalDetailInfo(JournalDetailTO journalDetailTo);
	public void updateJournalDetailInfo(JournalDetailTO journalDetailTo);
	
}

package com.test.logistics.business.dao;

import java.util.List;

import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;


public interface ContractDAO {
	int selectRowCount();
	List<ContractBean> selectContractList(int sr, int er);
	void insertContract(ContractBean contractBean);
	
	
	int selectContractDetailRowCount();
	int selectSearchRowCount(String searchCode);
	
	List<ContractDetailBean> selectContractDetailLists(int sr, int er, String searchCode);
	
	void insertContractDetail(ContractDetailBean contractDetailBean);
	void updateContractDetail(ContractDetailBean contractDetailBean);

	public List<ContractItemBean> selectContractReviewList(String startDate, String endDate);
	void updateStandByContract(ContractBean contractBean);
	void updateContract(ContractDetailBean contractDetailBean);
}

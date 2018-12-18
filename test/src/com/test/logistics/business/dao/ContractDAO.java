package com.test.logistics.business.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;


public interface ContractDAO {
	int selectRowCount();
	List<ContractBean> selectContractList(int sr, int er);
	public List<ContractItemBean> selectContractReviewList(String startDate, String endDate);
	void insertContract(ContractBean contractBean) throws DataAccessException;
	void updateContract(ContractDetailBean contractDetailBean);
}

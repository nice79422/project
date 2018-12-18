package com.test.logistics.business.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.business.to.ContractDetailBean;

public interface ContractDetailDAO {
	int selectRowCount();
	int selectRowCount(String searchCode);
	
	public int selectShippingRowCount(String code);

	List<ContractDetailBean> selectContractDetailLists(int sr, int er, String searchCode);
	
	void insertContractDetail(ContractDetailBean contractDetailBean) throws DataAccessException;
	void updateContractDetail(ContractDetailBean contractDetailBean);
	void deleteContractDetail(String code);

	public void updateShipping(String contractDetailCode);
	public List<ContractDetailBean> selectShippingList(int sr, int er, String code);
}

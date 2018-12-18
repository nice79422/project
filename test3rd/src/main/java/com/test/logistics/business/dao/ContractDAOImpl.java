package com.test.logistics.business.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;

public class ContractDAOImpl extends IBatisDAO implements ContractDAO{

	@SuppressWarnings("deprecation")
	public int selectRowCount() {
	return (int) getSqlMapClientTemplate().queryForObject("contract.selectRowCount");
	}

	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ContractBean> selectContractList(int sr, int er) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<ContractBean>) getSqlMapClientTemplate().queryForList("contract.selectContractList", map);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertContract(ContractBean contractBean){
		getSqlMapClientTemplate().update("contract.insertContract", contractBean);
	}


	@SuppressWarnings("deprecation")
	public int selectContractDetailRowCount() {
	return (int) getSqlMapClientTemplate().queryForObject("contract.selectContractDetailRowCount");
	}
	
	@SuppressWarnings("deprecation")
	public int selectSearchRowCount(String searchCode){
		return (int) getSqlMapClientTemplate().queryForObject("contract.selectSearchRowCount", searchCode);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ContractDetailBean> selectContractDetailLists(int sr, int er, String searchCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("searchCode", searchCode);
		return (List<ContractDetailBean>) getSqlMapClientTemplate().queryForList("contract.selectContractDetailLists", map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void insertContractDetail(ContractDetailBean contractDetailBean) {
		getSqlMapClientTemplate().update("contract.insertContractDetail", contractDetailBean);
	}

	@SuppressWarnings("deprecation")
	public void updateContractDetail(ContractDetailBean contractDetailBean) {
		getSqlMapClientTemplate().update("contract.updateContractDetail", contractDetailBean);
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<ContractItemBean> selectContractReviewList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<ContractItemBean>) getSqlMapClientTemplate().queryForList("contract.selectContractReviewList", map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void updateStandByContract(ContractBean contractBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("contract.updateStandByContract", contractBean);
	}



	@SuppressWarnings("deprecation")
	@Override
	public void updateContract(ContractDetailBean contractDetailBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("contract.updateContract", contractDetailBean);
		
	}
}





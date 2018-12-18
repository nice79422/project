package com.test.logistics.business.applicationservice;

import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.business.to.ClientBean;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;


public interface BusinessApplicationService {
	int getRowCount();
	int getRowCount(String tableName);
	int getRowCount(String tableName, String searchCode);

	
	List<ContractDetailBean> findContractDetailList(int sr, int er, String searchCode);
	

	public void registerClient(ClientBean clientBean);
	public List<ClientBean> clientList(int sr, int er);

	List<ContractBean> findContractList(int sr, int er);
	void registerContract(List<ContractBean> contractList);
	void registerContractDetailBean(ContractDetailBean contractDetailBean);

	List<EstimateBean> findEstimate();
	
	List<EstimateBean> findEstimateList(int sr, int er);
	void registerEstimate(List<EstimateBean> estimateList);
	void registerEstimateDetailBean(EstimateDetailBean estimateDetailBean);
	List<EstimateDetailBean> findEstimateDetailListal(String searchCode);
	List<EstimateDetailBean> findEstimateDetailList(int sr, int er, String searchCode);
	
	public void estimateModify(EstimateBean estimateBean);
	
	public List<EstimateItemBean> findEstimateReviewList(String startDate, String endDate);
	public List<ContractItemBean> findContractReviewList(String startDate, String endDate);
	void standByModify(EstimateBean estimateBean);
	void standByContractModify(ContractBean contractBean);
	public ArrayList<EstimateDetailBean> getEstimateDetailList(String estimateNo);
	
}

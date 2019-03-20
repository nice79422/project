package com.test.logistics.business.sf;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.logistics.business.applicationservice.BusinessApplicationService;
import com.test.logistics.business.to.ClientBean;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.business.to.EstimateReportBean;

public class BusinessServiceFacadeImpl implements BusinessServiceFacade {
	
	
	BusinessApplicationService businessApplicationService;

	
	
	
	public void setBusinessApplicationService(BusinessApplicationService businessApplicationService) {
		this.businessApplicationService = businessApplicationService;
	}

	public int getRowCount() {
		return businessApplicationService.getRowCount();
	}
	
	
	public int getRowCount(String business) {
		return businessApplicationService.getRowCount(business);
	}

	public int getRowCount(String business, String searchCode) {
		return businessApplicationService.getRowCount(business, searchCode);
			 
	}

	public List<ContractDetailBean> findContractDetailList(int sr, int er, String searchCode) {
		return  businessApplicationService.findContractDetailList(sr, er, searchCode);
	}

	

	@Override
	public void registerClient(ClientBean clientBean) {
		businessApplicationService.registerClient(clientBean);
	}

	@Override
	public List<ClientBean> clientList(int sr, int er) throws DataAccessException {
		return businessApplicationService.clientList(sr, er);
				 
		}

	public List<ContractBean> findContractList(int sr, int er) {
		return businessApplicationService.findContractList(sr, er);
	}

	public void registerContract(List<ContractBean> contractList) {
		businessApplicationService.registerContract(contractList);
	}

	public void registerContractDetailBean(ContractDetailBean contractDetailBean){
		businessApplicationService.registerContractDetailBean(contractDetailBean);
	}

	public List<EstimateBean> findEstimateList(int sr, int er) {
		return businessApplicationService.findEstimateList(sr, er);
	}

	public List<EstimateBean> findEstimate() {
		return businessApplicationService.findEstimate();
	}
	

	
	public void registerEstimate(List<EstimateBean> estimateList) {
		businessApplicationService.registerEstimate(estimateList);
	}

	public void registerEstimateDetailBean(EstimateDetailBean estimateDetailBean){
		businessApplicationService.registerEstimateDetailBean(estimateDetailBean);
	}

	public List<EstimateDetailBean> findEstimateDetailList(int sr, int er, String searchCode) {
		return  businessApplicationService.findEstimateDetailList(sr, er, searchCode);
	}

	public void estimateModify(EstimateBean estimateBean) {
		businessApplicationService.estimateModify(estimateBean);
	}
	
	public List<EstimateReportBean> findEstimateReport(String estimateCode){
		return businessApplicationService.findEstimateReport(estimateCode);
	}

	@Override
	public List<EstimateItemBean> findEstimateReviewList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return businessApplicationService.findEstimateReviewList(startDate, endDate);
	}

	@Override
	public List<ContractItemBean> findContractReviewList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return businessApplicationService.findContractReviewList(startDate, endDate);
	}

	@Override
	public void standByModify(EstimateBean estimateBean) {
		// TODO Auto-generated method stub
		businessApplicationService.standByModify(estimateBean);
	}

	@Override
	public void standByContractModify(ContractBean contractBean) {
		// TODO Auto-generated method stub
		businessApplicationService.standByContractModify(contractBean);
	}
	
	
}

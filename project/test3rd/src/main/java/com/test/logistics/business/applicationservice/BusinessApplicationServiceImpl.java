
package com.test.logistics.business.applicationservice;

import java.util.List;

import com.test.base.applicationservice.BaseApplicationService;
import com.test.common.exception.DataAccessException;
import com.test.logistics.business.dao.ClientDAO;
import com.test.logistics.business.dao.ContractDAO;
import com.test.logistics.business.dao.EstimateDAO;
import com.test.logistics.business.to.ClientBean;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.business.to.EstimateReportBean;
import com.test.logistics.item.applicationservice.ItemApplicationService;


	
	public class BusinessApplicationServiceImpl implements BusinessApplicationService {
		
		private ClientDAO clientDAO;
		private ContractDAO contractDAO;
		
		private EstimateDAO estimateDAO;
		
		BaseApplicationService baseApplicationService;
		ItemApplicationService itemApplicationService;
		
		public void setClientDAO(ClientDAO clientDAO) {
			this.clientDAO = clientDAO;
		}

		public void setContractDAO(ContractDAO contractDAO) {
			this.contractDAO = contractDAO;
		}


		public void setEstimateDAO(EstimateDAO estimateDAO) {
			this.estimateDAO = estimateDAO;
		}

		

		public void setBaseApplicationService(BaseApplicationService baseApplicationService) {
			this.baseApplicationService = baseApplicationService;
		}

		public void setItemApplicationService(ItemApplicationService itemApplicationService) {
			this.itemApplicationService = itemApplicationService;
		};
		
		
		public int getRowCount() {
			return  estimateDAO.selectRowCount();
		}
		
		
		public int getRowCount(String business) {
			int rowCount = 0;

			if (business == "customer")
				rowCount = clientDAO.selectRowCount();
			else if (business == "contract")
				rowCount = contractDAO.selectRowCount();
			else if (business == "estimate")
				rowCount = estimateDAO.selectRowCount();
			return rowCount;
		}

		public int getRowCount(String business, String searchCode) {
			int rowCount = 0;

			if (business == "contractDetailList")
				rowCount = contractDAO.selectSearchRowCount(searchCode);
			else if (business == "estimateDetailList")
				rowCount = estimateDAO.selectEstimateDetailRowCount(searchCode);
			return rowCount;
		}
		
		

		public List<ContractDetailBean> findContractDetailList(int sr, int er, String searchCode) {
			return contractDAO.selectContractDetailLists(sr, er, searchCode);
			
		}
		

		@Override
		public void registerClient(ClientBean clientBean) {
			clientDAO.insertClient(clientBean);
		}

		@Override
		public List<ClientBean> clientList(int sr, int er) throws DataAccessException {
			List<ClientBean> bean = clientDAO.selectClientList(sr, er);
			return bean;
		}

		public List<ContractBean> findContractList(int sr, int er) {
			List<ContractBean> contractList = contractDAO.selectContractList(sr, er);
			return contractList;
		}

		public void registerContract(List<ContractBean> contractList) {
			for (ContractBean contractBean : contractList) {
				contractDAO.insertContract(contractBean);
				for (ContractDetailBean ContractDetailBean : contractBean.getContractDetailList()) {
					//itemApplicationService.updateShippingStock(ContractDetailBean);
					registerContractDetailBean(ContractDetailBean);
				}
			}
		}

		public void registerContractDetailBean(ContractDetailBean contractDetailBean) {
			contractDAO.insertContractDetail(contractDetailBean);
		}

		public List<EstimateBean> findEstimateList(int sr, int er) {
			List<EstimateBean> estimateList = estimateDAO.selectEstimateList(sr, er);
			return estimateList;
		}
		
		public List<EstimateBean> findEstimate() {
			List<EstimateBean> estimate = estimateDAO.selectEstimate();
			
			for (EstimateBean estimateBean : estimate) {
				estimateBean.setEstimateDetailList(estimateDAO.selectEstimateDetail(estimateBean.getEstimateCode()));
	            
	       }
			
			return estimate;
		}
		
		
		
		
		public void registerEstimate(List<EstimateBean> estimateList) {
		for (EstimateBean estimateBean : estimateList) {
			String clientCode = estimateBean.getClientCode();
				if (estimateBean.getStatus().equals("INSERT")) {
					estimateDAO.insertEstimate(estimateBean, clientCode);
					for (EstimateDetailBean estimateDetailBean : estimateBean.getEstimateDetailList()) {
						registerEstimateDetailBean(estimateDetailBean);
					}
				} else if (estimateBean.getStatus().equals("UPDATE"))
					estimateDAO.updateEstimate(estimateBean);
			}	
		}

		public void registerEstimateDetailBean(EstimateDetailBean estimateDetailBean) {
			estimateDAO.insertEstimateDetail(estimateDetailBean);
		}

		public List<EstimateDetailBean> findEstimateDetailList(int sr, int er, String searchCode) {
			return  estimateDAO.selectEstimateDetailList(sr, er, searchCode);
		}
		
		public void estimateModify(EstimateBean estimateBean){
			estimateDAO.updateEstimate(estimateBean);
		}
		public List<EstimateReportBean> findEstimateReport(String estimateCode){
			return estimateDAO.selectEstimateReport(estimateCode);
		}

		@Override
		public List<EstimateItemBean> findEstimateReviewList(String startDate, String endDate) {
			// TODO Auto-generated method stub
			return estimateDAO.selectEstimateReviewList(startDate, endDate);
		}

		@Override
		public List<ContractItemBean> findContractReviewList(String startDate, String endDate) {
			// TODO Auto-generated method stub
			return contractDAO.selectContractReviewList(startDate, endDate);
		}

		@Override
		public void standByModify(EstimateBean estimateBean) {
			// TODO Auto-generated method stub
			estimateDAO.updateStandByEstimate(estimateBean);
		}

		@Override
		public void standByContractModify(ContractBean contractBean) {
			// TODO Auto-generated method stub
			contractDAO.updateStandByContract(contractBean);
		}
		
}

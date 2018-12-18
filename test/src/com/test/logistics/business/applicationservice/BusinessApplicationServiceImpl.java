
package com.test.logistics.business.applicationservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.applicationservice.CommonApplicationService;
import com.test.common.applicationservice.CommonApplicationServiceImpl;
import com.test.common.dao.DataAccessException;
import com.test.logistics.business.dao.ClientDAO;
import com.test.logistics.business.dao.ClientDAOImpl;
import com.test.logistics.business.dao.ContractDAO;
import com.test.logistics.business.dao.ContractDAOImpl;
import com.test.logistics.business.dao.ContractDetailDAO;
import com.test.logistics.business.dao.ContractDetailDAOImpl;
import com.test.logistics.business.dao.EstimateDAO;
import com.test.logistics.business.dao.EstimateDAOImpl;
import com.test.logistics.business.dao.EstimateDetailDAO;
import com.test.logistics.business.dao.EstimateDetailDAOImpl;
import com.test.logistics.business.to.ClientBean;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.item.applicationservice.ItemApplicationService;
import com.test.logistics.item.applicationservice.ItemApplicationServiceImpl;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class BusinessApplicationServiceImpl implements BusinessApplicationService {
	protected final Log logger = LogFactory.getLog(getClass());
	int rowCount;
	private static BusinessApplicationService instance;

	private BusinessApplicationServiceImpl() {
	}

	private ClientDAO clientDAO = ClientDAOImpl.getInstance();
	private ContractDAO contractDAO = ContractDAOImpl.getInstance();
	private ContractDetailDAO ContractDetailDAO = ContractDetailDAOImpl.getInstance();
	private EstimateDAO estimateDAO = EstimateDAOImpl.getInstance();
	private EstimateDetailDAO estimateDetailDAO = EstimateDetailDAOImpl.getInstance();
	CommonApplicationService commonApplicationService = CommonApplicationServiceImpl.getInstance();
	ItemApplicationService itemApplicationService = ItemApplicationServiceImpl.getInstance();

	public static BusinessApplicationService getInstance() {
		if (instance == null)
			instance = new BusinessApplicationServiceImpl();
		return instance;
	}

	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-����");
		}
		rowCount = 0;
		rowCount = estimateDAO.selectRowCount();
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-��");
		}

		return rowCount;
	}
	
	
	public int getRowCount(String business) {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-����");
		}
		rowCount = 0;

		if (business == "customer")
			rowCount = clientDAO.selectRowCount();
		else if (business == "contract")
			rowCount = contractDAO.selectRowCount();
		else if (business == "estimate")
			rowCount = estimateDAO.selectRowCount();
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-��");
		}

		return rowCount;
	}

	public int getRowCount(String business, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-����");
		}
		rowCount = 0;

		if (business == "contractDetailList")
			rowCount = ContractDetailDAO.selectRowCount(searchCode);
		else if (business == "estimateDetailList")
			rowCount = estimateDetailDAO.selectRowCount(searchCode);

		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-��");
		}

		return rowCount;
	}
	
	

	public List<ContractDetailBean> findContractDetailList(int sr, int er, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findContractDetailList()-����");
		}

		List<ContractDetailBean> orderList = null;

		orderList = ContractDetailDAO.selectContractDetailLists(sr, er, searchCode);

		if (logger.isDebugEnabled()) {
			logger.debug("findContractDetailList()-��");
		}

		return orderList;
	}
	
	

	@Override
	public void registerClient(ClientBean clientBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerClient() - ����");
		}

		clientDAO.insertClient(clientBean);

		if (logger.isDebugEnabled()) {
			logger.debug("registerClient() - ��");
		}

	}

	@Override
	public List<ClientBean> clientList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("ClientList() - ����");
		}
		List<ClientBean> bean = ClientDAOImpl.getInstance().selectClientList(sr, er);

		if (logger.isDebugEnabled()) {
			logger.debug("ClientList() - ��");
		}
		return bean;
	}

	public List<ContractBean> findContractList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("findContractList()-����");
		}

		List<ContractBean> contractList = null;

		contractList = contractDAO.selectContractList(sr, er);

		if (logger.isDebugEnabled()) {
			logger.debug("findContractList()-��");
		}

		return contractList;
	}

	public void registerContract(List<ContractBean> contractList) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerContract()-����");
		}

		
		for (ContractBean contractBean : contractList) {
			
			
			contractDAO.insertContract(contractBean);
			for (ContractDetailBean ContractDetailBean : contractBean.getContractDetailList()) {
				//itemApplicationService.updateShippingStock(ContractDetailBean);
				registerContractDetailBean(ContractDetailBean);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("registerContract()-��");
		}

	}

	public void registerContractDetailBean(ContractDetailBean contractDetailBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerContractDetailBean()-����");
		}

		ContractDetailDAO.insertContractDetail(contractDetailBean);

		if (logger.isDebugEnabled()) {
			logger.debug("registerContractDetailBean()-��");
		}

	}

	public List<EstimateBean> findEstimateList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEstimateList()-����");
		}

		List<EstimateBean> estimateList = null;

		estimateList = estimateDAO.selectEstimateList(sr, er);

		if (logger.isDebugEnabled()) {
			logger.debug("findContractList()-��");
		}

		return estimateList;
	}
	
	
	
	

	public List<EstimateBean> findEstimate() {
		if (logger.isDebugEnabled()) {
			logger.debug("findEstimate()-����");
		}

		List<EstimateBean> estimate = estimateDAO.selectEstimate();
		for (EstimateBean estimateBean : estimate) {
            estimateBean.setEstimateDetailList(estimateDAO.selectEstimateDetail(estimateBean.getEstimateCode()));
        }
		if (logger.isDebugEnabled()) {
			logger.debug("findContractList()-��");
		}
		return estimate;
	}
	
	
	
	
	public void registerEstimate(List<EstimateBean> estimateList) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerEstimate()-����");
		}

		
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
		if (logger.isDebugEnabled()) {
			logger.debug("registerEstimate()-��");
		}

	}

	public void registerEstimateDetailBean(EstimateDetailBean estimateDetailBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerestimateDetailBean()-����");
		}

		estimateDetailDAO.insertEstimateDetail(estimateDetailBean);

		if (logger.isDebugEnabled()) {
			logger.debug("registerestimateDetailBean()-��");
		}

	}

	public List<EstimateDetailBean> findEstimateDetailList(int sr, int er, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findestimateDetailList()-����");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findestimateDetailList()-��");
		}

		return  estimateDetailDAO.selectEstimateDetailLists(sr, er, searchCode);
	}
	
	public void estimateModify(EstimateBean estimateBean){
		if (logger.isDebugEnabled()) {logger.debug("mpsModify() - start");}

			
				estimateDAO.updateEstimate(estimateBean);
			

			if (logger.isDebugEnabled()) {logger.debug("mpsModify() - end");}

	}

	public List<EstimateItemBean> findEstimateReviewList(String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("END");
		}

		return estimateDAO.selectEstimateReviewList(startDate, endDate);
	}
	
	public List<ContractItemBean> findContractReviewList(String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("END");
		}
		return contractDAO.selectContractReviewList(startDate, endDate);
	}

	@Override
	public void standByModify(EstimateBean estimateBean) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {logger.debug("standByModify() - start");}

		estimateDAO.updateStandByEstimate(estimateBean);

	if (logger.isDebugEnabled()) {logger.debug("standByModify() - end");}

	}

	@Override
	public void standByContractModify(ContractBean contractBean) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {logger.debug("standByModify() - start");}

		estimateDAO.updateStandByContract(contractBean);

	if (logger.isDebugEnabled()) {logger.debug("standByModify() - end");}
	}

	public ArrayList<EstimateDetailBean> getEstimateDetailList(String estimateNo) {

	      if (logger.isDebugEnabled()) {
	         logger.debug("EstimateApplicationServiceImpl : getEstimateDetailList ����");
	      }

	      ArrayList<EstimateDetailBean> estimateDetailTOList = null;

	      try {

	         estimateDetailTOList = estimateDetailDAO.selectEstimateDetailList(estimateNo);

	      } catch (DataAccessException e) {
	         logger.error(e.getMessage());
	         throw e;
	      }

	      if (logger.isDebugEnabled()) {
	         logger.debug("EstimateApplicationServiceImpl : getEstimateDetailList ����");
	      }
	      return estimateDetailTOList;

	   }
	
	public List<EstimateDetailBean> findEstimateDetailListal(String searchCode){ //���ֿ��� ���� ������ 
		if (logger.isDebugEnabled()) {
			logger.debug("findestimateDetailList()-����");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findestimateDetailList()-��");
		}

		return  estimateDAO.selectEstimateDetail(searchCode);
				
	}
	
	
}

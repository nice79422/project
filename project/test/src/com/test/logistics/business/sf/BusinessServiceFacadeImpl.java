package com.test.logistics.business.sf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.business.applicationservice.BusinessApplicationService;
import com.test.logistics.business.applicationservice.BusinessApplicationServiceImpl;
import com.test.logistics.business.to.ClientBean;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class BusinessServiceFacadeImpl implements BusinessServiceFacade {
	protected final Log logger = LogFactory.getLog(getClass());
	DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	int rowCount;
	private static BusinessServiceFacade instance;
	BusinessApplicationService businessApplicationService = BusinessApplicationServiceImpl.getInstance();


	public static BusinessServiceFacade getInstance() {
		if (instance == null)
			instance = new BusinessServiceFacadeImpl();
		return instance;
	}

	
	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-����");
		}
		rowCount = 0;
		dataSourceTransactionManager.beginTransaction();
		try {
			rowCount = businessApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getRowCount()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return rowCount;
	}
	
	
	public int getRowCount(String business) {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-����");
		}
		rowCount = 0;
		dataSourceTransactionManager.beginTransaction();
		try {
			rowCount = businessApplicationService.getRowCount(business);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getRowCount()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return rowCount;
	}

	public int getRowCount(String business, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-����");
		}
		rowCount = 0;
		dataSourceTransactionManager.beginTransaction();
		try {
			rowCount=businessApplicationService.getRowCount(business, searchCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getRowCount()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return rowCount;
	}

	public List<ContractDetailBean> findContractDetailList(int sr, int er, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findContractDetailList()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		List<ContractDetailBean> orderList = null;
		try {
			orderList = businessApplicationService.findContractDetailList(sr, er, searchCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findContractDetailList()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findContractDetailList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return orderList;
	}

	

	@Override
	public void registerClient(ClientBean clientBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerClient() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			businessApplicationService.registerClient(clientBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("registerClient() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("registerClient()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public List<ClientBean> clientList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("ClientList() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<ClientBean> bean = businessApplicationService.clientList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("ClientList() - ��");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("ClientList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<ContractBean> findContractList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("findContractList()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		List<ContractBean> contractList = null;
		try {
			contractList = businessApplicationService.findContractList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findContractList()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findContractList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return contractList;
	}

	public void registerContract(List<ContractBean> contractList) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerContract()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			

			businessApplicationService.registerContract(contractList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("registerContract()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("registerContract()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public void registerContractDetailBean(ContractDetailBean contractDetailBean){
		if (logger.isDebugEnabled()) { logger.debug("registerContractDetailBean()-����");}
		dataSourceTransactionManager.beginTransaction();
		try{
			businessApplicationService.registerContractDetailBean(contractDetailBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) { logger.debug("registerContractDetailBean()-��");}
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerContractDetailBean()-����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<EstimateBean> findEstimateList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEstimateList()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		List<EstimateBean> estimateList = null;
		try {
			estimateList = businessApplicationService.findEstimateList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findContractList()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findContractList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return estimateList;
	}

	public List<EstimateBean> findEstimate() {
		if (logger.isDebugEnabled()) {
			logger.debug("findEstimate()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		List<EstimateBean> estimate = null;
		try {
			estimate = businessApplicationService.findEstimate();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findContract()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findContract()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return estimate;
	}
	

	
	public void registerEstimate(List<EstimateBean> estimateList) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerEstimate()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
		
			businessApplicationService.registerEstimate(estimateList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("registerEstimate()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("registerEstimate()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public void registerEstimateDetailBean(EstimateDetailBean estimateDetailBean){
		if (logger.isDebugEnabled()) { logger.debug("registerEstimateDetailBean()-����");}
		dataSourceTransactionManager.beginTransaction();
		try{                          
			businessApplicationService.registerEstimateDetailBean(estimateDetailBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) { logger.debug("registerEstimateDetailBean()-��");}
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerEstimateDetailBean()-����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	
	public List<EstimateDetailBean> findEstimateDetailList(int sr, int er, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEstimateDetailList()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		List<EstimateDetailBean> EstimateDetailList = null;
		try {
			EstimateDetailList = businessApplicationService.findEstimateDetailList(sr, er, searchCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findEstimateDetailList()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findEstimateDetailList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return EstimateDetailList;
	}

	public void estimateModify(EstimateBean estimateBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("EstimateModify() - start");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			businessApplicationService.estimateModify(estimateBean);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("EstimateModify() - end");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	public List<EstimateItemBean> findEstimateReviewList(String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}

		dataSourceTransactionManager.beginTransaction();
		List<EstimateItemBean> estimateItemList = null;
		try {
			estimateItemList = businessApplicationService.findEstimateReviewList(startDate, endDate);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("END");
		}
		return estimateItemList;
	}
	
	public List<ContractItemBean> findContractReviewList(String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}

		dataSourceTransactionManager.beginTransaction();
		List<ContractItemBean> contractItemList = null;
		try {
			contractItemList = businessApplicationService.findContractReviewList(startDate, endDate);
			dataSourceTransactionManager.commitTransaction();
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("END");
		}
		return contractItemList;
	}


	@Override
	public void standByModify(EstimateBean estimateBean) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("StandByModifyModify() - start");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			businessApplicationService.standByModify(estimateBean);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("StandByModifyModify() - end");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}


	@Override
	public void standByContractModify(ContractBean contractBean) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("StandByModifyModify() - start");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			businessApplicationService.standByContractModify(contractBean);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("StandByModifyModify() - end");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public ArrayList<EstimateDetailBean> getEstimateDetailList(String estimateNo) {

	      if (logger.isDebugEnabled()) {
	         logger.debug("SalesServiceFacadeImpl : getEstimateDetailList ����");
	      }

	      dataSourceTransactionManager.beginTransaction();
	      ArrayList<EstimateDetailBean> estimateDetailTOList = null;

	      try {

	         estimateDetailTOList = businessApplicationService.getEstimateDetailList(estimateNo);
	         dataSourceTransactionManager.commitTransaction();

	      } catch (DataAccessException e) {
	         e.printStackTrace();
	         logger.error(e.getMessage());
	         dataSourceTransactionManager.rollbackTransaction();
	         throw e;
	      }

	      if (logger.isDebugEnabled()) {
	         logger.debug("SalesServiceFacadeImpl : getEstimateDetailList ����");
	      }
	      return estimateDetailTOList;
	   }
	public List<EstimateDetailBean> findEstimateDetailListal(int sr, int er, String searchCode) {  //�������� ���� �󼼹޾ƿ��� 
		if (logger.isDebugEnabled()) {
			logger.debug("findEstimateDetailList()-����");
		}	System.out.println("���ֿ��� �������� �޾ƿ��� 111");
		dataSourceTransactionManager.beginTransaction();
		List<EstimateDetailBean> EstimateDetailList = null;
		try {
			EstimateDetailList = businessApplicationService.findEstimateDetailListal( searchCode);  //�ֹ� ����Ʈ 
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findEstimateDetailList()-��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findEstimateDetailList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		return EstimateDetailList;
	}
	
}

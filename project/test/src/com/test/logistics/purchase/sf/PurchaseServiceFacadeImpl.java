package com.test.logistics.purchase.sf;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.applicationservice.PurchaseApplicationService;
import com.test.logistics.purchase.applicationservice.PurchaseApplicationServiceImpl;
import com.test.logistics.purchase.to.PurchaseBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class PurchaseServiceFacadeImpl  implements PurchaseServiceFacade {
	protected final Log logger = LogFactory.getLog(getClass());
	DataSourceTransactionManager dataSourceTransactionManager=DataSourceTransactionManager.getInstance();
	int rowCount;
	private PurchaseServiceFacadeImpl(){}
	private static PurchaseServiceFacade instance;
	public static PurchaseServiceFacade getInstance() {
		if(instance ==null)
			instance= new PurchaseServiceFacadeImpl();
		return instance;
	}
	PurchaseApplicationService purchaseApplicationService = PurchaseApplicationServiceImpl.getInstance();

	
	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			int count = purchaseApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getRowCount() - 끝");
			}
			return count;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public int getPurchaseRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getPurchaseRowCount() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			int count = purchaseApplicationService.getPurchaseRowCount();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getPurchaseRowCount() - 끝");
			}
			return count;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getPurchaseRowCount()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	
	@Override
	public List<PurchasingPlaceBean> purchasingPlaceList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("PurchasingPlaceList() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<PurchasingPlaceBean> bean = purchaseApplicationService.purchasingPlaceList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("PurchasingPlaceList() - 끝");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("PurchasingPlaceList()-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public List<PurchaseBean> findPurchaseList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("findPurchaseList() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<PurchaseBean> bean = purchaseApplicationService.purchaseList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("findPurchaseList() - 끝");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findPurchaseList()-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	
	
	
	@Override
	public void registerPurchasingPlace(PurchasingPlaceBean purchasingPlaceBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerPurchasingPlace() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			purchaseApplicationService.registerPurchasingPlace(purchasingPlaceBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("registerPurchasingPlace() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("registerPurchasingPlace()-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	
	
	public int getPurchaseOrderRowCount(String code){
		if (logger.isDebugEnabled()) {logger.debug("getPurchaseOrderRowCount - start");}
		dataSourceTransactionManager.beginTransaction();
		try {
			int count=purchaseApplicationService.getPurchaseOrderRowCount(code);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {logger.debug("getPurchaseOrderRowCount - end");}
			return count;

		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<MrpBean> findPurchaseOrder(int sr, int er, String code){
		if (logger.isDebugEnabled()) {logger.debug("findPurchaseOrder - start");}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<MrpBean> mrpBean=purchaseApplicationService.findPurchaseOrder(sr,er,code);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {logger.debug("findPurchaseOrder - end");}
			return mrpBean;
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public void purchaseCrud(List<MrpBean> mrpList){
		if (logger.isDebugEnabled()) {logger.debug("purchaseCrud() - start");}
		dataSourceTransactionManager.beginTransaction();
		try {
			
			purchaseApplicationService.purchaseCrud(mrpList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {logger.debug("purchaseCrud() - end");}
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}




}

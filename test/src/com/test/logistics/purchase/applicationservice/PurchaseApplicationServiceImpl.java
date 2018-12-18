package com.test.logistics.purchase.applicationservice;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.logistics.product.dao.MrpDAO;
import com.test.logistics.product.dao.MrpDAOImpl;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.dao.PurchaseDAO;
import com.test.logistics.purchase.dao.PurchaseDAOImpl;
import com.test.logistics.purchase.dao.PurchasingPlaceDAO;
import com.test.logistics.purchase.dao.PurchasingPlaceDAOImpl;
import com.test.logistics.purchase.to.PurchaseBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;




public class PurchaseApplicationServiceImpl implements PurchaseApplicationService {
	protected final Log logger = LogFactory.getLog(getClass());
	int rowCount;
	private PurchaseApplicationServiceImpl() {
	}
	private PurchasingPlaceDAO purchasingPlaceDAO = PurchasingPlaceDAOImpl.getInstance();
	
	
	
	private static PurchaseApplicationService instance;

	public static PurchaseApplicationService getInstance() {
		if (instance == null)
			instance = new PurchaseApplicationServiceImpl();
		return instance;
	}

	private MrpDAO mrpDAO = MrpDAOImpl.getInstance();
	private PurchaseDAO purchaseDAO = PurchaseDAOImpl.getInstance();

	public int getPurchaseOrderRowCount(String code){
		if (logger.isDebugEnabled()) {logger.debug("getPurchaseOrderRowCount - start");}
			int count=mrpDAO.selectPurchaseOrderRowCount(code);
			if (logger.isDebugEnabled()) {logger.debug("getPurchaseOrderRowCount - end");}
			return count;
	}
   @Override
	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-시작");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-끝");
		}

		return purchasingPlaceDAO.selectRowCount();
	}
   
   @Override
	public int getPurchaseRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getPurchaseRowCount()-시작");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getPurchaseRowCount()-끝");
		}

		return purchaseDAO.selectRowCount();
	}
   
   @Override
	public List<PurchasingPlaceBean> purchasingPlaceList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("PurchaingPlaceList() - 시작");
		}
		List<PurchasingPlaceBean> bean = purchasingPlaceDAO.selectPurchasingPlaceList(sr, er);

		if (logger.isDebugEnabled()) {
			logger.debug("PurchaingPlaceList() - 끝");
		}
		return bean;
	}

   @Override
	public List<PurchaseBean> purchaseList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("purchaseList() - 시작");
		}
		List<PurchaseBean> bean = purchaseDAO.selectPurchaseList(sr, er);

		if (logger.isDebugEnabled()) {
			logger.debug("purchaseList() - 끝");
		}
		return bean;
	}
   
   
   
 
	
	
	
	
	public List<MrpBean> findPurchaseOrder(int sr, int er, String code){
		if (logger.isDebugEnabled()) {logger.debug("findPurchaseOrder - start");}

			List<MrpBean> mrpBean=mrpDAO.selectPurchaseOrder(sr,er,code);

			if (logger.isDebugEnabled()) {logger.debug("findPurchaseOrder - end");}
			return mrpBean;

	}

	public void purchaseCrud(List<MrpBean> mrpList){
		if (logger.isDebugEnabled()) {logger.debug("purchaseCrud() - start");}
			
			for (MrpBean mrpbean : mrpList) {
				purchaseDAO.insertPurchase(mrpbean);
				mrpDAO.updateMrp(mrpbean);
			}

			if (logger.isDebugEnabled()) {logger.debug("purchaseCrud() - end");}

	}

	@Override
	public void registerPurchasingPlace(PurchasingPlaceBean purchasingPlaceBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerPurchasingPlace() - 시작");
		}

		purchasingPlaceDAO.insertPurchasingPlace(purchasingPlaceBean);

		if (logger.isDebugEnabled()) {
			logger.debug("registerPurchasingPlace() - 끝");
		}

	}

	

}

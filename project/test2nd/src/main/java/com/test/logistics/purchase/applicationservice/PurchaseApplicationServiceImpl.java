package com.test.logistics.purchase.applicationservice;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.product.dao.MrpDAO;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.dao.PurchaseDAO;
import com.test.logistics.purchase.dao.PurchasingPlaceDAO;
import com.test.logistics.purchase.to.PurchaseBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;




public class PurchaseApplicationServiceImpl implements PurchaseApplicationService {
	
	private PurchasingPlaceDAO purchasingPlaceDAO;
	private MrpDAO mrpDAO;
	private PurchaseDAO purchaseDAO;
	

	public void setPurchasingPlaceDAO(PurchasingPlaceDAO purchasingPlaceDAO) {
		this.purchasingPlaceDAO = purchasingPlaceDAO;
	}
	public void setMrpDAO(MrpDAO mrpDAO) {
		this.mrpDAO = mrpDAO;
	}
	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}
	public int getPurchaseOrderRowCount(String code){
			return mrpDAO.selectPurchaseOrderRowCount(code);
	}
   @Override
	public int getRowCount() {

		return purchasingPlaceDAO.selectRowCount();
	}
   
   @Override
	public int getPurchaseRowCount() {

		return purchaseDAO.selectRowCount();
	}
   
   @Override
	public List<PurchasingPlaceBean> purchasingPlaceList(int sr, int er) throws DataAccessException {
		List<PurchasingPlaceBean> bean = purchasingPlaceDAO.selectPurchasingPlaceList(sr, er);
		return bean;
	}

   @Override
	public List<PurchaseBean> purchaseList(int sr, int er) throws DataAccessException {
		List<PurchaseBean> bean = purchaseDAO.selectPurchaseList(sr, er);
		return bean;
	}
   
   
   
 
	
	
	
	
	public List<MrpBean> findPurchaseOrder(int sr, int er, String code){
			List<MrpBean> mrpBean=mrpDAO.selectPurchaseOrder(sr,er,code);
			return mrpBean;

	}

	public void purchaseCrud(List<MrpBean> mrpList){
			for (MrpBean mrpbean : mrpList) {
				purchaseDAO.insertPurchase(mrpbean);
				mrpDAO.updateMrp(mrpbean);
			}
	}

	@Override
	public void registerPurchasingPlace(PurchasingPlaceBean purchasingPlaceBean) {
		purchasingPlaceDAO.insertPurchasingPlace(purchasingPlaceBean);
	}
}

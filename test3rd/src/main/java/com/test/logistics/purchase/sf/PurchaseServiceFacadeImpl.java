package com.test.logistics.purchase.sf;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.applicationservice.PurchaseApplicationService;
import com.test.logistics.purchase.to.PurchaseBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class PurchaseServiceFacadeImpl  implements PurchaseServiceFacade {
	
	PurchaseApplicationService purchaseApplicationService;

	public void setPurchaseApplicationService(PurchaseApplicationService purchaseApplicationService) {
		this.purchaseApplicationService = purchaseApplicationService;
	}

	
	public int getRowCount() {
			return purchaseApplicationService.getRowCount();
	}
	
	@Override
	public int getPurchaseRowCount() {
		return purchaseApplicationService.getPurchaseRowCount();
	}
	
	
	@Override
	public List<PurchasingPlaceBean> purchasingPlaceList(int sr, int er) throws DataAccessException {
		return purchaseApplicationService.purchasingPlaceList(sr, er);
	}
	
	@Override
	public List<PurchaseBean> findPurchaseList(int sr, int er) throws DataAccessException {
		return purchaseApplicationService.purchaseList(sr, er);
	}
	
	
	
	
	@Override
	public void registerPurchasingPlace(PurchasingPlaceBean purchasingPlaceBean) {
			purchaseApplicationService.registerPurchasingPlace(purchasingPlaceBean);
	}
	
	
	
	public int getPurchaseOrderRowCount(String code){
		return purchaseApplicationService.getPurchaseOrderRowCount(code);
	}

	public List<MrpBean> findPurchaseOrder(int sr, int er, String code){
		return purchaseApplicationService.findPurchaseOrder(sr,er,code);
	}

	public void purchaseCrud(List<MrpBean> mrpList){
			purchaseApplicationService.purchaseCrud(mrpList);
	}

}

package com.test.logistics.purchase.sf;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.to.PurchaseBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public interface PurchaseServiceFacade {
	 int getPurchaseOrderRowCount(String code);
	 int getPurchaseRowCount();
	 List<MrpBean> findPurchaseOrder(int sr, int er, String code);
	 void purchaseCrud(List<MrpBean> mrpList);
	int getRowCount();
	List<PurchasingPlaceBean> purchasingPlaceList(int sr, int er) throws DataAccessException;
	void registerPurchasingPlace(PurchasingPlaceBean purchasingPlaceBean);
	List<PurchaseBean> findPurchaseList(int sr, int er) throws DataAccessException;
	
}

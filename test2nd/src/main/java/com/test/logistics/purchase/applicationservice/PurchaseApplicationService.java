package com.test.logistics.purchase.applicationservice;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.to.PurchaseBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public interface PurchaseApplicationService {
	 int getPurchaseOrderRowCount(String code);
	 List<MrpBean> findPurchaseOrder(int sr, int er, String code);
	 void purchaseCrud(List<MrpBean> mrpList);
	void registerPurchasingPlace(PurchasingPlaceBean purchasingPlaceBean);
	List<PurchasingPlaceBean> purchasingPlaceList(int sr, int er) throws DataAccessException;
	int getRowCount();
	int getPurchaseRowCount();
	List<PurchaseBean> purchaseList(int sr, int er) throws DataAccessException;
}

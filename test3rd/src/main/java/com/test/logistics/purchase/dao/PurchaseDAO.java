package com.test.logistics.purchase.dao;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.to.PurchaseBean;

public interface PurchaseDAO {
	public int selectRowCount();
	public List<PurchaseBean> selectPurchaseList(int sr, int er) throws DataAccessException;
	/*void updatePurchase(PurchaseBean purchaseBean);*/
	void insertPurchase(MrpBean mrpbean);
}
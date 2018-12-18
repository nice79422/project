package com.test.logistics.purchase.dao;

import java.util.List;

import com.test.logistics.purchase.to.PurchasingPlaceBean;

public interface PurchasingPlaceDAO {
	public int selectRowCount();
	public List<PurchasingPlaceBean> selectPurchasingPlaceList(int sr, int er);
	public void insertPurchasingPlace(PurchasingPlaceBean client);
	/*public String selectPurchasingPlace(String name, String regionNo);*/
}


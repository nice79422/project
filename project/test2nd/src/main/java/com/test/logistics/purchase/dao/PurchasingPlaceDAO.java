package com.test.logistics.purchase.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public interface PurchasingPlaceDAO {
	public int selectRowCount();
	public List<PurchasingPlaceBean> selectPurchasingPlaceList(int sr, int er) throws DataAccessException;
	public void insertPurchasingPlace(PurchasingPlaceBean client) throws DataAccessException;
	public String selectPurchasingPlace(String name, String regionNo)  throws DataAccessException;
}


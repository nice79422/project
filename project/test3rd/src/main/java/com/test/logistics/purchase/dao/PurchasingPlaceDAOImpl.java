package com.test.logistics.purchase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class PurchasingPlaceDAOImpl extends IBatisDAO implements PurchasingPlaceDAO {

	
	
	@SuppressWarnings("deprecation")
	public int selectRowCount() {
		return (int) getSqlMapClientTemplate().queryForObject("purchasingPlace.selectRowCount");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<PurchasingPlaceBean> selectPurchasingPlaceList(int sr, int er){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<PurchasingPlaceBean>) getSqlMapClientTemplate().queryForList("purchasingPlace.selectPurchasingPlaceList", map);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertPurchasingPlace(PurchasingPlaceBean purchasingPlace){
		getSqlMapClientTemplate().update("purchasingPlace.insertPurchasingPlace", purchasingPlace);
	}

	
}

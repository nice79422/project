package com.test.logistics.purchase.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.to.PurchaseBean;

public class PurchaseDAOImpl extends IBatisDAO implements PurchaseDAO{
		
		@SuppressWarnings("deprecation")
		public int selectRowCount() {
			return (int) getSqlMapClientTemplate().queryForObject("purchase.selectRowCount");
		}

		@SuppressWarnings("deprecation")
		public void insertPurchase(MrpBean bean) {
			getSqlMapClientTemplate().insert("purchase.insertPurchase", bean);
		}
		
		
		
		@SuppressWarnings({ "unchecked", "deprecation" })	
	    @Override
	    public List<PurchaseBean> selectPurchaseList(int sr, int er){
		    	Map<String, Object> map = new HashMap<>();
				map.put("startRow", sr);
				map.put("endRow", er);
				return (List<PurchaseBean>) getSqlMapClientTemplate().queryForList("purchase.selectPurchaseList", map);
		}
	}
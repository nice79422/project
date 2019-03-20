package com.test.logistics.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.item.to.StockBean;

public class StockDAOImpl extends IBatisDAO implements StockDAO {
	
	@SuppressWarnings("deprecation")
	@Override
	public int selectRowCount() {
		return (int) getSqlMapClientTemplate().queryForObject("stock.selectRowCount");
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<StockBean> dateStockList(String start, String end) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", start);
		map.put("endRow", end);
		return (List<StockBean>) getSqlMapClientTemplate().queryForList("stock.dateStockList", map);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<StockBean> itemStockList(String itemCode) {
		return (List<StockBean>) getSqlMapClientTemplate().queryForList("stock.itemStockList", itemCode);
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public void updateOutputScheduleStock(String planDate, String planQuantity, String itemCode) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("planDate", planDate);
		map.put("planQuantity", planQuantity);
		map.put("itemCode", itemCode);
		getSqlMapClientTemplate().update("stock.updateOutputScheduleStock", map);
	}
}
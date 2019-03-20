package com.test.logistics.item.dao;

import java.util.List;

import com.test.logistics.item.to.StockBean;

public interface StockDAO {
/*	public int selectRowCount();
	public void insertStock(StockBean stock) throws DataAccessException;
	public void deleteStock(StockBean stock);
	public List<StockBean> dateStockList(String start, String end);
	public List<StockBean> itemStockList(String item);
	public StockBean selectStockAmount(String code);
	public void updateInputStock(String inDate, String inAmount, String itemCode);
	public void updateOutputStock(String shippingDate, String shippingAmount, String itemCode);*/
/*	public void updateOutputScheduleStock(StockBean stockBean);*/
	/*public void updateStock(String stockAmount, String itemCode);*/
	
	public int selectRowCount();
	public List<StockBean> dateStockList(String start, String end);
	public List<StockBean> itemStockList(String item);

	public void updateOutputScheduleStock(String planDate, String planQuantity, String itemCode);
}

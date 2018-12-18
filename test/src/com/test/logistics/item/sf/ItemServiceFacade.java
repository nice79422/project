package com.test.logistics.item.sf;

import java.util.List;

import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

public interface ItemServiceFacade {
	public List<ItemBean> getItemList(String code);
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName);
	int getRowCount();
	List<BomBean> findBom(String searchCode);
	public List<StockBean> getDateStockList(String start, String end);
	public List<StockBean> getItemStockList(String item);
	public void modifyItem(List<ItemBean> itemBean);
	public void modifyBom(List<BomBean> bomBean);
	public void modifyStock(List<StockBean> stockBean);
	
}

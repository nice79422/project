package com.test.logistics.item.applicationservice;

import java.util.List;

import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

public interface ItemApplicationService {
	public List<ItemBean> getItemList(String code);
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName);
	int getRowCount();
	/*List<BomBean> findBom(String searchCode);*/
	List<BomBean> findBom(String searchCode, String bom);
	public List<StockBean> getDateStockList(String start, String end);
	public List<StockBean> getItemStockList(String item);
	/*void updateOutputStock(ContractDetailBean contractItemBean);*/
	public void modifyItem(List<ItemBean> itemBean);
/*	public void modifyBom(List<BomBean> bomBean);
	public void modifyStock(List<StockBean> stockBean);*/
}

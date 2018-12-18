package com.test.logistics.item.sf;

import java.util.List;

import com.test.logistics.item.applicationservice.ItemApplicationService;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

public class ItemServiceFacadeImpl implements ItemServiceFacade {
	ItemApplicationService itemApplicationService;


	public void setItemApplicationService(ItemApplicationService itemApplicationService) {
		this.itemApplicationService = itemApplicationService;
	}

	public int getRowCount() {
			return itemApplicationService.getRowCount();
	}

	@Override
	public List<ItemBean> getItemList(String code) {
			return itemApplicationService.getItemList(code);
	}

	@Override
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName) {
		return itemApplicationService.getItemList(sr, er, itemCode, itemName);
	}

/*	public List<BomBean> findBom(String searchCode){
		return itemApplicationService.findBom(searchCode);
	}*/

	@Override
	public List<StockBean> getDateStockList(String start, String end){
		return itemApplicationService.getDateStockList(start, end);
	}

	@Override
	public List<StockBean> getItemStockList(String item){
		return itemApplicationService.getItemStockList(item);
	}

	@Override
	public void modifyItem(List<ItemBean> itemBean) {
		itemApplicationService.modifyItem(itemBean);
	}

	@Override
	public List<BomBean> findBom(String searchCode, String bom) {
		// TODO Auto-generated method stub
		return itemApplicationService.findBom(searchCode, bom);
		
	}
	
/*	@Override
	public void modifyBom(List<BomBean> bomBean) {
		itemApplicationService.modifyBom(bomBean);
	}
	
	@Override
	public void modifyStock(List<StockBean> stockBean) {
		itemApplicationService.modifyStock(stockBean);*/
	}


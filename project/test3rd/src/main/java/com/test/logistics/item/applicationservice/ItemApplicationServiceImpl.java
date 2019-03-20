package com.test.logistics.item.applicationservice;

import java.util.List;

import com.test.logistics.item.dao.ItemDAO;
import com.test.logistics.item.dao.StockDAO;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

public class ItemApplicationServiceImpl implements ItemApplicationService {
	
	private ItemDAO itemDAO;
	
	private StockDAO stockDAO;

	
	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}


	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	public int getRowCount() {
		return itemDAO.selectRowCount();
	}

	@Override
	public List<ItemBean> getItemList(String code) {
		return itemDAO.selectItemList(code);
	}

	@Override
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName) {
		
		
		List<ItemBean> bean = null;
		if (itemCode!=null && itemName!=null){
			bean = itemDAO.selectAllItemList(sr, er, itemCode, itemName);
		}else if (itemName!=null && itemCode.equals("")){
			bean = itemDAO.selectNameItemList(sr, er, "ITEM_NAME/" + itemName);
		}else if (itemCode!=null && itemName.equals("")){
			bean = itemDAO.selectCodeItemList(sr, er, "ITEM_CODE/" + itemCode);
		}else{
			bean = itemDAO.selectSearchItemList(sr, er);
		}
		return bean;
	}

	public List<BomBean> findBom(String searchCode, String bom) {
		return itemDAO.selectBom(searchCode, bom);
	}

	@Override
	public List<StockBean> getDateStockList(String start, String end) {
		return stockDAO.dateStockList(start, end);
	}

	@Override
	public List<StockBean> getItemStockList(String item) {
		return stockDAO.itemStockList(item);
	}

/*	@Override
	public void updateOutputStock(ContractDetailBean contractItemBean) {
		stockDAO.updateOutputScheduleStock(contractItemBean.getSupplyDate(), contractItemBean.getSupplyAmount(), contractItemBean.getItemCode() );
	}*/

	@Override
	public void modifyItem(List<ItemBean> itemBean) {
		for (ItemBean bean : itemBean)
			itemDAO.insertItem(bean);
	}
/*	
	@Override
	public void modifyBom(List<BomBean> bomBean) {
		for (BomBean bean : bomBean)
			bomDAO.insertBom(bean);
	}*/
/*	@Override
	public void modifyStock(List<StockBean> stockBean) {
		for (StockBean bean : stockBean)
			stockDAO.insertStock(bean);
	}*/
}

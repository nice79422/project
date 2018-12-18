package com.test.logistics.item.applicationservice;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.item.dao.BomDAO;
import com.test.logistics.item.dao.BomDAOImpl;
import com.test.logistics.item.dao.ItemDAO;
import com.test.logistics.item.dao.ItemDAOImpl;
import com.test.logistics.item.dao.StockDAO;
import com.test.logistics.item.dao.StockDAOImpl;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

public class ItemApplicationServiceImpl implements ItemApplicationService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static ItemApplicationService instance;
	private ItemDAO itemDAO = ItemDAOImpl.getInstance();
	private BomDAO bomDAO = BomDAOImpl.getInstance();
	private StockDAO stockDAO = StockDAOImpl.getInstance();

	public static ItemApplicationService getInstance() {
		if (instance == null)
			instance = new ItemApplicationServiceImpl();
		return instance;
	}

	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-끝");
		}
		return itemDAO.selectRowCount();
	}

	@Override
	public List<ItemBean> getItemList(String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - 끝");
		}
		return itemDAO.selectItemList(code);
	}

	@Override
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - 시작");
		}

		List<ItemBean> bean = null;

		if (itemCode!=null && itemName!=null){
			
			bean = itemDAO.selectItemList(sr, er, itemCode, itemName);
		}else if (itemCode!=null && itemName.equals("")){
			
			bean = itemDAO.selectItemList(sr, er, "ITEM_NAME/" + itemName);
		}else if (itemCode!=null && itemName.equals("")){
			
			bean = itemDAO.selectItemList(sr, er, "ITEM_CODE/" + itemCode);
		}else{
			
			bean = itemDAO.selectItemList(sr, er);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - 끝");
		}
		return bean;

	}

	public List<BomBean> findBom(String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findBom()-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findBom()-끝");
		}
		return bomDAO.selectBom(searchCode);
	}

	@Override
	public List<StockBean> getDateStockList(String start, String end) {
		if (logger.isDebugEnabled()) {
			logger.debug("getDateStockList - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getDateStockList - 끝");
		}
		return stockDAO.dateStockList(start, end);
	}

	@Override
	public List<StockBean> getItemStockList(String item) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemStockList - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getItemStockList - 끝");
		}

		return stockDAO.itemStockList(item);
	}

	@Override
	public void updateOutputStock(ContractDetailBean contractItemBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemStockList - 시작");
		}

		stockDAO.updateOutputScheduleStock(contractItemBean.getSupplyDate(), contractItemBean.getSupplyAmount(), contractItemBean.getItemCode() );

		if (logger.isDebugEnabled()) {
			logger.debug("getItemStockList - 끝");
		}
	}

	@Override
	public void modifyItem(List<ItemBean> itemBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyItem() - 시작");
		}

		for (ItemBean bean : itemBean)
			itemDAO.insertItem(bean);

		if (logger.isDebugEnabled()) {
			logger.debug("modifyItem() - 끝");
		}

	}
	
	@Override
	public void modifyBom(List<BomBean> bomBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBom() - 시작");
		}

		for (BomBean bean : bomBean)
			bomDAO.insertBom(bean);

		if (logger.isDebugEnabled()) {
			logger.debug("modifyBom() - 끝");
		}

	}
	@Override
	public void modifyStock(List<StockBean> stockBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyStock() - 시작");
		}

		for (StockBean bean : stockBean)
			stockDAO.insertStock(bean);

		if (logger.isDebugEnabled()) {
			logger.debug("modifyStock() - 끝");
		}

	}
	
	
}

package com.test.logistics.item.sf;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.item.applicationservice.ItemApplicationService;
import com.test.logistics.item.applicationservice.ItemApplicationServiceImpl;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

public class ItemServiceFacadeImpl implements ItemServiceFacade {
	protected final Log logger = LogFactory.getLog(getClass());
	DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	private static ItemServiceFacade instance;
	ItemApplicationService itemApplicationService = ItemApplicationServiceImpl.getInstance();

	public static ItemServiceFacade getInstance() {
		if (instance == null)
			instance = new ItemServiceFacadeImpl();
		return instance;
	}

	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		int itemCount = 0;
		try {
			itemCount = itemApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-끝");
		}
		return itemCount;
	}

	@Override
	public List<ItemBean> getItemList(String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<ItemBean> bean = itemApplicationService.getItemList(code);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getItemList() - 끝");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getItemList()-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<ItemBean> bean = itemApplicationService.getItemList(sr, er, itemCode, itemName);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getItemList() - 끝");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getItemList()-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<BomBean> findBom(String searchCode){
		if (logger.isDebugEnabled()) { logger.debug("findBom()-시작");}
		dataSourceTransactionManager.beginTransaction();
		List<BomBean> bomList=null;
		try{
			bomList=itemApplicationService.findBom(searchCode);
			dataSourceTransactionManager.commitTransaction();
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findBom()-에러");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) { logger.debug("findBom()-끝");}
		return bomList;
	}

	@Override
	public List<StockBean> getDateStockList(String start, String end){
		if(logger.isDebugEnabled()){logger.debug("getDateStockList - 시작");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<StockBean> bean = itemApplicationService.getDateStockList(start, end);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getDateStockList - 끝");}
			return bean;
		}catch(Exception e){
			 if (logger.isFatalEnabled()) { logger.fatal("getDateStockList()-에러");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public List<StockBean> getItemStockList(String item){
		if(logger.isDebugEnabled()){logger.debug("getItemStockList - 시작");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<StockBean> bean = itemApplicationService.getItemStockList(item);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getItemStockList - 끝");}
			return bean;
		}catch(Exception e){
			 if (logger.isFatalEnabled()) { logger.fatal("getItemStockList()-에러");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void modifyItem(List<ItemBean> itemBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateItem() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			itemApplicationService.modifyItem(itemBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyItem() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyItem()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public void modifyBom(List<BomBean> bomBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateBom() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			itemApplicationService.modifyBom(bomBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyBom() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyBom()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public void modifyStock(List<StockBean> stockBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateStock() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			itemApplicationService.modifyStock(stockBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyStock() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyStock()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	
	
}

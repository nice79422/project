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
			logger.debug("getRowCount()-����");
		}
		dataSourceTransactionManager.beginTransaction();
		int itemCount = 0;
		try {
			itemCount = itemApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount()-��");
		}
		return itemCount;
	}

	@Override
	public List<ItemBean> getItemList(String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<ItemBean> bean = itemApplicationService.getItemList(code);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getItemList() - ��");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getItemList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public List<ItemBean> getItemList(int sr, int er, String itemCode, String itemName) {
		if (logger.isDebugEnabled()) {
			logger.debug("getItemList() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<ItemBean> bean = itemApplicationService.getItemList(sr, er, itemCode, itemName);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getItemList() - ��");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getItemList()-����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<BomBean> findBom(String searchCode){
		if (logger.isDebugEnabled()) { logger.debug("findBom()-����");}
		dataSourceTransactionManager.beginTransaction();
		List<BomBean> bomList=null;
		try{
			bomList=itemApplicationService.findBom(searchCode);
			dataSourceTransactionManager.commitTransaction();
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findBom()-����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) { logger.debug("findBom()-��");}
		return bomList;
	}

	@Override
	public List<StockBean> getDateStockList(String start, String end){
		if(logger.isDebugEnabled()){logger.debug("getDateStockList - ����");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<StockBean> bean = itemApplicationService.getDateStockList(start, end);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getDateStockList - ��");}
			return bean;
		}catch(Exception e){
			 if (logger.isFatalEnabled()) { logger.fatal("getDateStockList()-����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public List<StockBean> getItemStockList(String item){
		if(logger.isDebugEnabled()){logger.debug("getItemStockList - ����");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<StockBean> bean = itemApplicationService.getItemStockList(item);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getItemStockList - ��");}
			return bean;
		}catch(Exception e){
			 if (logger.isFatalEnabled()) { logger.fatal("getItemStockList()-����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void modifyItem(List<ItemBean> itemBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateItem() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			itemApplicationService.modifyItem(itemBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyItem() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyItem()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public void modifyBom(List<BomBean> bomBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateBom() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			itemApplicationService.modifyBom(bomBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyBom() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyBom()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public void modifyStock(List<StockBean> stockBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateStock() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			itemApplicationService.modifyStock(stockBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyStock() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyStock()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	
	
}

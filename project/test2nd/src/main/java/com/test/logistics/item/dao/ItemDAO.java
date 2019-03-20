package com.test.logistics.item.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.item.to.ItemBean;

public interface ItemDAO {
	 public int selectRowCount();
	 public List<ItemBean> selectItemList(String code) throws DataAccessException;
	 public List<ItemBean> selectItemList(int sr, int er, String itemCode, String itemName) throws DataAccessException;
	public List<ItemBean> selectItemList(int sr, int er, String itemCode) throws DataAccessException;
	public List<ItemBean> selectItemList(int sr, int er) throws DataAccessException;
	 public void insertItem(ItemBean item) throws DataAccessException;
	 public void updateItem(ItemBean item);
	 public void deleteItem(ItemBean item);
}

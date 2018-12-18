package com.test.logistics.item.dao;

import java.util.List;

import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;

public interface ItemDAO {
	 public int selectRowCount();
	 public List<ItemBean> selectItemList(String code);
	 /*public List<ItemBean> selectItemList(int sr, int er, String itemCode, String itemName) throws DataAccessException;*/
	/*public List<ItemBean> selectItemList(int sr, int er, String itemCode) throws DataAccessException;*/
	/*public List<ItemBean> selectItemList(int sr, int er) throws DataAccessException;*/
	 public void insertItem(ItemBean item);
	 /*public void updateItem(ItemBean item);*/
	 /*public void deleteItem(ItemBean item);*/
	 
	 public List<BomBean> selectBom(String searchCode, String bom);
	 
	 public List<ItemBean> selectSearchItemList(int sr, int er);
	 public List<ItemBean> selectAllItemList(int sr, int er, String itemCode, String itemName);
	 public List<ItemBean> selectCodeItemList(int sr, int er, String itemCode);
	 public List<ItemBean> selectNameItemList(int sr, int er, String itemCode);
	 
	 public List<ItemBean> selectItem();
}

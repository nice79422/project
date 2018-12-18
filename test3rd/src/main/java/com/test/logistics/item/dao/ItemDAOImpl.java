package com.test.logistics.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;

public class ItemDAOImpl extends IBatisDAO implements ItemDAO {
	
	
	@SuppressWarnings("deprecation")
	public int selectRowCount() {
		return (int) getSqlMapClientTemplate().queryForObject("item.selectRowCount");
	}

	
	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<ItemBean> selectItemList(String code){
		Map<String, Object> map = new HashMap<>();
		map.put("productStatus", code);
		return (List<ItemBean>) getSqlMapClientTemplate().queryForList("item.selectItemList", map);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
	public List<ItemBean> selectSearchItemList(int sr, int er){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<ItemBean>) getSqlMapClientTemplate().queryForList("item.selectSearchItemList", map);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
	 public List<ItemBean> selectNameItemList(int sr, int er, String searchWord){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("searchWord", searchWord);
		return (List<ItemBean>) getSqlMapClientTemplate().queryForList("item.selectNameItemList", map);
	    		
	    }
	
	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
	 public List<ItemBean> selectCodeItemList(int sr, int er, String searchWord){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("searchWord", searchWord);
		return (List<ItemBean>) getSqlMapClientTemplate().queryForList("item.selectCodeItemList", map);
	    		
	    }
	
	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
	public List<ItemBean> selectAllItemList(int sr, int er, String itemCode, String itemName){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("itemCode", itemCode);
		map.put("itemName", itemName);
		return (List<ItemBean>) getSqlMapClientTemplate().queryForList("item.selectAllItemList", map);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insertItem(ItemBean item){
		getSqlMapClientTemplate().update("item.insertItem", item);
	    }
	
	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
	public List<BomBean> selectBom(String searchCode, String bom){
		Map<String, Object> map = new HashMap<>();
		map.put("searchCode", searchCode);
		map.put("bom", bom);
		return (List<BomBean>) getSqlMapClientTemplate().queryForList("item.selectBom", map);	
		
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<ItemBean> selectItem(){
		return (List<ItemBean>) getSqlMapClientTemplate().queryForList("item.selectItem");
	}
	

}


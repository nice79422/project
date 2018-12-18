package com.test.logistics.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;


public class MrpDAOImpl extends IBatisDAO implements MrpDAO {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MrpBean> selectMrpOpenList(String workplaceCode, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("workplaceCode", workplaceCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		return getSqlMapClientTemplate().queryForList("mrp.selectMrpOpenList", map);
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MrpBean> selectMrpCodeList(MrpBean mrpCode){
		Map<String, Object> map = new HashMap<>();
		map.put("mrpCode", mrpCode);
		
		return (List<MrpBean>)getSqlMapClientTemplate().queryForList("mrp.selectMrpCodeList", map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int selectMrpListRowCount(String workspaceCode) {
		return (int) getSqlMapClientTemplate().queryForObject("mrp.selectMrpListRowCount", workspaceCode);
	}

	@SuppressWarnings("deprecation")
	public int selectTotalMrpListRowCount(String supply) {
		return (int) getSqlMapClientTemplate().queryForObject("mrp.selectTotalMrpListRowCount");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MrpBean> selectTotalMrp(int sr,int er,String supply){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<MrpBean>) getSqlMapClientTemplate().queryForList("mrp.selectTotalMrp", map);	
	}

	@SuppressWarnings("deprecation")
	public void insertMrpGathering(MrpBean mrpGatheringBean){
		getSqlMapClientTemplate().insert("mrp.insertMrpGathering", mrpGatheringBean);
	}
	
	

	@SuppressWarnings("deprecation")
	public void updateMrp(MrpBean bean){
		getSqlMapClientTemplate().update("mrp.updateMrp", bean);
	}

	
	@SuppressWarnings("deprecation")
	public int selectPurchaseOrderRowCount(String purchasingPlaceCode){
		return (int) getSqlMapClientTemplate().queryForObject("mrp.selectPurchaseOrderRowCount", purchasingPlaceCode);
	}

	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MrpBean> selectPurchaseOrder(int sr, int er, String code){
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("purchasingPlaceCode", code);
		return (List<MrpBean>) getSqlMapClientTemplate().queryForList("mrp.selectPurchaseOrder", map);
	
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<MrpBean>) getSqlMapClientTemplate().queryForList("mrp.dateMrp", map);
	}


	@SuppressWarnings("deprecation")
	public int selectMrpTotalReviewCount() {
		return (int) getSqlMapClientTemplate().queryForObject("mrp.selectMrpTotalReviewCount");
	}


	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<MrpTotalBean> selectMrpTotalReviewList(int startRow, int endRow) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return (List<MrpTotalBean>) getSqlMapClientTemplate().queryForList("mrp.selectMrpTotalReviewList", map);
	}
	
	
}

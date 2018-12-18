package com.test.logistics.product.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.product.to.MpsBean;

public class MpsDAOImpl extends IBatisDAO implements MpsDAO{
	
	@SuppressWarnings("deprecation")
	public int selectMpsListRowCount(String mpsStatus) {
		Map<String, Object> map = new HashMap<>();
		map.put("mpsStatus", mpsStatus);
		return (int) getSqlMapClientTemplate().queryForObject("mps.selectMpsListRowCount", map);
	}
	
	@SuppressWarnings("deprecation")
	public int selectMpsLineListRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("mpsStatus", mpsStatus);
		map.put("mrpStatus", mrpStatus);
		map.put("workpalceCode", lineNo);
		return (int) getSqlMapClientTemplate().queryForObject("mps.selectMpsLineListRowCount", map);
	}

	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MpsBean> selectNotApplyMpsList(int sr, int er, String mpsStatus) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("mpsStatus", mpsStatus);
		return (List<MpsBean>) getSqlMapClientTemplate().queryForList("mps.selectNotApplyMpsList", map);
	}
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MpsBean> selectApplyMpsList(int sr, int er) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<MpsBean>) getSqlMapClientTemplate().queryForList("mps.selectApplyMpsList", map);
			
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MpsBean> selectMpsList(int sr, int er, String mpsStatus, String mrpStatus,String lineNo,String sDate, String eDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("mpsStatus", mpsStatus);
		map.put("mrpStatus", mrpStatus);
		map.put("workpalceCode", lineNo);
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		return (List<MpsBean>) getSqlMapClientTemplate().queryForList("mps.selectMpsList", map);	
			
	}
	
	@SuppressWarnings("deprecation")
	public void insertMps(MpsBean mpsBean){
		getSqlMapClientTemplate().insert("mps.insertMps", mpsBean);
		
	}

	@SuppressWarnings("deprecation")
	public void updateMps(MpsBean mpsBean) {
		getSqlMapClientTemplate().update("mps.updateMps", mpsBean);
	}

}
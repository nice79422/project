package com.test.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.to.BasicCodeBean;

public class BasicCodeDAOImpl extends IBatisDAO implements BasicCodeDAO {

	@SuppressWarnings("deprecation")
	public int selectRowCount() {
		return (int) getSqlMapClientTemplate().queryForObject("code.selectRowCount");
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<BasicCodeBean> selectBasicCodeList(int sr, int er) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<BasicCodeBean>) getSqlMapClientTemplate().queryForList("code.selectBasicCodeList", map);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<BasicCodeBean> selectDetailCodeList(int sr, int er, String code) {

		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("basicCode", code);

		return (List<BasicCodeBean>) getSqlMapClientTemplate().queryForList("code.selectDetailCodeList", map);

	}

	@SuppressWarnings("deprecation")
	public void insertDetailCode(String detailCode, String detailCodeName, String basicCode) {
		BasicCodeBean basicCodeBean = new BasicCodeBean();
		basicCodeBean.setDetailCode(detailCode);
		basicCodeBean.setDetailCodeName(detailCodeName);
		basicCodeBean.setBasicCode(basicCode);
		getSqlMapClientTemplate().update("code.insertDetailCode", basicCodeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateDetailCode(String detailCode, String detailCodeName, String basicCode) {
		BasicCodeBean basicCodeBean = new BasicCodeBean();
		basicCodeBean.setDetailCode(detailCode);
		basicCodeBean.setDetailCodeName(detailCodeName);
		basicCodeBean.setBasicCode(basicCode);
		getSqlMapClientTemplate().update("code.updateDetailCode", basicCodeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteDetailCode(String detailCode) {
		BasicCodeBean basicCodeBean = new BasicCodeBean();
		basicCodeBean.setDetailCode(detailCode);
		getSqlMapClientTemplate().update("code.deleteDetailCode", basicCodeBean);
	}
}

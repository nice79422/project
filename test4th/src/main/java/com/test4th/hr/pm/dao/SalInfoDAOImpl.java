package com.test4th.hr.pm.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.pm.to.SalInfoBean;

public class SalInfoDAOImpl extends IBatisDAO implements SalInfoDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 사원의 급여정보를 조회하는 메서드 */
	public List<SalInfoBean> selectSalInfoAll() {
		return getSqlMapClientTemplate().queryForList("salInfo.selectSalInfoAll");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateSalInfo(SalInfoBean salInfoBean) {
		getSqlMapClientTemplate().update("salInfo.updateSalInfo",salInfoBean);
	}

}

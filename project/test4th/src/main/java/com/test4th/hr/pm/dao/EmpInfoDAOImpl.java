package com.test4th.hr.pm.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.pm.to.EmpInfoBean;


public class EmpInfoDAOImpl extends IBatisDAO implements EmpInfoDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 사원의 상세정보를 가지고 오는 메서드 */
	public List<EmpInfoBean> selectEmpInfoAll() {
		return getSqlMapClientTemplate().queryForList("empInfo.selectEmpInfoAll");
	}

	
	// 사원 상세정보를 수정하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void updateEmpInfo(EmpInfoBean empInfoBean) {
		getSqlMapClientTemplate().update("empInfo.updateEmpInfo",empInfoBean);
	}
	
	
}

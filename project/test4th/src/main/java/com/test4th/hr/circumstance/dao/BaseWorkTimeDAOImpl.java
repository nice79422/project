package com.test4th.hr.circumstance.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.circumstance.to.BaseWorkTimeBean;

public class BaseWorkTimeDAOImpl extends IBatisDAO implements BaseWorkTimeDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 모든 년도의 기본근무시간 목록을 가지고 오는 메서드 */
	public List<BaseWorkTimeBean> selectBaseWorkTimeList() {
		return getSqlMapClientTemplate().queryForList("baseWorkTime.selectBaseWorkTimeList");
	}

	@SuppressWarnings("deprecation")
	@Override
	// 해당년도에 기본근무 시간을 추가하는 메서드  
	public void insertBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean) {
		getSqlMapClientTemplate().insert("baseWorkTime.insertBaseWorkTime",baseWorkTimeBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	// 해당년도의 기본근무시간을 수정하는 메서드 
	public void updateBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean) {
		getSqlMapClientTemplate().update("baseWorkTime.updateBaseWorkTime",baseWorkTimeBean);
	}

	/*
	@SuppressWarnings("deprecation")
	@Override
	// 해당년도의 근무시간을 삭제하는 메서드 
	public void deleteBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean) {
		getSqlMapClientTemplate().delete("baseWorkTime.deleteBaseWorkTime",baseWorkTimeBean);
	}
	*/
}

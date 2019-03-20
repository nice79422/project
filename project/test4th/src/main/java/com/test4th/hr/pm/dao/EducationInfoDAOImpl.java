package com.test4th.hr.pm.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.pm.to.EducationInfoBean;

public class EducationInfoDAOImpl extends IBatisDAO implements EducationInfoDAO {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	/* 사원의 학력 정보를 조회하는 메서드 */
	public List<EducationInfoBean> selectEducationInfoAll() {
		return getSqlMapClientTemplate().queryForList("educationInfo.selectEducationInfoAll");
	}

	@SuppressWarnings("deprecation")
	@Override
	/* 사원의 학력정보를 추가하는 메서드 */
	public void insertEducationInfo(EducationInfoBean educationInfoBean) {
		getSqlMapClientTemplate().insert("educationInfo.insertEducationInfo",educationInfoBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	/* 사원의 학력정보를 수정하는 메서드 */
	public void updateEducationInfo(EducationInfoBean educationInfoBean) {
		getSqlMapClientTemplate().update("educationInfo.updateEducationInfo",educationInfoBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	/* 사원의 학력정보를 삭제하는 메서드 */
	public void deleteEducationInfo(EducationInfoBean educationInfoBean) {
		getSqlMapClientTemplate().delete("educationInfo.deleteEducationInfo",educationInfoBean);
	}
}

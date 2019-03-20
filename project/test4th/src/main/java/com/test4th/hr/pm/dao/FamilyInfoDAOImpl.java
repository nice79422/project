package com.test4th.hr.pm.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.pm.to.FamilyInfoBean;

public class FamilyInfoDAOImpl extends IBatisDAO implements FamilyInfoDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 사원의 가족정보를 조회하는 메서드 */
	public List<FamilyInfoBean> selectFamilyInfoAll() {
		return getSqlMapClientTemplate().queryForList("familyInfo.selectFamilyInfoAll");
	}

	// 사원의 가족정보를 추가하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void insertFamilyInfo(FamilyInfoBean familyInfoBean) {
		getSqlMapClientTemplate().insert("familyInfo.insertFamilyInfo",familyInfoBean);
	}

	// 사원의 가족정보를 수정하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void updateFamilyInfo(FamilyInfoBean familyInfoBean) {
		getSqlMapClientTemplate().update("familyInfo.updateFamilyInfo",familyInfoBean);
	}

	// 사원의 가족정보를 삭제하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void deleteFamilyInfo(FamilyInfoBean familyInfoBean) {
		getSqlMapClientTemplate().delete("familyInfo.deleteFamilyInfo",familyInfoBean);
	}
	

}

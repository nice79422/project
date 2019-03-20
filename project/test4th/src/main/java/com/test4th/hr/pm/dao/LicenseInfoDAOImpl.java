package com.test4th.hr.pm.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.pm.to.LicenseInfoBean;

public class LicenseInfoDAOImpl extends IBatisDAO implements LicenseInfoDAO {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	/* 사원의 자격증정보를 가지고 오는 메서드 */
	public List<LicenseInfoBean> selectLicenseInfoAll() {
		return getSqlMapClientTemplate().queryForList("licenseInfo.selectLicenseInfoAll");
	}

	@SuppressWarnings("deprecation")
	@Override
	// 자격정보를 추가하는 메서드 
	public void insertLicenseInfo(LicenseInfoBean licenseInfoBean) {
		getSqlMapClientTemplate().insert("licenseInfo.insertLicenseInfo",licenseInfoBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	// 자격정보를 수정하는 메서드 
	public void updateLicenseInfo(LicenseInfoBean licenseInfoBean) {
		getSqlMapClientTemplate().update("licenseInfo.updateLicenseInfo",licenseInfoBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	// 자격정보를 삭제하는 메서드 
	public void deleteLicenseInfo(LicenseInfoBean licenseInfoBean) {
		getSqlMapClientTemplate().delete("licenseInfo.deleteLicenseInfo",licenseInfoBean);
	}
	
}

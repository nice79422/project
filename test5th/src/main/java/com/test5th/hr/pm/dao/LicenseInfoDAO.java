package com.test5th.hr.pm.dao;

import java.util.List;

import com.test5th.hr.pm.to.LicenseInfoBean;

public interface LicenseInfoDAO {
	public List<LicenseInfoBean> selectLicenseInfoAll();
	
	public void insertLicenseInfo(LicenseInfoBean licenseInfoBean);
	public void updateLicenseInfo(LicenseInfoBean licenseInfoBean);
	public void deleteLicenseInfo(LicenseInfoBean licenseInfoBean);
	
}

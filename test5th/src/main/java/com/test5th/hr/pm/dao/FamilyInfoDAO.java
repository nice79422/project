package com.test5th.hr.pm.dao;

import java.util.List;

import com.test5th.hr.pm.to.FamilyInfoBean;

public interface FamilyInfoDAO {
	public List<FamilyInfoBean> selectFamilyInfoAll();
	
	public void insertFamilyInfo(FamilyInfoBean familyInfoBean);
	public void updateFamilyInfo(FamilyInfoBean familyInfoBean);
	public void deleteFamilyInfo(FamilyInfoBean familyInfoBean);
	
}

package com.test5th.hr.pm.dao;

import java.util.List;

import com.test5th.hr.pm.to.EducationInfoBean;

public interface EducationInfoDAO {
    public List<EducationInfoBean> selectEducationInfoAll();
    public void insertEducationInfo(EducationInfoBean educationInfoBean);
	public void updateEducationInfo(EducationInfoBean educationInfoBean);
	public void deleteEducationInfo(EducationInfoBean educationInfoBean);
}

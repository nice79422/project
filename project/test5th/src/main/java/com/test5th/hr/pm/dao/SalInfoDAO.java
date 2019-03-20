package com.test5th.hr.pm.dao;

import java.util.List;

import com.test5th.hr.pm.to.SalInfoBean;

public interface SalInfoDAO {
    public List<SalInfoBean> selectSalInfoAll();
    
    public void updateSalInfo(SalInfoBean salInfoBean);
}

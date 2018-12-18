package com.test4th.hr.pm.dao;

import java.util.List;

import com.test4th.hr.pm.to.EmpInfoBean;


public interface EmpInfoDAO {
    public List<EmpInfoBean> selectEmpInfoAll();
   
    public void updateEmpInfo(EmpInfoBean empInfoBean);
}

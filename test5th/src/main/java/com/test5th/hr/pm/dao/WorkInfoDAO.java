package com.test5th.hr.pm.dao;

import java.util.List;

import com.test5th.hr.pm.to.WorkInfoBean;

public interface WorkInfoDAO {
    public List<WorkInfoBean> selectWorkInfoAll();
    
    public void updateWorkInfo(WorkInfoBean workInfoBean);
}

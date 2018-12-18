package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.to.EtcSalBean;
import com.test4th.base.to.OvertimeSalBean;
import com.test4th.base.to.SudangInfoBean;

public interface BasicSalaryAppService {
    public List<OvertimeSalBean> findOvertimeSalList();
    public List<EtcSalBean> findEtcSalList();
    
    public void batchSudang(SudangInfoBean sudangInfoBean);
}

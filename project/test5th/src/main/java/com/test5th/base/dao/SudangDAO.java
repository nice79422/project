package com.test5th.base.dao;

import java.util.List;

import com.test5th.base.to.EtcSalBean;
import com.test5th.base.to.OvertimeSalBean;

public interface SudangDAO {

	public List<OvertimeSalBean> selectOvertimeSalList();
	public List<EtcSalBean> selectEtcSalList();

	public void insertOvertimeSal(OvertimeSalBean overtimeSalBean);
    public void updateOvertimeSal(OvertimeSalBean overtimeSalBean);
    public void deleteOvertimeSal(OvertimeSalBean overtimeSalBean);

    public void insertEtcSal(EtcSalBean etcSalBean);
    public void updateEtcSal(EtcSalBean etcSalBean);
    public void deleteEtcSal(EtcSalBean etcSalBean);
   

}

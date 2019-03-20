package com.test4th.hr.circumstance.dao;

import java.util.List;
import java.util.Map;

import com.test4th.hr.circumstance.to.AnnualBean;

public interface AnnualDAO {
	public List<AnnualBean> selectAnnualList();
	
	public Map<String, Object> createAnnual(String standardYear,String empCode,String hireDate);
	
	public void updateAnnual(AnnualBean annulBean);
	
	public void deleteAnnual(AnnualBean annulBean);	
	
	public void updateAnnualMgt(String standardYear,String empCode,String days,String restDays);
	
}

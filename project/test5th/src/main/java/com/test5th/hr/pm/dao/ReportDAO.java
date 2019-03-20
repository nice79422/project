package com.test5th.hr.pm.dao;

import java.util.List;
//import java.util.Map;

import com.test5th.hr.pm.to.ReportBean;


public interface ReportDAO {
    
	//public List<ReportBean> selectEmpInfoReport(Map<String, String> parameters);
	public List<ReportBean> selectEmpInfoReport(String empCode);
	
	public List<ReportBean> selectReport(String empCode);
	
}

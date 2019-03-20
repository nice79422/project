package kr.co.seoulit.hr.pm.dao;

import java.util.List;
//import java.util.Map;

import kr.co.seoulit.hr.pm.to.ReportTO;


public interface ReportDAO {
    
	//public List<ReportBean> selectEmpInfoReport(Map<String, String> parameters);
	public List<ReportTO> selectEmpInfoReport(String empCode);
	
	public List<ReportTO> selectReport(String empCode);
	
}

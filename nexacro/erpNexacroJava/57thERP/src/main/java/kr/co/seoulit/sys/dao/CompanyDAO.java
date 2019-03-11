package kr.co.seoulit.sys.dao;

import java.util.List;

import kr.co.seoulit.sys.to.CompanyTO;



public interface CompanyDAO {
	public List<CompanyTO> selectCompanyList();
	// 회사정보 조회
	public void updateCompanyInfo(CompanyTO companyTO);
	
}

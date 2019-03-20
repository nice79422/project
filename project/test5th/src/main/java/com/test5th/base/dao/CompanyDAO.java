
package com.test5th.base.dao;



import com.test5th.base.to.CompanyBean;

public interface CompanyDAO {
	 public CompanyBean selectCompany();

	    public void insertCompany(CompanyBean companyBean);
	    public void updateCompany(CompanyBean companyBean);
	    public void deleteCompany(CompanyBean companyBean);
}

package com.test4th.base.dao;

import com.test4th.base.to.CompanyBean;
import com.test4th.common.dao.IBatisDAO;

public class CompanyDAOImpl extends IBatisDAO implements CompanyDAO{

	  @Override
		@SuppressWarnings({ "deprecation"})
	    public CompanyBean selectCompany() {
	        return (CompanyBean)getSqlMapClientTemplate().queryForObject("Company.selectCompany");
	        
	    }

		@SuppressWarnings("deprecation")
		@Override
		public void insertCompany(CompanyBean companyBean) {
			getSqlMapClientTemplate().insert("Company.insertCompany",companyBean);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void updateCompany(CompanyBean companyBean) {
			getSqlMapClientTemplate().update("Company.updateCompany",companyBean);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void deleteCompany(CompanyBean companyBean) {
			getSqlMapClientTemplate().delete("Company.deleteCompany",companyBean);
		}
	

}


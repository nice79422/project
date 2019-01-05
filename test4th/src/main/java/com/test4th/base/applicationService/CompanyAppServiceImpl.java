package com.test4th.base.applicationService;



import com.test4th.base.dao.CompanyDAO;
import com.test4th.base.to.CompanyBean;





public class CompanyAppServiceImpl implements CompanyAppService {

    private CompanyDAO companyDAO;
    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }
	
	@Override
	public CompanyBean findCompany() {
	
		return companyDAO.selectCompany();
	}

	@Override
	public void batchCompany(CompanyBean companyBean) {

	
			switch(companyBean.getStatus()){ /*mapper에서 bean들의  rowType를 읽어 온듯?? 필요없는경우에 받아만 놓고 사용 안함*/
				case "insert" : companyDAO.insertCompany(companyBean); break;
				case "update" : companyDAO.updateCompany(companyBean); break;
				case "delete" : companyDAO.deleteCompany(companyBean); break;
			
		}
	
	}
	
}

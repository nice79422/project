package com.test4th.hr.pm.applicationService;


import com.test4th.hr.pm.dao.EducationInfoDAO;
import com.test4th.hr.pm.dao.EmpInfoDAO;
import com.test4th.hr.pm.dao.FamilyInfoDAO;
import com.test4th.hr.pm.dao.LicenseInfoDAO;
import com.test4th.hr.pm.dao.SalInfoDAO;
import com.test4th.hr.pm.dao.WorkInfoDAO;
import com.test4th.hr.pm.to.EducationInfoBean;
import com.test4th.hr.pm.to.EmpInfoBean;
import com.test4th.hr.pm.to.EmployeeInfoBean;
import com.test4th.hr.pm.to.FamilyInfoBean;
import com.test4th.hr.pm.to.LicenseInfoBean;
import com.test4th.hr.pm.to.SalInfoBean;
import com.test4th.hr.pm.to.WorkInfoBean;


public class PMAppServiceImpl implements PMAppService {

	private EmpInfoDAO empInfoDAO;
	
	public void setEmpInfoDAO(EmpInfoDAO empInfoDAO) {
		this.empInfoDAO = empInfoDAO;
	}
	
	private WorkInfoDAO workInfoDAO;
	
	public void setWorkInfoDAO(WorkInfoDAO workInfoDAO) {
		this.workInfoDAO = workInfoDAO;
	}
	
	private FamilyInfoDAO familyInfoDAO;

	public void setFamilyInfoDAO(FamilyInfoDAO familyInfoDAO) {
		this.familyInfoDAO = familyInfoDAO;
	}
	
	private LicenseInfoDAO licenseInfoDAO;
	
	public void setLicenseInfoDAO(LicenseInfoDAO licenseInfoDAO) {
		this.licenseInfoDAO = licenseInfoDAO;
	}
	
	private EducationInfoDAO educationInfoDAO;
	
	public void setEducationInfoDAO(EducationInfoDAO educationInfoDAO) {
		this.educationInfoDAO = educationInfoDAO;
	}
	
	private SalInfoDAO salInfoDAO;
	public void setSalInfoDAO(SalInfoDAO salInfoDAO) {
		this.salInfoDAO = salInfoDAO;
	}
	
	@Override
	/* 사원의 모든 상세정보를 가지고 오는 메서드 */
	public EmployeeInfoBean findEmployeeInfoAll() {
		EmployeeInfoBean employeeInfoBean=new EmployeeInfoBean();
		employeeInfoBean.setEmpInfoList(empInfoDAO.selectEmpInfoAll());
		employeeInfoBean.setWorkInfoList(workInfoDAO.selectWorkInfoAll());
		employeeInfoBean.setFamilyInfoList(familyInfoDAO.selectFamilyInfoAll());
		employeeInfoBean.setLicenseInfoList(licenseInfoDAO.selectLicenseInfoAll());
		employeeInfoBean.setEducationInfoList(educationInfoDAO.selectEducationInfoAll());
		employeeInfoBean.setSalInfoList(salInfoDAO.selectSalInfoAll());
		
		return employeeInfoBean;
	}
	
	/* 사원관련정보를 일괄처리하는 메서드 */
	@Override
	public void batchEmployeeInfoAll(EmployeeInfoBean employeeInfoBean) {
		//사원상세정보 있으면 여기 실행(수정된것) [사원코드랑 연결 되어 있는 구조는 insert,delete가 안됨 update 뿐임.. insert는 기본 사원정보 넣을 때 실시 삭제는 내가 막아 놨음]
		for(EmpInfoBean EmpInfoBean :employeeInfoBean.getEmpInfoList()){
			empInfoDAO.updateEmpInfo(EmpInfoBean);
		}
		
		for(WorkInfoBean workInfoBean:employeeInfoBean.getWorkInfoList()){
			workInfoDAO.updateWorkInfo(workInfoBean);
		}
		
		for(SalInfoBean salInfoBean:employeeInfoBean.getSalInfoList()){
			salInfoDAO.updateSalInfo(salInfoBean);
		}

		for(FamilyInfoBean familyInfoBean :employeeInfoBean.getFamilyInfoList()){
			switch(familyInfoBean.getStatus()){
				case "insert" : familyInfoDAO.insertFamilyInfo(familyInfoBean); break;
				case "update" : familyInfoDAO.updateFamilyInfo(familyInfoBean); break;
				case "delete" : familyInfoDAO.deleteFamilyInfo(familyInfoBean); break;
			}
		}
		
		for(LicenseInfoBean licenseInfoBean :employeeInfoBean.getLicenseInfoList()){
			switch(licenseInfoBean.getStatus()){
				case "insert" : licenseInfoDAO.insertLicenseInfo(licenseInfoBean); break;
				case "update" : licenseInfoDAO.updateLicenseInfo(licenseInfoBean); break;
				case "delete" : licenseInfoDAO.deleteLicenseInfo(licenseInfoBean); break;
			}
		}
		
		
		for(EducationInfoBean educationInfoBean :employeeInfoBean.getEducationInfoList()){
			switch(educationInfoBean.getStatus()){
				case "insert" : educationInfoDAO.insertEducationInfo(educationInfoBean); break;
				case "update" : educationInfoDAO.updateEducationInfo(educationInfoBean); break;
				case "delete" : educationInfoDAO.deleteEducationInfo(educationInfoBean); break;
			}
		}
	
	}
}

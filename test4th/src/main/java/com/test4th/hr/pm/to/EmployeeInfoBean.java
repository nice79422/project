package com.test4th.hr.pm.to;

import java.util.List;

import com.test4th.common.to.BaseBean;

public class EmployeeInfoBean extends BaseBean{

	private List<EmpInfoBean> empInfoList;
	
	private List<WorkInfoBean> workInfoList;
	
	private List<FamilyInfoBean> familyInfoList;
	
	private List<LicenseInfoBean> licenseInfoList;
	
	private List<EducationInfoBean> educationInfoList;
	
	private List<SalInfoBean> salInfoList;
	
	public List<EmpInfoBean> getEmpInfoList() {
		return empInfoList;
	}
	public void setEmpInfoList(List<EmpInfoBean> empInfoList) {
		this.empInfoList = empInfoList;
	}
	
	
	public List<WorkInfoBean> getWorkInfoList() {
		return workInfoList;
	}
	public void setWorkInfoList(List<WorkInfoBean> workInfoList) {
		this.workInfoList = workInfoList;
	}
	
	public List<FamilyInfoBean> getFamilyInfoList() {
		return familyInfoList;
	}
	public void setFamilyInfoList(List<FamilyInfoBean> familyInfoList) {
		this.familyInfoList = familyInfoList;
	}
	
	public List<LicenseInfoBean> getLicenseInfoList() {
		return licenseInfoList;
	}
	public void setLicenseInfoList(List<LicenseInfoBean> licenseInfoList) {
		this.licenseInfoList = licenseInfoList;
	}

	public List<EducationInfoBean> getEducationInfoList() {
		return educationInfoList;
	}
	public void setEducationInfoList(List<EducationInfoBean> educationInfoList) {
		this.educationInfoList = educationInfoList;
	}
	
	public List<SalInfoBean> getSalInfoList() {
		return salInfoList;
	}
	public void setSalInfoList(List<SalInfoBean> salInfoList) {
		this.salInfoList = salInfoList;
	}
	
	@Override
	public String toString() {
		return "EmployeeInfoBean [empInfoList=" + empInfoList + ", workInfoList=" + workInfoList + ", familyInfoList=" + familyInfoList
				+ ", licenseInfoList=" + licenseInfoList + ", educationInfoList=" + educationInfoList +  ", salInfoList="
				+ salInfoList +  "]";
	}
}

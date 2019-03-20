package kr.co.seoulit.hr.pm.to;

import java.util.List;

import kr.co.seoulit.common.to.BaseTO;

public class EmployeeInfoTO extends BaseTO{

	private List<EmpInfoTO> empInfoList;
	
	private List<WorkInfoTO> workInfoList;
	
	private List<FamilyInfoTO> familyInfoList;
	
	private List<LicenseInfoTO> licenseInfoList;
	
	private List<EducationInfoTO> educationInfoList;
	
	//private List<SalInfoTO> salInfoList;
	
	public List<EmpInfoTO> getEmpInfoList() {
		return empInfoList;
	}
	public void setEmpInfoList(List<EmpInfoTO> empInfoList) {
		this.empInfoList = empInfoList;
	}
	
	
	public List<WorkInfoTO> getWorkInfoList() {
		return workInfoList;
	}
	public void setWorkInfoList(List<WorkInfoTO> workInfoList) {
		this.workInfoList = workInfoList;
	}
	
	public List<FamilyInfoTO> getFamilyInfoList() {
		return familyInfoList;
	}
	public void setFamilyInfoList(List<FamilyInfoTO> familyInfoList) {
		this.familyInfoList = familyInfoList;
	}
	
	public List<LicenseInfoTO> getLicenseInfoList() {
		return licenseInfoList;
	}
	public void setLicenseInfoList(List<LicenseInfoTO> licenseInfoList) {
		this.licenseInfoList = licenseInfoList;
	}

	public List<EducationInfoTO> getEducationInfoList() {
		return educationInfoList;
	}
	public void setEducationInfoList(List<EducationInfoTO> educationInfoList) {
		this.educationInfoList = educationInfoList;
	}
	
//	public List<SalInfoTO> getSalInfoList() {
//		return salInfoList;
//	}
//	public void setSalInfoList(List<SalInfoTO> salInfoList) {
//		this.salInfoList = salInfoList;
//	}
	
	@Override
	public String toString() {
		return "EmployeeInfoTO [empInfoList=" + empInfoList + ", workInfoList=" + workInfoList + ", familyInfoList=" + familyInfoList + ", licenseInfoList=" + licenseInfoList + ", educationInfoList=" + educationInfoList + "]";
	}
	
//	@Override
//	public String toString() {
//		return "EmployeeInfoTO [empInfoList=" + empInfoList + ", workInfoList=" + workInfoList + ", familyInfoList=" + familyInfoList
//				+ ", licenseInfoList=" + licenseInfoList + ", educationInfoList=" + educationInfoList +  ", salInfoList="
//				+ salInfoList +  "]";
//	}
	
}

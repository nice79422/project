package com.test4th.hr.pm.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsEducationInfo")
public class EducationInfoBean extends BaseBean{

	private String empCode;
	private String educationSeq;
	private String campus;
	private String major;
	private String subMajor;
	private String enterDate;
	private String graduationDate;
	private String grade;
	private String location;

	public String getEducationSeq() {
		return educationSeq;
	}
	public void setEducationSeq(String educationSeq) {
		this.educationSeq = educationSeq;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSubMajor() {
		return subMajor;
	}
	public void setSubMajor(String subMajor) {
		this.subMajor = subMajor;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}

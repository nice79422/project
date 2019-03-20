package kr.co.seoulit.hr.pm.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="dsFamilyInfo")
public class FamilyInfoTO extends BaseTO{

	private String empCode;
	private String familySeq;
	private String familyName;
	private String familyBirthDate;
	private String relationship;
	private String supporting;
	private String cohabitationStatus;
	private String disabilityCode;
	private String highestEducationCode;
	private String job;
	private String companyName;
	private String tel;

	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFamilySeq() {
		return familySeq;
	}
	public void setFamilySeq(String familySeq) {
		this.familySeq = familySeq;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFamilyBirthDate() {
		return familyBirthDate;
	}
	public void setFamilyBirthDate(String familyBirthDate) {
		this.familyBirthDate = familyBirthDate;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getSupporting() {
		return supporting;
	}
	public void setSupporting(String supporting) {
		this.supporting = supporting;
	}
	public String getCohabitationStatus() {
		return cohabitationStatus;
	}
	public void setCohabitationStatus(String cohabitationStatus) {
		this.cohabitationStatus = cohabitationStatus;
	}
	public String getDisabilityCode() {
		return disabilityCode;
	}
	public void setDisabilityCode(String disabilityCode) {
		this.disabilityCode = disabilityCode;
	}
	public String getHighestEducationCode() {
		return highestEducationCode;
	}
	public void setHighestEducationCode(String highestEducationCode) {
		this.highestEducationCode = highestEducationCode;
	}
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

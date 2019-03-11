package kr.co.seoulit.hr.pm.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="dsEmpInfo")
public class EmpInfoTO extends BaseTO{
	
	private String chk;
	private String empCode;
	private String empName;
	private String residentNumber;
	private String birthDate;
	private String gender;
	private String imgSrc;
	private String zipCode;
	private String baseAddress;
	private String detailAddress;
	private String email;
	private String tel;
	private String phoneNumber;
	private String highestEducationCode;
	private String nativeTypeCode;
	private String disabilityCode;

	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getResidentNumber() {
		return residentNumber;
	}
	public void setResidentNumber(String residentNumber) {
		this.residentNumber = residentNumber;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getHighestEducationCode() {
		return highestEducationCode;
	}
	public void setHighestEducationCode(String highestEducationCode) {
		this.highestEducationCode = highestEducationCode;
	}
	public String getNativeTypeCode() {
		return nativeTypeCode;
	}
	public void setNativeTypeCode(String nativeTypeCode) {
		this.nativeTypeCode = nativeTypeCode;
	}
	public String getDisabilityCode() {
		return disabilityCode;
	}
	public void setDisabilityCode(String disabilityCode) {
		this.disabilityCode = disabilityCode;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
}

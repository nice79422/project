package kr.co.seoulit.sys.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_department")
public class DepartmentTO extends BaseTO{

	String deptCode,
	deptName,
	deptTel,
	businessPlaceCode;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptTel() {
		return deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	public String getBusinessPlaceCode() {
		return businessPlaceCode;
	}

	public void setBusinessPlaceCode(String businessPlaceCode) {
		this.businessPlaceCode = businessPlaceCode;
	}


}

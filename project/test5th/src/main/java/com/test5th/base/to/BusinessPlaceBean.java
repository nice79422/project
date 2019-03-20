package com.test5th.base.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name = "dsBusinessPlace")
public class BusinessPlaceBean extends BaseBean {

	private String businessPlaceCode;
	private String businessPlaceName;
	private String businessPlaceTel;
	private String companyCode;

	public String getBusinessPlaceCode() {
		return businessPlaceCode;
	}

	public void setBusinessPlaceCode(String businessPlaceCode) {
		this.businessPlaceCode = businessPlaceCode;
	}

	public String getBusinessPlaceName() {
		return businessPlaceName;
	}

	public void setBusinessPlaceName(String businessPlaceName) {
		this.businessPlaceName = businessPlaceName;
	}

	public String getBusinessPlaceTel() {
		return businessPlaceTel;
	}

	public void setBusinessPlaceTel(String businessPlaceTel) {
		this.businessPlaceTel = businessPlaceTel;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

}

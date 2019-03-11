package kr.co.seoulit.sys.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_businessPlace")
public class BusinessPlaceTO extends BaseTO{

	String businessPlaceCode,
	businessPlaceName,
	businessPlaceTel,
	companyCode;

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

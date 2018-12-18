package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsAddress")
public class AddressBean extends BaseBean{
	
	private String zipCode;
	private String address;

	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

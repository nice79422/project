package com.test5th.base.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsEtcSal")
public class EtcSalBean extends BaseBean{

	private String etcSalCode;
	private String etcSalName;
	private String etcSalPrice;


	public String getEtcSalCode() {
		return etcSalCode;
	}
	public void setEtcSalCode(String etcSalCode) {
		this.etcSalCode = etcSalCode;
	}
	public String getEtcSalName() {
		return etcSalName;
	}
	public void setEtcSalName(String etcSalName) {
		this.etcSalName = etcSalName;
	}
	public String getEtcSalPrice() {
		return etcSalPrice;
	}
	public void setEtcSalPrice(String etcSalPrice) {
		this.etcSalPrice = etcSalPrice;
	}

}

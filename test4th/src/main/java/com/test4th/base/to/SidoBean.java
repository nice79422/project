package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsSido")
public class SidoBean extends BaseBean{

	private String sidoCode;
	private String sidoValue;

	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoValue() {
		return sidoValue;
	}
	public void setSidoValue(String sidoValue) {
		this.sidoValue = sidoValue;
	}
}

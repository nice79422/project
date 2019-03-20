package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsDetailCode")
public class DetailCodeBean extends BaseBean{

	private String code;
	private String detailCode;
	private String detailCodeName;
	private String usingStatus;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public String getDetailCodeName() {
		return detailCodeName;
	}
	public void setDetailCodeName(String detailCodeName) {
		this.detailCodeName = detailCodeName;
	}
	public String getUsingStatus() {
		return usingStatus;
	}
	public void setUsingStatus(String usingStatus) {
		this.usingStatus = usingStatus;
	}
}

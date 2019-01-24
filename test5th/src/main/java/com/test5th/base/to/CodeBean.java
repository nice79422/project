package com.test5th.base.to;

import java.util.List;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.annotation.Remove;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsCode")
public class CodeBean extends BaseBean{

	private String code;
	private String codeName;
	private String usingStatus;
	private List<DetailCodeBean> detailCodeList;

	@Remove
	public List<DetailCodeBean> getDetailCodeList() {
		return detailCodeList;
	}
	@Remove
	public void setDetailCodeList(List<DetailCodeBean> detailCodeList) {
		this.detailCodeList = detailCodeList;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getUsingStatus() {
		return usingStatus;
	}
	public void setUsingStatus(String usingStatus) {
		this.usingStatus = usingStatus;
	}

	@Override
	public String toString() {
		return "CodeBean [code=" + code + ", codeName=" + codeName + ", usingStatus=" + usingStatus
				+ ", detailCodeList=" + detailCodeList + "]";
	}
}

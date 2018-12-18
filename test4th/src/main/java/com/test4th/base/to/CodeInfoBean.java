package com.test4th.base.to;

import java.util.List;

import com.test4th.common.to.BaseBean;

public class CodeInfoBean extends BaseBean{

	private List<CodeBean> codeList;
	private List<DetailCodeBean> detailCodeList;

	public List<CodeBean> getCodeList() {
		return codeList;
	}
	public void setCodeList(List<CodeBean> codeList) {
		this.codeList = codeList;
	}
	public List<DetailCodeBean> getDetailCodeList() {
		return detailCodeList;
	}
	public void setDetailCodeList(List<DetailCodeBean> detailCodeList) {
		this.detailCodeList = detailCodeList;
	}

	@Override
	public String toString() {
		return "CodeInfoBean [codeList=" + codeList + ", detailCodeList=" + detailCodeList + "]";
	}
}

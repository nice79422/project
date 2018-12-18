package com.test.logistics.business.to;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimateBean {
	private String estimateCode;
	private String clientCode;
	private String estimateDate;
	private String contractStatus;
	private String status;
	private String standByStatus;
	
	private List<EstimateDetailBean> estimateDetailList;
	
	
	
	
	private EstimateDetailBean estimateDetailBean;
	

	public EstimateDetailBean getEstimateDetailBean() {
		return estimateDetailBean;
	}
	public void setEstimateDetailBean(EstimateDetailBean estimateDetailBean) {
		this.estimateDetailBean = estimateDetailBean;
	}
	
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEstimateCode() {
		return estimateCode;
	}
	public void setEstimateCode(String estimateCode) {
		this.estimateCode = estimateCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public List<EstimateDetailBean> getEstimateDetailList() {
		return estimateDetailList;
	}
	public void setEstimateDetailList(List<EstimateDetailBean> estimateDetailList) {
		this.estimateDetailList = estimateDetailList;
	}
	public String getStandByStatus() {
		return standByStatus;
	}
	public void setStandByStatus(String standByStatus) {
		this.standByStatus = standByStatus;
	}


}

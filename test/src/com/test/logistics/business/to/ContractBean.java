package com.test.logistics.business.to;


import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractBean {
	private String contractCode;
	private String contractDate;
	private String produceStatus;
	private String standByStatus;
	private String clientCode;
	private EstimateBean estimateBean;
	private String estimateCode;
	private ArrayList<ContractDetailBean> contractDetailList;
	
	
	public String getStandByStatus() {
		return standByStatus;
	}
	public void setStandByStatus(String standByStatus) {
		this.standByStatus = standByStatus;
	}
	
	public String getProduceStatus() {
		return produceStatus;
	}
	public void setProduceStatus(String produceStatus) {
		this.produceStatus = produceStatus;
	}
	
	
	public String getEstimateCode() {
		return estimateCode;
	}
	public void setEstimateCode(String estimateCode) {
		this.estimateCode = estimateCode;
	}

	

	public ArrayList<ContractDetailBean> getContractDetailList() {
		return contractDetailList;
	}
	public void setContractDetailList(ArrayList<ContractDetailBean> contractDetailList) {
		this.contractDetailList = contractDetailList;
	}
	public EstimateBean getEstimateBean() {
		return estimateBean;
	}
	public void setEstimateBean(EstimateBean estimateBean) {
		this.estimateBean = estimateBean;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	@Override
	public String toString() {
		return "ContractBean [contractCode=" + contractCode + ", contractDate=" + contractDate + ", clientCode="
				+ clientCode + ", contractDetailList=" + contractDetailList + "]";
	}
}

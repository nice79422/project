package kr.co.seoulit.hr.pay.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.annotation.Remove;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_salaryInput")
public class SalaryInputTO extends BaseTO{

	private String paymentDate;
	private String empCode;
	private String salaryTypeCode;
	private String totalSalary;
	private String totalDeductionPrice;
	private String deptCode;
	private String positionCode;
	private String hireDate;
	private String retireDate;
	private String chinePayments;
	private List<PayDeductionTO> payDeductionList;

	@Remove
	public List<PayDeductionTO> getPayDeductionList() {
		return payDeductionList;
	}
	@Remove
	public void setPayDeductionList(List<PayDeductionTO> payDeductionList) {
		this.payDeductionList = payDeductionList;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getSalaryTypeCode() {
		return salaryTypeCode;
	}
	public void setSalaryTypeCode(String salaryTypeCode) {
		this.salaryTypeCode = salaryTypeCode;
	}
	public String getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(String totalSalary) {
		this.totalSalary = totalSalary;
	}
	public String getTotalDeductionPrice() {
		return totalDeductionPrice;
	}
	public void setTotalDeductionPrice(String totalDeductionPrice) {
		this.totalDeductionPrice = totalDeductionPrice;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(String retireDate) {
		this.retireDate = retireDate;
	}
	public String getChinePayments() {
		return chinePayments;
	}
	public void setChinePayments(String chinePayments) {
		this.chinePayments = chinePayments;
	}
}

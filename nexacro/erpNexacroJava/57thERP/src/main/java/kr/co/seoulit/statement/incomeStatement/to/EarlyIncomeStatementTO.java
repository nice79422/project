package kr.co.seoulit.statement.incomeStatement.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_EarlyIncomeStatement")
public class EarlyIncomeStatementTO extends BaseTO{
	private String accountName, price,parentAccountInnerCode;
	
	                                         
                                             
                                             
	public String getParentAccountInnerCode() {
		return parentAccountInnerCode;
	}

	public void setParentAccountInnerCode(String parentAccountInnerCode) {
		this.parentAccountInnerCode = parentAccountInnerCode;
	}

	public String getPrice() {               
		return price;                        
	}                                        
                                             
	public void setPrice(String price) {     
		this.price = price;                  
	}                                        
                                             
	public String getAccountName() {         
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
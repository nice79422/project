package kr.co.seoulit.statement.financialStatement.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;


@Dataset(name="ds_earlyFinancialStatements")
public class EarlyFinancialStatementsTO extends BaseTO{
	private String groupCode,
	price;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	

}

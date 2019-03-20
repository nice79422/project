package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_overtimeSal")
public class OvertimeSudangTO extends BaseTO{

	private String overtimeSalCode;
	private String overtimeSalName;
	private String overtimeSalRate;

	public String getovertimeSalCode() {
		return overtimeSalCode;
	}
	public void setovertimeSalCode(String overtimeSalCode) {
		this.overtimeSalCode = overtimeSalCode;
	}
	public String getovertimeSalName() {
		return overtimeSalName;
	}
	public void setovertimeSalName(String overtimeSalName) {
		this.overtimeSalName = overtimeSalName;
	}
	public String getovertimeSalRate() {
		return overtimeSalRate;
	}
	public void setovertimeSalRate(String overtimeSalRate) {
		this.overtimeSalRate = overtimeSalRate;
	}

}

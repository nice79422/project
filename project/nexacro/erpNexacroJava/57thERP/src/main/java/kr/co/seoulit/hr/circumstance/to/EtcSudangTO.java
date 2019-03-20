package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_etcSal")
public class EtcSudangTO extends BaseTO{

	private String etcSalCode;
	private String etcSalName;
	private String etcSalPrice;


	public String getEtcSalCode() {
		return etcSalCode;
	}
	public void setEtcSalCode(String etcSalCode) {
		this.etcSalCode = etcSalCode;
	}
	public String getEtcSalName() {
		return etcSalName;
	}
	public void setEtcSalName(String etcSalName) {
		this.etcSalName = etcSalName;
	}
	public String getEtcSalPrice() {
		return etcSalPrice;
	}
	public void setEtcSalPrice(String etcSalPrice) {
		this.etcSalPrice = etcSalPrice;
	}

}

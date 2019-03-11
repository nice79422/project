package kr.co.seoulit.common.to;

public class BaseTO {

	protected String status = "normal";
	protected String chk = "0";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

}
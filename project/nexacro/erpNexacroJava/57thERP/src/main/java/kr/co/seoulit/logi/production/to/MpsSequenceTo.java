package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.to
@Class			MpsSequenceTo.java
@Author			kanghokyeong
@Description	MPS 시퀀스
@Create			2019.02.20
@Last Update   
************************************************************************/

@Dataset(name="ds_MPSSqeNo")
public class MpsSequenceTo extends BaseTO {
	String seqNo;

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
}

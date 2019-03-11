package kr.co.seoulit.sys.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_sequence")
public class SequenceTo extends BaseTO{
String seqNo;

public String getSeqNo() {
	return seqNo;
}

public void setSeqNo(String seqNo) {
	this.seqNo = seqNo;
}

}

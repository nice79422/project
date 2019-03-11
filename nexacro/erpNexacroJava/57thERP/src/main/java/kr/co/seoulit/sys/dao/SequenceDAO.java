package kr.co.seoulit.sys.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.sys.to.CodeTO;

public interface SequenceDAO{

	public List<CodeTO> selectCodeList();
	// 코드 리스트 조회

	public String selectSequenceNo(HashMap<String, Object> takeSeq);

}

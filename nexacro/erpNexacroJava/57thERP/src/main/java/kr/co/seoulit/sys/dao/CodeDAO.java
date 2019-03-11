package kr.co.seoulit.sys.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.sys.to.CodeTO;

public interface CodeDAO{

	public List<CodeTO> selectCodeList();
	// 코드 리스트 조회
	
	public String takeSerialCode(HashMap<String, Object> takeSeq);

	public String takeSerialCodeTest(HashMap<String, Object> findSeq);	
}

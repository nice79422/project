package kr.co.seoulit.logi.purchase.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.purchase.to.BomDeployTO;
import kr.co.seoulit.logi.purchase.to.BomTO;

public interface BomDAO {

	//BOM목록 조회
	public List<BomTO> selectBomList();

	//BOM전개 조회
	public List<BomDeployTO> selectBomDeployList(HashMap<String, Object> deployCondition);
	
}

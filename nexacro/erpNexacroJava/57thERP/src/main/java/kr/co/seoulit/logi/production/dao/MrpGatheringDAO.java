package kr.co.seoulit.logi.production.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.logi.production.to.MrpGatheringTO;

public interface MrpGatheringDAO {

	public Map<String, Object> mrpGathering(Map<String, Object> paramMap);
	
	public List<MrpGatheringTO> selectMrpGatheringList(HashMap<String, Object> paramMap);

	public void updateMrpGathering(MrpGatheringTO mrpGatheringTO);
	
//	public void insertMrpGathering(MrpGatheringTO mrpGatheringTO);

	public void deleteMrpGathering(MrpGatheringTO mrpGatheringTO);
}

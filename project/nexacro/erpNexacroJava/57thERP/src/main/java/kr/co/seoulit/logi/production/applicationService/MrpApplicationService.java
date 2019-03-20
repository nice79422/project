package kr.co.seoulit.logi.production.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.production.to.MrpOpenTempTO;
import kr.co.seoulit.logi.production.to.MrpTO;

public interface MrpApplicationService {

	List<MrpTO> findMrpList(HashMap<String, Object> paramMap);

	List<MrpOpenTempTO> findMrpOpenTempProcessList(HashMap<String, Object> paramMap);

	void registMrpGathering(Map<String, Object> paramMap);

	List<MrpGatheringTO> findMrpGatheringList(HashMap<String, Object> paramMap);

	void batchMrpGathering(List<MrpGatheringTO> mrpGatheringList);

}

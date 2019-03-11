package kr.co.seoulit.logi.production.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.logi.production.to.MrpOpenTempTO;
import kr.co.seoulit.logi.production.to.MrpTO;

public interface MrpDAO {
	public List<MrpTO> selectMrpList(HashMap<String, Object> paramMap);
	
	public List<MrpOpenTempTO> MrpOpenTempProcessList(Map<String,Object> parameters);
}

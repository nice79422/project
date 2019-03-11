package kr.co.seoulit.logi.production.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.production.to.MpsTO;

public interface MpsDAO {

	public List<MpsTO> selectMpsList(HashMap<String, Object> searchDate);

	public void insertMps(MpsTO mpsTO);
}

package kr.co.seoulit.logi.production.dao;

import java.util.List;

import kr.co.seoulit.logi.production.to.PrmTO;

public interface PrmDAO {

	public List<PrmTO> selectPrmList();

	public void insertPrm(PrmTO prmTO);

}

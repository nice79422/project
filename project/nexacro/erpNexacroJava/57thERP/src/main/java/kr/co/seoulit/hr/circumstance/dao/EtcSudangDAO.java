package kr.co.seoulit.hr.circumstance.dao;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.EtcSudangTO;

public interface EtcSudangDAO {

	public List<EtcSudangTO> selectEtcSudangList();

	public void insertEtcSudang(EtcSudangTO etcSudangTO);

	public void updateEtcSudang(EtcSudangTO etcSudangTO);

	public void deleteEtcSudang(EtcSudangTO etcSudangTO);

}

package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;
import java.util.Map;

import kr.co.seoulit.hr.circumstance.to.EtcSudangTO;
import kr.co.seoulit.hr.circumstance.to.OvertimeSudangTO;

public interface SudangMgnApplicationService {
	/*수당정보 조회등록*/
	public Map<String,Object> findSudangList();

	public Map<String,Object> batchSudangProcess(Map<String,Object> sudangInfoList);

	// 연장,야간,휴일수당목록을 가져오는 메서드
	public List<OvertimeSudangTO> findOvertimeSudangList();
	//기타수당목록을 가져오는 메서드
	public List<EtcSudangTO> findEtcSudangList();
}

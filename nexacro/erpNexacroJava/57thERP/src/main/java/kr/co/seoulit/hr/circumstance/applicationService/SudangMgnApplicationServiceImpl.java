package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.circumstance.dao.EtcSudangDAO;
import kr.co.seoulit.hr.circumstance.dao.OvertimeSudangDAO;
import kr.co.seoulit.hr.circumstance.to.EtcSudangTO;
import kr.co.seoulit.hr.circumstance.to.OvertimeSudangTO;
import kr.co.seoulit.sys.applicationService.BaseApplicationService;

@Component
public class SudangMgnApplicationServiceImpl implements SudangMgnApplicationService {

	@Autowired
	OvertimeSudangDAO overTimeSudangDAO;
	@Autowired
	EtcSudangDAO etcSudangDAO;
	@Autowired
	private BaseApplicationService baseApplicationService;


	@Override
	public Map<String, Object> findSudangList() {
		// TODO Auto-generated method stub
		Map<String, Object> sudangInfoList = new HashMap<>();
		List<OvertimeSudangTO> overtimeSudangList = overTimeSudangDAO.selectOvertimeSudangList();
		List<EtcSudangTO> etcSudangList = etcSudangDAO.selectEtcSudangList();
		sudangInfoList.put("overtimeSudangList", overtimeSudangList);
		sudangInfoList.put("etcSudangList", etcSudangList);
		return sudangInfoList;
	}

	@Override
	public Map<String, Object> batchSudangProcess(Map<String, Object> sudangInfoList) {
		// TODO Auto-generated method stub
		for (String key : sudangInfoList.keySet()) {
			if (key.equals("overtimeSudangList")) {
				List<OvertimeSudangTO> overtimeSudangList = (List<OvertimeSudangTO>) sudangInfoList.get(key);

				for (OvertimeSudangTO overtimeSudangTO : overtimeSudangList) {
					Map<String, Object> codeColumn=new HashMap<>();
					codeColumn.put("divisionCodeNo","HR26");
					codeColumn.put("detailCode", overtimeSudangTO.getovertimeSalCode());
					codeColumn.put("detailCodeName", overtimeSudangTO.getovertimeSalName());
					switch (overtimeSudangTO.getStatus()) {
					case "inserted":
						overTimeSudangDAO.insertOvertimeSudang(overtimeSudangTO);
						baseApplicationService.batchDetailCodeProcess(overtimeSudangTO,codeColumn);
						break;
					case "updated":
						overTimeSudangDAO.updateOvertimeSudang(overtimeSudangTO);
						baseApplicationService.batchDetailCodeProcess(overtimeSudangTO,codeColumn);
						break;
					case "deleted":
						overTimeSudangDAO.deleteOvertimeSudang(overtimeSudangTO);
						baseApplicationService.batchDetailCodeProcess(overtimeSudangTO,codeColumn);
						break;
					}
				}

			}else if(key.equals("etcSudangList")){
				List<EtcSudangTO> etcSudangList = (List<EtcSudangTO>) sudangInfoList.get(key);
				for (EtcSudangTO etcSudangTO : etcSudangList) {
					Map<String, Object> codeColumn=new HashMap<>();
					codeColumn.put("divisionCodeNo","HR27");
					codeColumn.put("detailCode", etcSudangTO.getEtcSalCode());
					codeColumn.put("detailCodeName", etcSudangTO.getEtcSalName());
					switch (etcSudangTO.getStatus()) {
					case "inserted":
						etcSudangDAO.insertEtcSudang(etcSudangTO);
						baseApplicationService.batchDetailCodeProcess(etcSudangTO,codeColumn);
						break;
					case "updated":
						etcSudangDAO.updateEtcSudang(etcSudangTO);
						baseApplicationService.batchDetailCodeProcess(etcSudangTO,codeColumn);
						break;
					case "deleted":
						etcSudangDAO.deleteEtcSudang(etcSudangTO);
						baseApplicationService.batchDetailCodeProcess(etcSudangTO,codeColumn);
						break;
					}
				}

			}
		}
		sudangInfoList.clear();

		List<OvertimeSudangTO> overtimeSudangList = overTimeSudangDAO.selectOvertimeSudangList();
		List<EtcSudangTO> etcSudangList = etcSudangDAO.selectEtcSudangList();

		sudangInfoList.put("overtimeSudangList", overtimeSudangList);
		sudangInfoList.put("etcSudangList", etcSudangList);
		return sudangInfoList;
	}

	// 연장,야간,휴일수당목록을 가져오는 메서드
	@Override
	public List<OvertimeSudangTO> findOvertimeSudangList() {

		return overTimeSudangDAO.selectOvertimeSudangList();
	}

	// 기타수당목록을 가져오는 메서드
	@Override
	public List<EtcSudangTO> findEtcSudangList() {

		return etcSudangDAO.selectEtcSudangList();
	}
}

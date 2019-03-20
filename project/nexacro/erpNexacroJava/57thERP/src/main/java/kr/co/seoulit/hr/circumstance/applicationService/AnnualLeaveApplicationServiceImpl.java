package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;
import kr.co.seoulit.hr.circumstance.dao.AnnualLeaveDAO;


@Component
public class AnnualLeaveApplicationServiceImpl implements AnnualLeaveApplicationService{

	@Autowired
	private AnnualLeaveDAO annualLeaveDAO;


	/* 연차정보를 조회하는 메서드 */
	@Override
	public List<AnnualLeaveTO> findAnnualLeaveList() {
		return annualLeaveDAO.selectAnnualLeaveList();
	}

	// 해당년도, 해당사원의 연차정보를 생성하는 메서드
	@Override
	public List<AnnualLeaveTO> createAnnualLeave(HashMap<String, Object> map) throws ProcedureException {		
		annualLeaveDAO.createAnnualLeave(map);
		int errorCode = Integer.parseInt((String) map.get("errorCode"));
		if (errorCode < 0) {
			throw new ProcedureException((String) map.get("errorMsg"));
		}
		// 이걸 넣어서 성공 메세지를 넣고 싶은데 안된다..
		// else if(errorCode == 0){
		// throw new ProcedureException((String) map.get("errorMsg"));
		// }
		return annualLeaveDAO.selectAnnualLeaveList();
	}

	// 연차와 관련된 일괄처리를 하는 메서드
	@Override
	public void batchAnnualLeave(List<AnnualLeaveTO> annualLeaveList) {
		for (AnnualLeaveTO annualLeaveTO : annualLeaveList) {
			System.out.println(annualLeaveTO.getStatus());			
			switch (annualLeaveTO.getStatus()) {
			case "inserted":
				annualLeaveDAO.insertAnnualLeave(annualLeaveTO);
				break;// 필요 없을듯			
			case "updated":
				annualLeaveDAO.updateAnnualLeave(annualLeaveTO);
				break;// 필요 한지는 모르겠음 (막아야 될 듯)
			case "deleted":
				annualLeaveDAO.deleteAnnualLeave(annualLeaveTO);
				break;

			}
		}
	}

	// 연차를 신청할때 연차관리테이블을 update하기 위한 메서드
	@Override
	public List<AnnualLeaveTO> editAnnualLeaveMgt(HashMap<String, Object> map) {
		annualLeaveDAO.updateAnnualLeaveMgt(map);
		return annualLeaveDAO.selectAnnualLeaveList();
	}

}

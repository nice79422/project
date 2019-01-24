package com.test5th.hr.circumstance.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.common.exception.ProcedureException;
import com.test5th.hr.circumstance.dao.AnnualDAO;
import com.test5th.hr.circumstance.to.AnnualBean;

@Component
public class AnnualAppServiceImpl implements AnnualAppService {
	/* AnnualDAO setting */
	@Autowired
	private AnnualDAO annualDAO;


	/* 연차정보를 조회하는 메서드 */
	@Override
	public List<AnnualBean> findAnnualList() {
		return annualDAO.selectAnnualList();
	}

	// 해당년도, 해당사원의 연차정보를 생성하는 메서드
	@Override
	public List<AnnualBean> createAnnual(String standardDate, String empCode, String hireDate)
			throws ProcedureException {
		
		Map<String, String> map=new HashMap<>();
		map.put("standardYear", standardDate);
		map.put("empCode", empCode);
		map.put("hireDate", hireDate);
		
		annualDAO.createAnnual(map);
		int errorCode = Integer.parseInt((String) map.get("errorCode"));
		if (errorCode < 0) {
			throw new ProcedureException((String) map.get("errorMsg"));
		}
		// 이걸 넣어서 성공 메세지를 넣고 싶은데 안된다..
		// else if(errorCode == 0){
		// throw new ProcedureException((String) map.get("errorMsg"));
		// }
		return annualDAO.selectAnnualList();
	}

	// 연차와 관련된 일괄처리를 하는 메서드
	@Override
	public void batchAnnual(List<AnnualBean> annualList) {
		for (AnnualBean annualBean : annualList) {
			switch (annualBean.getStatus()) {
			case "update":
				annualDAO.updateAnnual(annualBean);
				break;
			case "deleted":
				annualDAO.deleteAnnual(annualBean);
				break;
			}
		}
	}

	// 연차를 신청할때 연차관리테이블을 update하기 위한 메서드
	@Override
	public List<AnnualBean> editAnnualMgt(String standardYear, String empCode, String days, String restDays) {
		annualDAO.updateAnnualMgt(standardYear, empCode, days, restDays);
		return annualDAO.selectAnnualList();
	}

}

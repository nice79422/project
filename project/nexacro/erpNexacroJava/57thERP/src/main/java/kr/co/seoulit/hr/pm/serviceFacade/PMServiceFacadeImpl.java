package kr.co.seoulit.hr.pm.serviceFacade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.hr.pm.applicationService.PMAppService;
import kr.co.seoulit.hr.pm.to.EmpInfoTO;
import kr.co.seoulit.hr.pm.to.EmployeeInfoTO;
import kr.co.seoulit.hr.pm.to.ReportTO;
import kr.co.seoulit.hr.pm.to.WorkInfoTO;

@Service
public class PMServiceFacadeImpl implements PMServiceFacade{
	/* PMAppService setting */
	@Autowired
	private PMAppService pmAppService;
	
	/* 사원의 모든상세정보를 가지고 오는 메서드 */
	@Override
	public EmployeeInfoTO findEmployeeInfoAll() {
		return pmAppService.findEmployeeInfoAll();
	}

	//사원관련된 정보를 일괄처리하는 메서드 
	@Override
	public void batchEmployeeInfoAll(EmployeeInfoTO employeeInfoTO) {
		 pmAppService.batchEmployeeInfoAll(employeeInfoTO);
	}

	@Override
	public List<ReportTO> findEmpInfoReport(String empCode) {
		return pmAppService.findEmpInfoReport(empCode);
	}

	@Override
	public List<EmpInfoTO> findEmpInfoList() {
		// TODO Auto-generated method stub
		return pmAppService.findEmpInfoList();
	}

	@Override
	public void batchEmpInfoList(List<EmpInfoTO> batchEmpInfo) {
		// TODO Auto-generated method stub
		pmAppService.batchEmpInfoList(batchEmpInfo);
	}

	@Override
	public List<EmpInfoTO> findEmpCode(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return pmAppService.findEmpCode(map);
	}

	@Override
	public List<WorkInfoTO> selectWorkInfoAll() {
		// TODO Auto-generated method stub
		return pmAppService.selectWorkInfoAll();
	}
	
	


}

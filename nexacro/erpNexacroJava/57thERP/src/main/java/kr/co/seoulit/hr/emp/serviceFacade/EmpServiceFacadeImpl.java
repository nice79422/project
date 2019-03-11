package kr.co.seoulit.hr.emp.serviceFacade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.hr.emp.applicationService.EmpApplicationService;
import kr.co.seoulit.hr.emp.to.EmployeeTO;


@Service
public class EmpServiceFacadeImpl implements EmpServiceFacade{

	@Autowired
	private EmpApplicationService empApplicationService;

	@Override
	public List<EmployeeTO> findEmployeeList() {
		// TODO Auto-generated method stub

		return empApplicationService.findEmployeeList();

	}

	@Override
	public void batchEmployeeList(List<EmployeeTO> employeeList) {
		// TODO Auto-generated method stub
		empApplicationService.batchEmployeeList(employeeList);
	}


}

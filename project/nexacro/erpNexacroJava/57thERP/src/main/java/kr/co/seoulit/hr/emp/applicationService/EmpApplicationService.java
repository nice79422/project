package kr.co.seoulit.hr.emp.applicationService;

import java.util.List;

import kr.co.seoulit.hr.emp.to.EmployeeTO;

public interface EmpApplicationService {

	public List<EmployeeTO> findEmployeeList();
	
	public EmployeeTO findEmployee(String empCode);
	
	public void batchEmployeeList(List<EmployeeTO> employeeList);
	
}

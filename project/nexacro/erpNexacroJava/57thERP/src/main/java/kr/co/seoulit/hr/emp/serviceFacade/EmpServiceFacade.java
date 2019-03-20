package kr.co.seoulit.hr.emp.serviceFacade;

import java.util.List;

import kr.co.seoulit.hr.emp.to.EmployeeTO;

public interface EmpServiceFacade {

	public List<EmployeeTO> findEmployeeList();

	public void batchEmployeeList(List<EmployeeTO> employeeList);


}

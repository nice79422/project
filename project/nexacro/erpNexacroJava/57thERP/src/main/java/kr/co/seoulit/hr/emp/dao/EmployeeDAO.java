package kr.co.seoulit.hr.emp.dao;

import java.util.List;

import kr.co.seoulit.hr.emp.to.EmployeeTO;

public interface EmployeeDAO {

	public List<EmployeeTO> selectEmployeeList();

	public EmployeeTO selectEmployee(String empCode);

	public void insertEmployee(EmployeeTO employee);

	public void updateEmployee(EmployeeTO employee);

	public void deleteEmployee(EmployeeTO employee);
}

package kr.co.seoulit.hr.emp.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.emp.dao.EmployeeDAO;
import kr.co.seoulit.hr.emp.to.EmployeeTO;


@Component
public class EmpApplicationServiceImpl implements EmpApplicationService{

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public List<EmployeeTO> findEmployeeList() {
		// TODO Auto-generated method stub
		return employeeDAO.selectEmployeeList();
	}

	@Override
	public void batchEmployeeList(List<EmployeeTO> employeeList) {
		// TODO Auto-generated method stub
		for(EmployeeTO employeeTO : employeeList) {
			System.out.println("&&&&&&&&&& EmployeeTO.getStatus() :  "+employeeTO.getStatus());
			switch (employeeTO.getStatus()) {

			case "inserted":
				employeeDAO.insertEmployee(employeeTO);
				break;
			case "updated":
				employeeDAO.updateEmployee(employeeTO);
				break;
			case "deleted ":
				employeeDAO.deleteEmployee(employeeTO);
				break;

			}
		}
	}

	@Override
	public EmployeeTO findEmployee(String empCode) {
		// TODO Auto-generated method stub
		return employeeDAO.selectEmployee(empCode);
	}

}

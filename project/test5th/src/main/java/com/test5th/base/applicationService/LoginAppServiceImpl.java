package com.test5th.base.applicationService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.base.dao.EmployeeDAO;
import com.test5th.base.exception.BusinessPlaceNotFoundException;
import com.test5th.base.exception.DeptNotFoundException;
import com.test5th.base.exception.EmpCodeNotFoundException;
import com.test5th.base.exception.PwMissMatchException;
import com.test5th.base.to.EmployeeBean;


@Component
public class LoginAppServiceImpl implements LoginAppService {

	@Autowired
	private EmployeeDAO employeeDAO;
   
	
	@Override
	public EmployeeBean checkLogin(String businessPlaceCode,String deptCode,String empCode,String password) throws EmpCodeNotFoundException,BusinessPlaceNotFoundException,DeptNotFoundException,PwMissMatchException {
	
		EmployeeBean employee=employeeDAO.selectEmployee(empCode);
		
		if(employee==null) {
			throw new EmpCodeNotFoundException("존재하는 사원이 없습니다.");
		}
		else {
			if(!employee.getBusinessCode().equals(businessPlaceCode)) {
				throw new BusinessPlaceNotFoundException("사원의 사업장정보가 일치하지 않습니다.");
			}
			else if(!employee.getDeptCode().equals(deptCode)) {
				throw new DeptNotFoundException("사원의 부서정보가 일치하지 않습니다.");
			}
			else {
				if(employee.getBusinessCode().equals(businessPlaceCode) && employee.getDeptCode().equals(deptCode) && employee.getPassword().equals(password)) {
				employee.setPassword("null");
					return employee;
				}
				else {
					throw new PwMissMatchException("사원의 비밀번호가 일치하지 않습니다.");
					
				}
				
			}
		}
		
		
	}


	
}

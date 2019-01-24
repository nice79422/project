package com.test5th.base.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.base.dao.EmployeeDAO;
import com.test5th.base.to.EmployeeBean;
import com.test5th.base.to.EmployeeHireDateBean;



@Component
public class BasicEmployeeAppServiceImpl implements BasicEmployeeAppService {
	/* EmployeeDAO setting */
	
	@Autowired
	private EmployeeDAO employeeDAO;
	


	/*로그인 사원정보 담기*/
	public List<EmployeeBean> findEmployeeList() {
		
		return employeeDAO.selectEmployeeList();
	}
	
	/* 사원정보를 일괄처리하는 메서드(저장) */
	@Override
	public void batchEmployee(EmployeeBean employeeBean) {
		
		employeeDAO.insertEmployee(employeeBean);
	}
	
	/*퇴직자 제외한 사원정보 담기*/
	public List<EmployeeBean> findFilterEmployeeList() {
		
		return employeeDAO.selectFilterEmployeeList();
	}
	
	/*사원기초정보 관리(수정)*/
	@Override
	public void batcEditEmployee(List<EmployeeBean> employeeList) {
		for(EmployeeBean employeeBean:employeeList){
			employeeDAO.updateEmployee(employeeBean);
		}

	}
	
	/* 모든사원의 사원정보, 재직정보를 가지고 오는 메서드 */
	@Override
	public List<EmployeeHireDateBean> findEmployeeHireDateList() {
		return employeeDAO.selectEmployeeHireDateList();
	}
	

}

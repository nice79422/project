package com.test5th.base.applicationService;

import com.test5th.base.exception.BusinessPlaceNotFoundException;
import com.test5th.base.exception.DeptNotFoundException;
import com.test5th.base.exception.EmpCodeNotFoundException;
import com.test5th.base.exception.PwMissMatchException;
import com.test5th.base.to.EmployeeBean;

public interface LoginAppService {

    public EmployeeBean checkLogin(String businessPlaceCode,String deptCode,String empCode,String password)throws EmpCodeNotFoundException,BusinessPlaceNotFoundException,DeptNotFoundException,PwMissMatchException;
    
    
}

	
		
	

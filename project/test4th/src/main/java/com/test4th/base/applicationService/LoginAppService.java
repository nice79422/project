package com.test4th.base.applicationService;

import com.test4th.base.exception.BusinessPlaceNotFoundException;
import com.test4th.base.exception.DeptNotFoundException;
import com.test4th.base.exception.EmpCodeNotFoundException;
import com.test4th.base.exception.PwMissMatchException;
import com.test4th.base.to.EmployeeBean;

public interface LoginAppService {

    public EmployeeBean checkLogin(String businessPlaceCode,String deptCode,String empCode,String password)throws EmpCodeNotFoundException,BusinessPlaceNotFoundException,DeptNotFoundException,PwMissMatchException;
    
    
}

	
		
	

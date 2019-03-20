package com.test4th.base.controller;


import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.EmployeeBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.PlatformData;

public class LoginController extends AbstractMiplatformMultiActionController{
	/* baseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
    public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
        this.baseServiceFacade = baseServiceFacade;
    }

  
	public void checkLogin(PlatformData inData, PlatformData outData) throws Exception {
		String businessPlaceCode= inData.getVariable("businessPlaceCode").getValue().asString();
		String deptCode= inData.getVariable("deptCode").getValue().asString();
		String empCode= inData.getVariable("empCode").getValue().asString();
		String password= inData.getVariable("password").getValue().asString();
		System.out.println(businessPlaceCode);
		System.out.println(deptCode);
		System.out.println(empCode);
		System.out.println(password);
		
		EmployeeBean employee=baseServiceFacade.checkLogin(businessPlaceCode,deptCode,empCode,password);
		  datasetBeanMapper.beanToDataset(outData, employee, EmployeeBean.class);
		 
    }
	

	
}

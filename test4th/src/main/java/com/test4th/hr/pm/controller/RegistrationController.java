package com.test4th.hr.pm.controller;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.pm.service.PMServiceFacade;
import com.test4th.hr.pm.to.EducationInfoBean;
import com.test4th.hr.pm.to.EmpInfoBean;
import com.test4th.hr.pm.to.EmployeeInfoBean;
import com.test4th.hr.pm.to.FamilyInfoBean;
import com.test4th.hr.pm.to.LicenseInfoBean;
import com.test4th.hr.pm.to.SalInfoBean;
import com.test4th.hr.pm.to.WorkInfoBean;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.PlatformData;


public class RegistrationController extends AbstractMiplatformMultiActionController{
	/* PMServiceFacade setting */
	private PMServiceFacade pmServiceFacade;
	public void setPmServiceFacade(PMServiceFacade pmServiceFacade) {
		this.pmServiceFacade = pmServiceFacade;
	}

	// 해당사원의 모든 사원관련 상세정보를 가져오는 메서드 
	public void findEmployeeInfoAll(PlatformData inData, PlatformData outData) throws Exception {
		EmployeeInfoBean employeeInfoBean=pmServiceFacade.findEmployeeInfoAll();
		
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getEmpInfoList(), EmpInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getWorkInfoList(), WorkInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getFamilyInfoList(), FamilyInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getLicenseInfoList(), LicenseInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getEducationInfoList(), EducationInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getSalInfoList(), SalInfoBean.class);
		
		
		
    }

	// 사원의 이미지를 저장하는 메서드 
	public void saveEmpImg(PlatformData inData, PlatformData outData) throws Exception {
		Dataset dataset = inData.getDataset("dsImg");
		FileOutputStream out = null;
		String fileName = dataset.getColumn(0, "EMP_FILE_NAME").getString();
		try {
			if (fileName != null) {
				out = new FileOutputStream("C:/apache2.2/htdocs/worldMiplatform/EMP-IMG/" + fileName);
				
				
				byte[] file = dataset.getColumnAsByteArray(0, "IMG_FILE_DATA");
				BufferedOutputStream bufferedOut = new BufferedOutputStream(out);
				bufferedOut.write(file);
				bufferedOut.flush();
				bufferedOut.close();
				out.close();
				bufferedOut = null;
				out = null;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	// 사원의 상세정보를 일괄처리하는 메서드 
	public void batchEmployeeInfoAll(PlatformData inData, PlatformData outData) throws Exception {
		System.out.println(inData);
		EmployeeInfoBean employeeInfoBean=new EmployeeInfoBean();
		employeeInfoBean.setEmpInfoList(datasetBeanMapper.datasetToBeans(inData, EmpInfoBean.class));
		employeeInfoBean.setWorkInfoList(datasetBeanMapper.datasetToBeans(inData, WorkInfoBean.class));
		employeeInfoBean.setFamilyInfoList(datasetBeanMapper.datasetToBeans(inData, FamilyInfoBean.class));
		employeeInfoBean.setLicenseInfoList(datasetBeanMapper.datasetToBeans(inData, LicenseInfoBean.class));
		employeeInfoBean.setEducationInfoList(datasetBeanMapper.datasetToBeans(inData, EducationInfoBean.class));
	
		employeeInfoBean.setSalInfoList(datasetBeanMapper.datasetToBeans(inData, SalInfoBean.class));
		
		
		pmServiceFacade.batchEmployeeInfoAll(employeeInfoBean);
		findEmployeeInfoAll(inData,outData);
    }

	
}
package kr.co.seoulit.hr.pm.applicationService;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.pm.dao.EducationInfoDAO;
import kr.co.seoulit.hr.pm.dao.EmpInfoDAO;
import kr.co.seoulit.hr.pm.dao.FamilyInfoDAO;
import kr.co.seoulit.hr.pm.dao.LicenseInfoDAO;
import kr.co.seoulit.hr.pm.dao.SalInfoDAO;
import kr.co.seoulit.hr.pm.dao.WorkInfoDAO;
import kr.co.seoulit.hr.pm.to.EducationInfoTO;
import kr.co.seoulit.hr.pm.to.EmpInfoTO;
import kr.co.seoulit.hr.pm.to.EmployeeInfoTO;
import kr.co.seoulit.hr.pm.to.FamilyInfoTO;
import kr.co.seoulit.hr.pm.to.LicenseInfoTO;
import kr.co.seoulit.hr.pm.to.SalInfoTO;
import kr.co.seoulit.hr.pm.to.WorkInfoTO;
import kr.co.seoulit.hr.pm.dao.ReportDAO;
import kr.co.seoulit.hr.pm.to.ReportTO;

@Component
public class PMAppServiceImpl implements PMAppService {
	@Autowired
	private EmpInfoDAO empInfoDAO;
	@Autowired
	private WorkInfoDAO workInfoDAO;
	@Autowired
	private FamilyInfoDAO familyInfoDAO;
	@Autowired
	private LicenseInfoDAO licenseInfoDAO;
	@Autowired
	private EducationInfoDAO educationInfoDAO;
//	@Autowired
//	private SalInfoDAO salInfoDAO;
	@Autowired
	private ReportDAO reportDAO;
	
	@Override
	/* 사원의 모든 상세정보를 가지고 오는 메서드 */
	public EmployeeInfoTO findEmployeeInfoAll() {
		EmployeeInfoTO employeeInfoTO=new EmployeeInfoTO();
		employeeInfoTO.setEmpInfoList(empInfoDAO.selectEmpInfoAll());
		employeeInfoTO.setWorkInfoList(workInfoDAO.selectWorkInfoAll());
		employeeInfoTO.setFamilyInfoList(familyInfoDAO.selectFamilyInfoAll());
		employeeInfoTO.setLicenseInfoList(licenseInfoDAO.selectLicenseInfoAll());
		employeeInfoTO.setEducationInfoList(educationInfoDAO.selectEducationInfoAll());
		//employeeInfoTO.setSalInfoList(salInfoDAO.selectSalInfoAll());
		
		return employeeInfoTO;
	}
	
	/* 사원관련정보를 일괄처리하는 메서드 */
	@Override
	public void batchEmployeeInfoAll(EmployeeInfoTO employeeInfoTO) {
		//사원상세정보 있으면 여기 실행(수정된것) [사원코드랑 연결 되어 있는 구조는 insert,delete가 안됨 update 뿐임.. insert는 기본 사원정보 넣을 때 실시 삭제는 내가 막아 놨음]
		for(EmpInfoTO EmpInfoTO :employeeInfoTO.getEmpInfoList()){
			empInfoDAO.updateEmpInfo(EmpInfoTO);
		}
		
		for(WorkInfoTO workInfoTO:employeeInfoTO.getWorkInfoList()){
			workInfoDAO.updateWorkInfo(workInfoTO);
		}
		
//		for(SalInfoTO salInfoTO:employeeInfoTO.getSalInfoList()){
//			salInfoDAO.updateSalInfo(salInfoTO);
//		}

		for(FamilyInfoTO familyInfoTO :employeeInfoTO.getFamilyInfoList()){
			switch(familyInfoTO.getStatus()){
				case "insert" : familyInfoDAO.insertFamilyInfo(familyInfoTO); break;
				case "update" : familyInfoDAO.updateFamilyInfo(familyInfoTO); break;
				case "delete" : familyInfoDAO.deleteFamilyInfo(familyInfoTO); break;
			}
		}
		
		for(LicenseInfoTO licenseInfoTO :employeeInfoTO.getLicenseInfoList()){
			switch(licenseInfoTO.getStatus()){
				case "insert" : licenseInfoDAO.insertLicenseInfo(licenseInfoTO); break;
				case "update" : licenseInfoDAO.updateLicenseInfo(licenseInfoTO); break;
				case "delete" : licenseInfoDAO.deleteLicenseInfo(licenseInfoTO); break;
			}
		}
		
		
		for(EducationInfoTO educationInfoTO :employeeInfoTO.getEducationInfoList()){
			switch(educationInfoTO.getStatus()){
				case "insert" : educationInfoDAO.insertEducationInfo(educationInfoTO); break;
				case "update" : educationInfoDAO.updateEducationInfo(educationInfoTO); break;
				case "delete" : educationInfoDAO.deleteEducationInfo(educationInfoTO); break;
			}
		}
	
	}
	
	@Override
    public List<ReportTO> findEmpInfoReport(String empCode) {
		return reportDAO.selectReport(empCode);
    }

	@Override
	public List<EmpInfoTO> findEmpInfoList() {
		// TODO Auto-generated method stub
		return empInfoDAO.selectEmpInfoAll();
	}

	@Override
	public void batchEmpInfoList(List<EmpInfoTO> batchEmpInfo) {
		// TODO Auto-generated method stub
			for(EmpInfoTO empinfo : batchEmpInfo) {

				switch (empinfo.getStatus()) {

				case "inserted":
					empInfoDAO.insertEmpinfo(empinfo);
					break;
				case "updated":
					empInfoDAO.updateEmpInfo(empinfo);
					break;
				case "deleted":
					empInfoDAO.deleteEmpinfo(empinfo);
					break;

				}
			}
		}

	@Override
	public List<EmpInfoTO> findEmpCode(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return empInfoDAO.selectEmpInfoList(map);
	}

	// Work Information 리스트 조회
	@Override
	public List<WorkInfoTO> selectWorkInfoAll() { 
		// TODO Auto-generated method stub
		return workInfoDAO.selectWorkInfoAll();
	}


}


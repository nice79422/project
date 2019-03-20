package com.test5th.base.serviceFacade;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test5th.base.applicationService.BasicDeptAppService;
import com.test5th.base.applicationService.BasicEmployeeAppService;
import com.test5th.base.applicationService.BasicSalaryAppService;
import com.test5th.base.applicationService.BusinessPlaceAppService;
import com.test5th.base.applicationService.CodeAppService;
import com.test5th.base.applicationService.CompanyAppService;
import com.test5th.base.applicationService.LoginAppService;
import com.test5th.base.applicationService.MenuAppService;
import com.test5th.base.applicationService.PositionAppService;
import com.test5th.base.exception.BusinessPlaceNotFoundException;
import com.test5th.base.exception.DeptNotFoundException;
import com.test5th.base.exception.EmpCodeNotFoundException;
import com.test5th.base.exception.PwMissMatchException;
import com.test5th.base.to.BusinessPlaceBean;
import com.test5th.base.to.CodeBean;
import com.test5th.base.to.CodeInfoBean;
import com.test5th.base.to.CompanyBean;
import com.test5th.base.to.DepartmentBean;
import com.test5th.base.to.DetailCodeBean;
import com.test5th.base.to.EmployeeBean;
import com.test5th.base.to.EmployeeHireDateBean;
import com.test5th.base.to.EtcSalBean;
import com.test5th.base.to.MenuBean;
import com.test5th.base.to.OvertimeSalBean;
import com.test5th.base.to.PositionBean;
import com.test5th.base.to.SudangInfoBean;

@Service
public class BaseServiceFacadeImpl implements BaseServiceFacade{
    
	@Autowired
	private BasicDeptAppService basicDeptAppService;
	@Autowired
	private BasicEmployeeAppService basicEmployeeAppService;
	@Autowired
	private MenuAppService menuAppService;
	@Autowired
	private PositionAppService positionAppService;
	@Autowired
	private CodeAppService codeAppService;
	@Autowired
	private BasicSalaryAppService basicSalaryAppService;
	@Autowired
	private CompanyAppService companyAppService;
	@Autowired
	private BusinessPlaceAppService businessPlaceAppService;
	@Autowired
	private LoginAppService loginAppService;
	
	
	
	/* 부서코드목록을 반환하는 메서드 */
	@Override
	public List<DepartmentBean> findDeptList() {
		return basicDeptAppService.findDeptList();
	}

	/*로그인시 사원정보 얻어 옴*/
	public List<EmployeeBean> findEmployeeList() {
		
		return basicEmployeeAppService.findEmployeeList();
	}

	/* menu 목록을 들고오는 메서드 */
	@Override
	public List<MenuBean> findMenuList() {
		return menuAppService.findMenuList();
	}
	

	
	
	/* 직급목록을 가져오는 메서드 */
	@Override
	public List<PositionBean> findPositionList() {
		return positionAppService.findPositionList();
	}
	
	/*호봉목록을 가져오는 메서드*/
	public PositionBean findPosition(String positionCode) {
		return positionAppService.findPosition(positionCode);
	}
	
	

	
	/* 사원정보를 일괄저장하는 메서드 */
	@Override
	public void batchEmployee(EmployeeBean employeeBean) {
		basicEmployeeAppService.batchEmployee(employeeBean);
	}
	
	/*퇴직한 사원을 제외한 사원 조회*/
	public List<EmployeeBean> findFilterEmployeeList() {
		
		return basicEmployeeAppService.findFilterEmployeeList();
	}
	
	/*사원기초정보 수정*/
	@Override
	public void batcEditEmployee(List<EmployeeBean> employeeList) {
		basicEmployeeAppService.batcEditEmployee(employeeList);

	}
	
	/*부서정보 수정*/
	@Override
	public void batchDept(List<DepartmentBean> deptList) {
		basicDeptAppService.batchDept(deptList);
	}
	
	/* 코드목록을 반환하는 메서드 */
	@Override
	public List<CodeBean> findCodeList() {
		return codeAppService.findCodeList();
	}
	
	/* 코드관련된 정보를 일괄적으로 처리하는 메서드(코드 + 상세코드) */
	@Override
	public void batchCode(CodeInfoBean codeInfoBean) {
		codeAppService.batchCode(codeInfoBean);
	}
	/* 코드상세관련 정보를 일괄적으로 처리하는 메서드 (상세코드)*/
	@Override
	public void batchDetailCode(List<DetailCodeBean> codeDetailList) {
		codeAppService.batchDetailCode(codeDetailList);
	}
	

	
	/* 모든사원의 사원정보, 재직정보를 가지고 오는 메서드 */
	@Override
	public List<EmployeeHireDateBean> findEmployeeHireDateList() {
		return basicEmployeeAppService.findEmployeeHireDateList();
	}
	
	
	/* 연장,야간,휴일수당목록을 가져오는 메서드 */
	@Override
	public List<OvertimeSalBean> findOvertimeSalList() {
		return basicSalaryAppService.findOvertimeSalList();
	}
	/* 기타수당목록을 가져오는 메서드 */
	@Override
	public List<EtcSalBean> findEtcSalList() {
		return basicSalaryAppService.findEtcSalList();
	}
	/*기타 수당 연장수당 등록*/
	@Override
	public void batchSudang(SudangInfoBean sudangInfoBean) {
		basicSalaryAppService.batchSudang(sudangInfoBean);

	}
    /*회사정보 가져오는 메서드*/
	@Override
	public CompanyBean findCompany() {
		return companyAppService.findCompany();
	}

	@Override
	public void batchCompany(CompanyBean companyBean) {

		companyAppService.batchCompany(companyBean);
	}

	@Override
	public List<BusinessPlaceBean> findBusinessPlaceList() {
		 return businessPlaceAppService.findBusinessPlaceList();
	}

	@Override
	public void batchBusinessPlaceList(List<BusinessPlaceBean> businessPlaceList) {
	   
		businessPlaceAppService.batchBusinessPlaceList(businessPlaceList);
	
	}
	
	public EmployeeBean checkLogin(String businessPlaceCode,String deptCode,String empCode,String password)throws EmpCodeNotFoundException,BusinessPlaceNotFoundException,DeptNotFoundException,PwMissMatchException {
		
		return loginAppService.checkLogin(businessPlaceCode,deptCode,empCode,password);
		
	}

}

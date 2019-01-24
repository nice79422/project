package com.test5th.base.serviceFacade;

import java.util.List;

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



public interface BaseServiceFacade {
	/* 부서 */
	public List<DepartmentBean> findDeptList();
	public void batchDept(List<DepartmentBean> deptList);

	/*사원*/
	public List<EmployeeBean> findEmployeeList();
	public void batchEmployee(EmployeeBean employeeBean);
	public List<EmployeeBean> findFilterEmployeeList();
	public void batcEditEmployee(List<EmployeeBean> employeeList); /*사원기초정보관리 (수정)*/
	public List<EmployeeHireDateBean> findEmployeeHireDateList();/*사원의연차 정보가져 오기 위해서 사원목록 필요함 */
	
	/* 권한및메뉴 */
	public List<MenuBean> findMenuList();	

	
	/* 직급 */
	public List<PositionBean> findPositionList();
	
	/* 호봉 */
	public PositionBean findPosition(String positionCode);
	

	
	
	/*코드*/
	public List<CodeBean> findCodeList();
	public void batchCode(CodeInfoBean codeInfoBean);
	public void batchDetailCode(List<DetailCodeBean> codeDetailList);
	
	
	/*수당*/
	public List<OvertimeSalBean> findOvertimeSalList();
	public List<EtcSalBean> findEtcSalList();
	public void batchSudang(SudangInfoBean sudangInfoBean);
	
	/*회사*/
	public CompanyBean findCompany();
	public void batchCompany(CompanyBean companyBean);
	
	/*사업장*/
	public List<BusinessPlaceBean> findBusinessPlaceList();
	public void batchBusinessPlaceList(List<BusinessPlaceBean> businessPlaceList);
	
	/*로그인*/
	public EmployeeBean checkLogin(String businessPlaceCode,String deptCode,String empCode,String password)throws EmpCodeNotFoundException,BusinessPlaceNotFoundException,DeptNotFoundException,PwMissMatchException;

}

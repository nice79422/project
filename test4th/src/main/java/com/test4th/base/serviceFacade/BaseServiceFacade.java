package com.test4th.base.serviceFacade;

import java.util.List;
import java.util.Map;

import com.test4th.base.to.AddressBean;
import com.test4th.base.to.AuthorityBean;
import com.test4th.base.to.AuthorityInfoBean;
import com.test4th.base.to.CodeBean;
import com.test4th.base.to.CodeInfoBean;
import com.test4th.base.to.DepartmentBean;
import com.test4th.base.to.DetailCodeBean;
import com.test4th.base.to.EmployeeBean;
import com.test4th.base.to.EmployeeHireDateBean;
import com.test4th.base.to.EtcSalBean;
import com.test4th.base.to.MenuAuthorityBean;
import com.test4th.base.to.MenuBean;
import com.test4th.base.to.OvertimeSalBean;
import com.test4th.base.to.PositionBean;
import com.test4th.base.to.SidoBean;
import com.test4th.base.to.SigunguBean;
import com.test4th.base.to.SudangInfoBean;



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
	public List<MenuAuthorityBean> findMenuAuthorityList(String authorityCode); //로그인 사원의 권한
	public List<MenuAuthorityBean> findMenuAuthorityListAll();//권한 수정 관리시
	public void batchAuthority(AuthorityInfoBean authorityInfoBean);
	
	/* 직급 */
	public List<PositionBean> findPositionList();
	
	/*권한목록*/
	public List<AuthorityBean> findAuthorityList();
	
	
	/*코드*/
	public List<CodeBean> findCodeList();
	public void batchCode(CodeInfoBean codeInfoBean);
	public void batchDetailCode(List<DetailCodeBean> codeDetailList);
	
	/* 주소 */
	public List<SidoBean> findSido();
	
	public List<SigunguBean> findSiGunGuList(String sido);
	public List<AddressBean> findAddrList(Map<String, Object> addrList);
	
	/*수당*/
	public List<OvertimeSalBean> findOvertimeSalList();
	public List<EtcSalBean> findEtcSalList();
	public void batchSudang(SudangInfoBean sudangInfoBean);

}

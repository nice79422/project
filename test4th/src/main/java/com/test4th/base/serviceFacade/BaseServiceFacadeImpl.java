package com.test4th.base.serviceFacade;

import java.util.List;
import java.util.Map;

import com.test4th.base.applicationService.AuthorityAppService;
import com.test4th.base.applicationService.BasicDeptAppService;
import com.test4th.base.applicationService.BasicEmployeeAppService;
import com.test4th.base.applicationService.BasicSalaryAppService;
import com.test4th.base.applicationService.CodeAppService;
import com.test4th.base.applicationService.MenuAppService;
import com.test4th.base.applicationService.PositionAppService;
import com.test4th.base.applicationService.PostAppService;
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


public class BaseServiceFacadeImpl implements BaseServiceFacade{

	private BasicDeptAppService basicDeptAppService;
	
	private BasicEmployeeAppService basicEmployeeAppService;
	
	private MenuAppService menuAppService;
	
	private AuthorityAppService authorityAppService;
	
	private PositionAppService positionAppService;
	
	private CodeAppService codeAppService;

	private PostAppService postAppService;

	private BasicSalaryAppService basicSalaryAppService;
	

	public void setBasicDeptAppService(BasicDeptAppService basicDeptAppService) {
		this.basicDeptAppService = basicDeptAppService;
	}

	public void setBasicEmployeeAppService(BasicEmployeeAppService basicEmployeeAppService) {
		this.basicEmployeeAppService = basicEmployeeAppService;
	}
	
	public void setMenuAppService(MenuAppService menuAppService) {
		this.menuAppService = menuAppService;
	}
	
	public void setAuthorityAppService(AuthorityAppService authorityAppService) {
		this.authorityAppService = authorityAppService;
	}
	
	public void setPositionAppService(PositionAppService positionAppService) {
		this.positionAppService = positionAppService;
	}
	
	public void setCodeAppService(CodeAppService codeAppService) {
		this.codeAppService = codeAppService;
	}
	
	public void setPostAppService(PostAppService postAppService) {
		this.postAppService = postAppService;
	}
	
	public void setBasicSalaryAppService(BasicSalaryAppService basicSalaryAppService) {
		this.basicSalaryAppService = basicSalaryAppService;
	}

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
	
	/* 권한에따른 메뉴목록을 들고오는 메서드 */
	@Override
	public List<MenuAuthorityBean> findMenuAuthorityList(String authorityCode) {
		return authorityAppService.findMenuAuthorityList(authorityCode);
	}
	
	
	/* 직급목록을 가져오는 메서드 */
	@Override
	public List<PositionBean> findPositionList() {
		return positionAppService.findPositionList();
	}
	
	/* 권한목록을 가져오는 메서드 */
	@Override
	public List<AuthorityBean> findAuthorityList() {
		return authorityAppService.findAuthorityList();
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
	
	/* 모든권한의 메뉴를 다 가져오는 메서드 */
	@Override
	public List<MenuAuthorityBean> findMenuAuthorityListAll() {
		return authorityAppService.findMenuAuthorityListAll();
	}
	
	/* 권한, 메뉴관련된 일괄처리를 하는 메서드 */
	@Override
	public void batchAuthority(AuthorityInfoBean authorityInfoBean) {
		authorityAppService.batchAuthority(authorityInfoBean);
	}
	
	/* 특별시,광역시,도를 조회하는 메서드 */
	@Override
	public List<SidoBean> findSido() {
		return postAppService.findSido();
	}
	/* 특별시,광역시,도에 해당하는 시,군,구를 조회하는 메서드 */
	@Override
	public List<SigunguBean> findSiGunGuList(String sido) {
		return postAppService.findSiGunGuList(sido);
	}
	/* 전체조건에 맞는 주소를 가지고 오는 메서드 */
	@Override
	public List<AddressBean> findAddrList(Map<String, Object> addrList) {
		return postAppService.findAddrList(addrList);
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

}

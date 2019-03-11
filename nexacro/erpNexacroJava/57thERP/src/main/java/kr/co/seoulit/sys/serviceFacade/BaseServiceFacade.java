package kr.co.seoulit.sys.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.hr.emp.to.EmployeeTO;
import kr.co.seoulit.sys.exception.DeptCodeNotFoundException;
import kr.co.seoulit.sys.exception.IdNotFoundException;
import kr.co.seoulit.sys.exception.PwMissmatchException;
import kr.co.seoulit.sys.to.AuthorityTO;
import kr.co.seoulit.sys.to.BaseYearTO;
import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeDetailTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.CompanyTO;
import kr.co.seoulit.sys.to.CustomerTO;
import kr.co.seoulit.sys.to.DepartmentTO;
import kr.co.seoulit.sys.to.MenuAuthorityTO;
import kr.co.seoulit.sys.to.MenuTO;

public interface BaseServiceFacade {
	
	public void batchCustomer(List<CustomerTO> batchCustomerList);
	//거래처 배치프로세스
	public void batchBusinessPlace(List<BusinessPlaceTO> batchBusinessPlaceList);
	//사업처 배치프로세스
	public void updateCompanyInfo(List<CompanyTO> companyInfo);
	//회사정보 수정
	public void batchDept(List<DepartmentTO> batchDeptList);
	//부서 배치프로세스
	public List<DepartmentTO> findAllDeptList();
	//부서 전체조회
	public List<CustomerTO> findCustomerList();
	//거래처조회
	public List<CompanyTO> findCompanyList();
	// 회사정보 조회
	public List<CodeTO> findCodeList();
	// 코드리스트 조회

	public List<CodeDetailTO> findCodeDetailList();
	// 상세코드리스트 조회
	public List<BaseYearTO> findBaseYearList(Map<String,Object> vList);
	//기초연도조회
	public void batchDetailCodeProcess(List<CodeDetailTO> codeDetailList);
	// DetailCode 변경저장

	public List<MenuTO> findMenuList();

	public String findSequenceNo(String findSeq);

	public HashMap<String, Object> accessToAuthority(HashMap<String, Object> loginInfo) throws IdNotFoundException, PwMissmatchException, DeptCodeNotFoundException;

	public List<BusinessPlaceTO> findBusinessPlaceList();

	public List<DepartmentTO> findDepartmentList(Map<String,Object> vList);

	public List<AuthorityTO> findAuthorityList();

	/*로그인*/
	public EmployeeTO checkLogin(HashMap<String, Object> loginInfo) throws IdNotFoundException, PwMissmatchException, DeptCodeNotFoundException;
	public List<MenuAuthorityTO> findMenuAuthorityList(String authorityCode); //로그인 사원의 권한
	
	public String takeSerialCodeTest(HashMap<String, Object> findSeq);
}

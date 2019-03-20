package kr.co.seoulit.sys.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.nexacro.xapi.data.PlatformData;

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

public interface BaseApplicationService {

	public void batchCustomer(List<CustomerTO> batchCustomerList);
	//거래처 배치프로세스
	public void batchBusinessPlace(List<BusinessPlaceTO> batchBusinessPlaceList);
	//사업처 배치프로세스
	public void updateCompanyInfo(List<CompanyTO> companyInfo);
	//회사정보 수정
	public void batchDept(List<DepartmentTO> batchDeptList);
	//부서 배치프로세스
	public List<DepartmentTO> findAllDeptList();
	//부서전체조회
	public List<CustomerTO> findCustomerList();
	//거래처 조회
	public List<CodeTO> findCodeList();
	// 코드리스트 조회
	public List<CodeDetailTO> findCodeDetailList();
	// 상세코드리스트 조회
	public List<BaseYearTO> findBaseYearList(Map<String,Object> vList);
	//기초연도조회
	public void batchDetailCodeProcess(List<CodeDetailTO> codeDetailList);
	// DetailCode 변경저장
	public <T> void batchDetailCodeProcess(T bean,Map<String,Object> codeColumn);
	// DetailCode 외부패키지에서 온것 변경저장
/*Map구성 예제
 * 					Map<String, Object> codeColumn=new HashMap<>();
					codeColumn.put("divisionCodeNo","HR27"); 디비전코드
					codeColumn.put("detailCode", etcSudangTO.getEtcSalCode()); 입력될 코드
					codeColumn.put("detailCodeName", etcSudangTO.getEtcSalName()); 입력될 코드이름
 * */
	public List<MenuTO> findMenuList();

	public String findSequenceNo(String findSeq);

	public HashMap<String, Object> accessToAuthority(HashMap<String, Object> loginInfo) throws IdNotFoundException,DeptCodeNotFoundException,PwMissmatchException, DataAccessException;

	public List<BusinessPlaceTO> findBusinessPlaceList();

	public List<DepartmentTO> findDepartmentList(Map<String,Object> vList);

	public EmployeeTO checkLogin(HashMap<String, Object> loginInfo) throws IdNotFoundException,DeptCodeNotFoundException,PwMissmatchException, DataAccessException;

	public List<MenuAuthorityTO> findMenuAuthorityList(String authorityCode); //로그인 사원의 권한

	public List<CompanyTO> findCompanyList();
	
	public String takeSerialCodeTest(HashMap<String, Object> findSeq);
}

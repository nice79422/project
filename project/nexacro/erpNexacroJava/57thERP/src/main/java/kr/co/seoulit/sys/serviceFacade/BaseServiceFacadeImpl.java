package kr.co.seoulit.sys.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.hr.emp.to.EmployeeTO;
import kr.co.seoulit.sys.applicationService.AuthorityAppService;
import kr.co.seoulit.sys.applicationService.BaseApplicationService;
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


@Service
public class BaseServiceFacadeImpl implements BaseServiceFacade{
	@Autowired
	private BaseApplicationService baseApplicationService;

	@Autowired
	private AuthorityAppService authorityAppService;

	@Override
	public void batchCustomer(List<CustomerTO> batchCustomerList) {
		baseApplicationService.batchCustomer(batchCustomerList);
	}
	
	@Override
	public void batchBusinessPlace(List<BusinessPlaceTO> batchBusinessPlaceList) {
		baseApplicationService.batchBusinessPlace(batchBusinessPlaceList);
	}

	@Override
	public void updateCompanyInfo(List<CompanyTO> companyInfo) {
		baseApplicationService.updateCompanyInfo(companyInfo);
	}

	@Override
	public void batchDept(List<DepartmentTO> batchDeptList) {
		baseApplicationService.batchDept(batchDeptList);
	}

	@Override
	public List<DepartmentTO> findAllDeptList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findAllDeptList();
	}


	@Override
	public List<CustomerTO> findCustomerList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findCustomerList();
	}
	@Override
	public List<CompanyTO> findCompanyList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findCompanyList();
	}

	@Override
	public List<CodeTO> findCodeList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findCodeList();
	}

	@Override
	public List<CodeDetailTO> findCodeDetailList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findCodeDetailList();
	}


	@Override
	public void batchDetailCodeProcess(List<CodeDetailTO> codeDetailList) {
		// TODO Auto-generated method stub
		baseApplicationService.batchDetailCodeProcess(codeDetailList);
	}


	@Override
	public List<MenuTO> findMenuList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findMenuList();
	}


	@Override
	public String findSequenceNo(String findSeq) {
		// TODO Auto-generated method stub
		return baseApplicationService.findSequenceNo(findSeq);
	}


	@Override
	public HashMap<String, Object> accessToAuthority(HashMap<String, Object> loginInfo)
			throws IdNotFoundException, PwMissmatchException, DeptCodeNotFoundException {
		// TODO Auto-generated method stub
		return baseApplicationService.accessToAuthority(loginInfo);
	}

	@Override
	public List<BusinessPlaceTO> findBusinessPlaceList() {
		// TODO Auto-generated method stub
		return baseApplicationService.findBusinessPlaceList();
	}

	@Override
	public List<DepartmentTO> findDepartmentList(Map<String,Object> vList) {
		// TODO Auto-generated method stub
		return baseApplicationService.findDepartmentList(vList);
	}

	@Override
	public EmployeeTO checkLogin(HashMap<String, Object> loginInfo)
			throws IdNotFoundException, PwMissmatchException, DeptCodeNotFoundException {
		// TODO Auto-generated method stub
		return baseApplicationService.checkLogin(loginInfo);
	}

	@Override
	public List<MenuAuthorityTO> findMenuAuthorityList(String authorityCode) {
		// TODO Auto-generated method stub
		return authorityAppService.findMenuAuthorityList(authorityCode);
	}

	@Override
	public List<BaseYearTO> findBaseYearList(Map<String,Object> vList) {
		// TODO Auto-generated method stub
		return baseApplicationService.findBaseYearList(vList);
	}

	@Override
	public List<AuthorityTO> findAuthorityList() {
		// TODO Auto-generated method stub
		return authorityAppService.findAuthorityList();
	}

	@Override
	public String takeSerialCodeTest(HashMap<String, Object> findSeq) {
		// TODO Auto-generated method stub
		return baseApplicationService.takeSerialCodeTest(findSeq);
	}

}

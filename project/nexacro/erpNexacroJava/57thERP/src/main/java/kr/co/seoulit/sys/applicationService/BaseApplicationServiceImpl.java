
package kr.co.seoulit.sys.applicationService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.emp.applicationService.EmpApplicationService;
import kr.co.seoulit.hr.emp.to.EmployeeTO;
import kr.co.seoulit.sys.dao.CodeDAO;
import kr.co.seoulit.sys.dao.CodeDetailDAO;
import kr.co.seoulit.sys.dao.CompanyDAO;
import kr.co.seoulit.sys.dao.CustomerDAO;
import kr.co.seoulit.sys.dao.DepartmentDAO;
import kr.co.seoulit.sys.dao.MenuDAO;
import kr.co.seoulit.sys.dao.SequenceDAO;
import kr.co.seoulit.sys.dao.AuthorityDAO;
import kr.co.seoulit.sys.dao.BaseYearDAO;
import kr.co.seoulit.sys.dao.BusinessPlaceDAO;
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

@Component
public class BaseApplicationServiceImpl implements BaseApplicationService {

	@Autowired
	private CustomerDAO customerDAO;
	@Autowired
	private CodeDAO codeDAO;
	@Autowired
	private CodeDetailDAO codeDetailDAO;
	@Autowired
	private MenuDAO menuDAO;
	@Autowired
	private BusinessPlaceDAO businessPlaceDAO;
	@Autowired
	private DepartmentDAO departmentDAO;
	@Autowired
	private AuthorityDAO authorityDAO;
	@Autowired
	private BaseYearDAO baseYearDAO;
	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private EmpApplicationService empApplicationService;
	@Autowired
	private	SequenceDAO sequenceDAO;

	@Override
	public void batchCustomer(List<CustomerTO> batchCustomerList) {
		for (CustomerTO customerTO : batchCustomerList) {
			switch (customerTO.getStatus()) {
			case "inserted":
				customerDAO.insertCustomer(customerTO);
				break;
			case "updated":
				customerDAO.updateCustomer(customerTO);
				break;
			case "deleted":
				customerDAO.deleteCustomer(customerTO);
				break;
			}
		}
	}
	
	@Override
	public void batchBusinessPlace(List<BusinessPlaceTO> batchbusinessPlaceList) {
		for (BusinessPlaceTO businessPlaceTO : batchbusinessPlaceList) {
			switch (businessPlaceTO.getStatus()) {
			case "inserted":
				businessPlaceDAO.insertBusinessPlace(businessPlaceTO);
				break;
			case "updated":
				businessPlaceDAO.updateBusinessPlace(businessPlaceTO);
				break;
			case "deleted":
				businessPlaceDAO.deleteBusinessPlace(businessPlaceTO);
				break;
			}
		}
	}

	@Override
	public void updateCompanyInfo(List<CompanyTO> companyInfo) {
		for (CompanyTO companyTO : companyInfo) {
			companyDAO.updateCompanyInfo(companyTO);
		}
	}

	@Override
	public void batchDept(List<DepartmentTO> batchDeptList) {
		for (DepartmentTO departmentTO : batchDeptList) {
			switch (departmentTO.getStatus()) {
			case "inserted":
				departmentDAO.insertDept(departmentTO);
				break;
			case "updated":
				departmentDAO.updateDept(departmentTO);
				break;
			case "deleted":
				departmentDAO.deleteDept(departmentTO);
				break;
			}
		}
	}

	@Override
	public List<DepartmentTO> findAllDeptList() {
		// TODO Auto-generated method stub
		return departmentDAO.selectAllDeptList();
	}

	@Override
	public List<CustomerTO> findCustomerList() {
		// TODO Auto-generated method stub
		return customerDAO.selectCustomerList();
	}

	@Override
	public List<CodeTO> findCodeList() {
		// TODO Auto-generated method stub
		return codeDAO.selectCodeList();
	}

	@Override
	public List<CodeDetailTO> findCodeDetailList() {
		// TODO Auto-generated method stub
		return codeDetailDAO.selectCodeDetailList();
	}

	@Override
	public void batchDetailCodeProcess(List<CodeDetailTO> codeDetailList) {
		// TODO Auto-generated method stub
		for (CodeDetailTO codeDetailTO : codeDetailList) {
			System.out.println(codeDetailTO.getStatus());	
			switch (codeDetailTO.getStatus()) {
			case "inserted":
				codeDetailDAO.insertDetailCode(codeDetailTO);
				break;
			case "updated":
				codeDetailDAO.updateDetailCode(codeDetailTO);
				break;
			case "deleted":
				codeDetailDAO.deleteDetailCode(codeDetailTO);
				break;
			}
		}
	}

	@Override
	public List<MenuTO> findMenuList() {
		// TODO Auto-generated method stub
		return menuDAO.selectMenuList();
	}

	@Override
	public String findSequenceNo(String findSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> takeSeq = new HashMap<String, Object>();
		takeSeq.put("findSeq", findSeq);
		return sequenceDAO.selectSequenceNo(takeSeq);
	}

	@Override
	public HashMap<String, Object> accessToAuthority(HashMap<String, Object> loginInfo)
			throws IdNotFoundException, DeptCodeNotFoundException, PwMissmatchException, DataAccessException {
		// TODO Auto-generated method stub

		EmployeeTO empTo = null;
		List<MenuTO> masterMenuList = null;
		HashMap<String, Object> result = new HashMap<String, Object>();
		empTo = empApplicationService.findEmployee((String) loginInfo.get("empCode"));
		if (empTo == null) {
			throw new IdNotFoundException(" 존재 하지 않는 계정입니다. ");
		} else {
			if (!(empTo.getDeptCode().equals((String) loginInfo.get("deptCode")))) {
				throw new DeptCodeNotFoundException("존재하지않는 부서입니다");
			} else {
				if (empTo.getPassword().equals((String) loginInfo.get("password"))) {
					masterMenuList = menuDAO.selectMenuList();
					result.put("employeeTO", empTo);
					result.put("masterMenuList", masterMenuList);

				} else {

					throw new PwMissmatchException(" 비밀번호가 틀립니다. ");
				}

			}

		}
		return result;
	}

	@Override
	public List<BusinessPlaceTO> findBusinessPlaceList() {
		// TODO Auto-generated method stub
		return businessPlaceDAO.selectBusinessPlaceList();
	}

	@Override
	public List<DepartmentTO> findDepartmentList(Map<String, Object> vList) {
		// TODO Auto-generated method stub
		return departmentDAO.selectDepartmentList(vList);
	}

	@Override
	public EmployeeTO checkLogin(HashMap<String, Object> loginInfo)
			throws IdNotFoundException, DeptCodeNotFoundException, PwMissmatchException, DataAccessException {
		// TODO Auto-generated method stub
		EmployeeTO empTo = null;
		// List<MenuTO> masterMenuList = null;
		// HashMap<String, Object> result = new HashMap<String, Object>();
		empTo = empApplicationService.findEmployee((String) loginInfo.get("empCode"));
		if (empTo == null) {
			throw new IdNotFoundException(" 존재 하지 않는 계정입니다. ");
		} else {
			if (!(empTo.getDeptCode().equals((String) loginInfo.get("deptCode")))) {
				throw new DeptCodeNotFoundException("존재하지않는 부서입니다");
			} else {
				if (empTo.getPassword().equals((String) loginInfo.get("password"))) {
					return empTo;
					// masterMenuList = menuDAO.selectMenuList();
					// result.put("employeeTO", empTo);
					// result.put("masterMenuList", masterMenuList);
				} else {
					throw new PwMissmatchException(" 비밀번호가 틀립니다. ");
				}
			}
		}
	}

	@Override
	public List<MenuAuthorityTO> findMenuAuthorityList(String authorityCode) {
		// TODO Auto-generated method stub
		return authorityDAO.selectMenuAuthorityList(authorityCode);
	}

	@Override
	public List<BaseYearTO> findBaseYearList(Map<String, Object> vList) {
		// TODO Auto-generated method stub
		return baseYearDAO.selectBaseYearList(vList);
	}

	@Override
	public List<CompanyTO> findCompanyList() {
		// TODO Auto-generated method stub
		return companyDAO.selectCompanyList();
	}

	@Override
	public <T> void batchDetailCodeProcess(T TO, Map<String, Object> codeColumn) {
		// TODO Auto-generated method stub
		try {
			CodeDetailTO codeDetailTO = new CodeDetailTO();
			Method method = TO.getClass().getMethod("getStatus");
			codeDetailTO.setStatus((String) method.invoke(TO));
			for (String key : codeColumn.keySet()) {
				String colValue = (String) codeColumn.get(key);
				if (colValue != null) {
						switch (key) {
					case "divisionCodeNo":
						codeDetailTO.setDivisionCodeNo(colValue);
						break;
					case "detailCode":
						codeDetailTO.setDetailCode(colValue);
						break;
					case "detailCodeName":
						codeDetailTO.setDetailCodeName(colValue);
						break;
					}
				}
			}
			switch (codeDetailTO.getStatus()) {
			case "inserted":
				codeDetailDAO.insertDetailCode(codeDetailTO);
				break;
			case "deleted":
				codeDetailDAO.deleteDetailCode(codeDetailTO);
				break;
			case "updated":
				codeDetailDAO.updateDetailCode(codeDetailTO);
				break;
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String takeSerialCodeTest(HashMap<String, Object> findSeq) {
		// TODO Auto-generated method stub
		return codeDAO.takeSerialCodeTest(findSeq);
	}

}

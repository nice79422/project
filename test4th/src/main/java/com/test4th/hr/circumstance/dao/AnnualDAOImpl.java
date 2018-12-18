package com.test4th.hr.circumstance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.circumstance.to.AnnualBean;

public class AnnualDAOImpl extends IBatisDAO implements AnnualDAO {

	/* 연차테이블의 정보를 조회하는 메서드 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<AnnualBean> selectAnnualList() {
		return getSqlMapClientTemplate().queryForList("annual.selectAnnualList");
	}

	
	// 해당년도, 해당사원의 연차를 생성하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> createAnnual(String standardYear,String empCode,String hireDate){
		Map<String, Object> map = new HashMap<>();
	       map.put("standardYear", standardYear);
	       map.put("empCode", empCode);
	       map.put("hireDate", hireDate);
	       getSqlMapClientTemplate().queryForObject("annual.createAnnual",map);
		return map;
	}
		
		
	
	
	

	// 연차관리화면에서 해당사원의 해당연차를 삭제하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void deleteAnnual(AnnualBean annualBean) {
		getSqlMapClientTemplate().delete("annual.deleteAnnual",annualBean);
	}

	// 연차관리테이블에서 연차를 수정하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void updateAnnual(AnnualBean annualBean) {
		getSqlMapClientTemplate().update("annual.updateAnnual",annualBean);
	}
	
	// 연차신청시 연차관리테이블을 update하기 위한 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void updateAnnualMgt(String standardYear,String empCode,String days,String restDays) {
		Map<String, Object> map = new HashMap<>();
        map.put("standardYear", standardYear);
        map.put("empCode", empCode);
        map.put("days", days);
        map.put("restDays", restDays);
		getSqlMapClientTemplate().update("annual.updateAnnualMgt",map);
	}
	
}
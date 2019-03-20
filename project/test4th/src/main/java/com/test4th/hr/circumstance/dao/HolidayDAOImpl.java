package com.test4th.hr.circumstance.dao;

import java.util.List;
import java.util.Map;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.circumstance.to.HolidayBean;

public class HolidayDAOImpl extends IBatisDAO implements HolidayDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 휴일목록을 가지고 오는 메서드 */
	public List<HolidayBean> selectHolidayList(Map<String, Object> dateList) {
		return getSqlMapClientTemplate().queryForList("holiday.selectHolidayList", dateList);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	// 휴일을 등록하는 메서드 
	public void insertHoliday(HolidayBean holidayBean) {
		getSqlMapClientTemplate().insert("holiday.insertHoliday",holidayBean);
	}


	@SuppressWarnings("deprecation")
	@Override
	// 휴일목록을 삭제하는 메서드
	public void deleteHoliday(HolidayBean holidayBean) {
		getSqlMapClientTemplate().delete("holiday.deleteHoliday",holidayBean);
	}
	
	/*
	@SuppressWarnings("deprecation")
	@Override
	// 휴일목록을 수정하는 메서드 
	public void updateHoliday(HolidayBean holidayBean) {
		getSqlMapClientTemplate().update("holiday.updateHoliday",holidayBean);
	}*/

}

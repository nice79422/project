package kr.co.seoulit.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.attendance.to.DailyAttdTO;


public interface DailyAttdDAO {

	//근태 목록 가져오기 
	public List<DailyAttdTO> selectDailyAttdList(HashMap<String, String> map);
	
	// 일근태 정보  등록
	public void insertDailyAttdList(DailyAttdTO dailyAttdTO);
	
	// 일근태 정보  수정
	public void updateDailyAttdList(DailyAttdTO dailyAttdTO);
	
	// 일근태 정보  삭제
	public void deleteDailyAttdList(DailyAttdTO dailyAttdTO);

	//미승인 일근태 내역 조회 
	public List<DailyAttdTO> selectDailyAttdListByCondition(HashMap<String,Object> map);
}

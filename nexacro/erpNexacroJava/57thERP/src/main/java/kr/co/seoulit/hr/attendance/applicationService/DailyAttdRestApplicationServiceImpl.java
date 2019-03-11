package kr.co.seoulit.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.attendance.dao.DailyAttdRestDAO;
import kr.co.seoulit.hr.attendance.to.DailyAttdRestTO;

@Component
public class DailyAttdRestApplicationServiceImpl implements DailyAttdRestApplicationService {

	/* DailyAttdRestDAO setting */
	@Autowired
	private DailyAttdRestDAO dailyAttdRestDAO;

	
	// 근태외 목록을 가지고오는 메서드 
	@Override
	public List<DailyAttdRestTO> findAttdRestList(HashMap<String,Object> map) {
		// TODO Auto-generated method stub
		return dailyAttdRestDAO.selectAttdRestList(map);
	}
	
	// 근태외 목록을 등록,수정,삭제 하는 메서드 
	@Override
	public void batchDailyAttdRest(List<DailyAttdRestTO> dailyAttdRestList) {
		
		 for(DailyAttdRestTO dailyAttdRestBean : dailyAttdRestList) {
	            switch(dailyAttdRestBean.getStatus()) {
	            case "inserted" : dailyAttdRestDAO.insertAttdRestList(dailyAttdRestBean); break;
	            case "updated" : dailyAttdRestDAO.updateAttdRestList(dailyAttdRestBean); break; 
	            case "deleted" : dailyAttdRestDAO.deleteAttdRestList(dailyAttdRestBean); break;
	            }
	     }
	}

	@Override
	public List<DailyAttdRestTO> findAttdRestListByCondition(HashMap<String, Object> map) {

		return dailyAttdRestDAO.selectAttdRestListByCondition(map);
	}
}

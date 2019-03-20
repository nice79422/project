package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.circumstance.dao.BaseWorkTimeDAO;
import kr.co.seoulit.hr.circumstance.to.BaseWorkTimeTO;

@Component
public class BaseWorkTimeApplicationServiceImple implements BaseWorkTimeApplicationService {
	
	@Autowired
	private BaseWorkTimeDAO baseWorkTimeDAO;

	@Override
	public List<BaseWorkTimeTO> findBaseWorkTimeList() {
		return baseWorkTimeDAO.selectBaseWorkTimeList();
		
		
	}

	@Override
	public void addBaseWorkTime(BaseWorkTimeTO baseWorkTimeTO) {
		baseWorkTimeDAO.insertBaseWorkTime(baseWorkTimeTO);
		
	}

	@Override
	public void editBaseWorkTime(BaseWorkTimeTO baseWorkTimeTO) {
		baseWorkTimeDAO.updateBaseWorkTime(baseWorkTimeTO);
		
	}

	@Override
	public void removeBaseWorkTimeList(List<BaseWorkTimeTO> baseWorkTimeList) {
		for(BaseWorkTimeTO baseWorkTime:baseWorkTimeList){
			baseWorkTimeDAO.deleteBaseWorkTime(baseWorkTime);
		}
		
	}



}

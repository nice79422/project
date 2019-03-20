package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.BaseWorkTimeTO;

public interface BaseWorkTimeApplicationService {
	public List<BaseWorkTimeTO> findBaseWorkTimeList();
	
	public void addBaseWorkTime(BaseWorkTimeTO baseWorkTimeTO);
	public void editBaseWorkTime(BaseWorkTimeTO baseWorkTimeTO);
	public void removeBaseWorkTimeList(List<BaseWorkTimeTO> baseWorkTimeList);

	}

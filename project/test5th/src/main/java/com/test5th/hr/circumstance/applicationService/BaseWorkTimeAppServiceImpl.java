package com.test5th.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.hr.circumstance.dao.BaseWorkTimeDAO;
import com.test5th.hr.circumstance.to.BaseWorkTimeBean;

@Component
public class BaseWorkTimeAppServiceImpl implements BaseWorkTimeAppService {
	/* BaseWorkTimeDAO setting */
	@Autowired
	private BaseWorkTimeDAO baseWorkTimeDAO;
	

	/* 모든 기본근무시간목록을 다 가지고 오는 메서드 */
	@Override
	public List<BaseWorkTimeBean> findBaseWorkTimeList() {
		return baseWorkTimeDAO.selectBaseWorkTimeList();
	}

	// 해당년도의 기본근무시간을 추가하는 메서드 
	@Override
	public void addBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean) {
		baseWorkTimeDAO.insertBaseWorkTime(baseWorkTimeBean);
	}

	// 해당년도의 기본근무시간을 수정하는 메서드 
	@Override
	public void editBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean) {
		baseWorkTimeDAO.updateBaseWorkTime(baseWorkTimeBean);
	}

	/*
	// 해당년도의 근무시간을 삭제하는 메서드 
	@Override
	public void removeBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean) {
		baseWorkTimeDAO.deleteBaseWorkTime(baseWorkTimeBean);
	}
	*/
}

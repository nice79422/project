package com.test4th.hr.circumstance.applicationService;

import java.util.List;

import com.test4th.hr.circumstance.to.BaseWorkTimeBean;

public interface BaseWorkTimeAppService {
	public List<BaseWorkTimeBean> findBaseWorkTimeList();

	public void addBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean);
	public void editBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean);
	/*
	public void removeBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean);
	*/
}

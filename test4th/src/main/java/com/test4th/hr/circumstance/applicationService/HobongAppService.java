package com.test4th.hr.circumstance.applicationService;

import java.util.List;

import com.test4th.hr.circumstance.to.HobongBean;

public interface HobongAppService {
	public List<HobongBean> findHobongList();
	
	public void batchHobong(List<HobongBean> hobongList);
	
}

package com.test4th.hr.circumstance.dao;

import java.util.List;

import com.test4th.hr.circumstance.to.HobongBean;

public interface HobongDAO {
	public List<HobongBean> selectHobongList();	
	
	public void insertHobong(HobongBean hobongBean);
	public void updateHobong(HobongBean hobongBean);
	public void deleteHobong(HobongBean hobongBean);
	
}

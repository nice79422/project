package com.test5th.hr.circumstance.dao;

import java.util.List;

import com.test5th.hr.circumstance.to.HobongBean;

public interface HobongDAO {
	public List<HobongBean> selectDirectHobongList();	
	
	public void insertHobong(HobongBean hobongBean);
	public void updateHobong(HobongBean hobongBean);
	public void deleteHobong(HobongBean hobongBean);
	
}

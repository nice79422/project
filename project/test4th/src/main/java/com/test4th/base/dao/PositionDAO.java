package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.PositionBean;

public interface PositionDAO {
	public List<PositionBean> selectPositionList();
	
	public PositionBean selectPosition(String positionCode);
}

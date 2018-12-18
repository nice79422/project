package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.dao.PositionDAO;
import com.test4th.base.to.PositionBean;

public class PositionAppServiceImpl implements PositionAppService {

    private PositionDAO positionDAO;
	public void setPositionDAO(PositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}
	/* 직급목록을 가져오는 메서드 */
	@Override
	public List<PositionBean> findPositionList() {
		return positionDAO.selectPositionList();
	}

}

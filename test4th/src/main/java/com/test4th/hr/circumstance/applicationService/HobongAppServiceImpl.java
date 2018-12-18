package com.test4th.hr.circumstance.applicationService;

import java.util.List;

import com.test4th.hr.circumstance.dao.HobongDAO;
import com.test4th.hr.circumstance.to.HobongBean;

public class HobongAppServiceImpl implements HobongAppService {

    private HobongDAO hobongDAO;
    public void setHobongDAO(HobongDAO hobongDAO) {
        this.hobongDAO = hobongDAO;
    }

	@Override
	/* 호봉목록을 가지고 오는 메서드 */
	public List<HobongBean> findHobongList() {
		return hobongDAO.selectHobongList();
	}

	/* 호봉관련처리를 일괄적으로 하는메서드 */
	@Override
	public void batchHobong(List<HobongBean> hobongList) {
		for(HobongBean hobongBean:hobongList){
			switch(hobongBean.getStatus()){
				case "insert" : hobongDAO.insertHobong(hobongBean); break;
				case "update" : hobongDAO.updateHobong(hobongBean); break;
				case "delete" : hobongDAO.deleteHobong(hobongBean); break;
			}
		}
	}
}

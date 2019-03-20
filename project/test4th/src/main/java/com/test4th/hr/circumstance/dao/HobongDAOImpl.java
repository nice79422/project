package com.test4th.hr.circumstance.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.circumstance.to.HobongBean;


public class HobongDAOImpl extends IBatisDAO implements HobongDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 호봉목록을 가지고 오는 메서드*/
	public List<HobongBean> selectHobongList() {
		return getSqlMapClientTemplate().queryForList("hobong.selectHobongList");
	}
	
	/* 호봉을 추가하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void insertHobong(HobongBean hobongBean) {
		getSqlMapClientTemplate().insert("hobong.insertHobong",hobongBean);
	}

	/* 호봉을 수정하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void updateHobong(HobongBean hobongBean) {
		getSqlMapClientTemplate().update("hobong.updateHobong",hobongBean);
	}

	/* 호봉을 삭제하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void deleteHobong(HobongBean hobongBean) {
		getSqlMapClientTemplate().delete("hobong.deleteHobong",hobongBean);
	}


}

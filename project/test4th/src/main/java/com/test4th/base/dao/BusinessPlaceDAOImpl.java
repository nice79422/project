package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.BusinessPlaceBean;
import com.test4th.common.dao.IBatisDAO;

public class BusinessPlaceDAOImpl extends IBatisDAO implements BusinessPlaceDAO {

    @Override
	@SuppressWarnings({ "deprecation", "unchecked" })
    public List<BusinessPlaceBean> selectBusinessPlaceList() {
        return getSqlMapClientTemplate().queryForList("BusinessPlace.selectBusinessPlaceList");
        
    }

	@SuppressWarnings("deprecation")
	@Override
	public void insertBusinessPlace(BusinessPlaceBean businessPlaceBean) {
		getSqlMapClientTemplate().insert("BusinessPlace.insertBusinessPlace",businessPlaceBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateBusinessPlace(BusinessPlaceBean businessPlaceBean) {
		getSqlMapClientTemplate().update("BusinessPlace.updateBusinessPlace",businessPlaceBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteBusinessPlace(BusinessPlaceBean businessPlaceBean) {
		getSqlMapClientTemplate().delete("BusinessPlace.deleteBusinessPlace",businessPlaceBean);
	}
}



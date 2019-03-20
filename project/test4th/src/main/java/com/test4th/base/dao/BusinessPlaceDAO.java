package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.BusinessPlaceBean;

public interface BusinessPlaceDAO {
    public List<BusinessPlaceBean> selectBusinessPlaceList();

    public void insertBusinessPlace(BusinessPlaceBean businessPlaceBean);
    public void updateBusinessPlace(BusinessPlaceBean businessPlaceBean);
    public void deleteBusinessPlace(BusinessPlaceBean businessPlaceBean);
}

package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.to.BusinessPlaceBean;


public interface BusinessPlaceAppService{
    public List<BusinessPlaceBean> findBusinessPlaceList();
    public void batchBusinessPlaceList(List<BusinessPlaceBean> businessPlaceList);
}

	
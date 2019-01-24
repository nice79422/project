package com.test5th.base.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.base.dao.BusinessPlaceDAO;
import com.test5th.base.to.BusinessPlaceBean;

@Component
public class BusinessPlaceAppServiceImpl implements BusinessPlaceAppService {

	@Autowired 
	private BusinessPlaceDAO businessPlaceDAO;
	 
	   

   @Override
   public List<BusinessPlaceBean> findBusinessPlaceList() {
       return businessPlaceDAO.selectBusinessPlaceList();
   }

   
	@Override
	public void batchBusinessPlaceList (List<BusinessPlaceBean> businessPlaceList) {
		for(BusinessPlaceBean businessPlaceBean:businessPlaceList){
			switch(businessPlaceBean.getStatus()){ /*mapper에서 bean들의  rowType를 읽어 온듯?? 필요없는경우에 받아만 놓고 사용 안함*/
				case "insert" : businessPlaceDAO.insertBusinessPlace(businessPlaceBean); break;
				case "update" : businessPlaceDAO.updateBusinessPlace(businessPlaceBean); break;
				case "delete" : businessPlaceDAO.deleteBusinessPlace(businessPlaceBean); break;
			}
		}
	}

}



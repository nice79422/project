package com.test4th.base.controller;


import java.util.List;

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.BusinessPlaceBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.PlatformData;

public class BusinessPlaceController extends AbstractMiplatformMultiActionController{
	/* baseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
    public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
        this.baseServiceFacade = baseServiceFacade;
    }

    /* 부서목록을 조회하는 메서드 */
  	public void findBusinessPlaceList(PlatformData inData, PlatformData outData) throws Exception {
          List<BusinessPlaceBean> businessPlaceList=baseServiceFacade.findBusinessPlaceList();
          for(BusinessPlaceBean businessPlaceBean:businessPlaceList) {
          System.out.println("사업장코드:"+businessPlaceBean.getBusinessPlaceName());
          }
          datasetBeanMapper.beansToDataset(outData, businessPlaceList, BusinessPlaceBean.class);
          /*
           * 	beansToDataset : bean --> dataset
           * */
      }
  	
  	/*부서관리 (등록/삭제/수정)*/
  	public void batchBusinessPlaceList(PlatformData inData, PlatformData outData) throws Exception {
  		/*
  		 * datasetToBean도 있다.
  		 *
  		 *  datasetToBeans : dataset --> bean
  		 *
  		 * */
          List<BusinessPlaceBean> businessPlaceList=datasetBeanMapper.datasetToBeans(inData, BusinessPlaceBean.class);
          baseServiceFacade.batchBusinessPlaceList(businessPlaceList);

          findBusinessPlaceList(inData,outData);
      }

	
}

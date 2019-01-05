package com.test4th.base.controller;


import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.CompanyBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.PlatformData;

public class CompanyController extends AbstractMiplatformMultiActionController{
	/* baseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
    public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
        this.baseServiceFacade = baseServiceFacade;
    }

    /* 회사 조회하는 메서드 */
	public void findCompany(PlatformData inData, PlatformData outData) throws Exception {
        CompanyBean companyBean=baseServiceFacade.findCompany();
        datasetBeanMapper.beanToDataset(outData, companyBean, CompanyBean.class);
        /*
         * 	beansToDataset : bean --> dataset
         * */
    }
	
	/*회사관리 (등록/삭제/수정)*/
	public void batchCompany(PlatformData inData, PlatformData outData) throws Exception {
		/*
		 * datasetToBean도 있다.
		 *
		 *  datasetToBeans : dataset --> bean
		 *
		 * */
		CompanyBean companyBean=datasetBeanMapper.datasetToBean(inData, CompanyBean.class);
        baseServiceFacade.batchCompany(companyBean);

        findCompany(inData,outData);
    }

	
}

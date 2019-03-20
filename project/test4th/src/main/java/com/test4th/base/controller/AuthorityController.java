package com.test4th.base.controller;

import java.util.List;

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.AuthorityBean;
import com.test4th.base.to.AuthorityInfoBean;
import com.test4th.base.to.MenuAuthorityBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.PlatformData;

public class AuthorityController extends AbstractMiplatformMultiActionController {
	/* BaseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
    public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
        this.baseServiceFacade = baseServiceFacade;
    }

    /* 권한에따른 menu목록을 가져오는 메서드 */
    public void findMenuAuthorityList(PlatformData inData, PlatformData outData) throws Exception {
    	String authorityCode = inData.getVariable("authorityCode").getValue().asString();
    	//입력한 변수를 얻어와서 String으로 변경 하는 듯?
    	List<MenuAuthorityBean> menuAuthorityList = baseServiceFacade.findMenuAuthorityList(authorityCode);
        datasetBeanMapper.beansToDataset(outData, menuAuthorityList, MenuAuthorityBean.class);
    }

   /* 권한목록을 가져오는 메서드 */
    public void findAuthorityList(PlatformData inData, PlatformData outData) throws Exception {
    	List<AuthorityBean> authorityList = baseServiceFacade.findAuthorityList();
		datasetBeanMapper.beansToDataset(outData, authorityList, AuthorityBean.class);
    }

    /* 모든 메뉴를 다가져오는 메서드 */
    public void findMenuAuthorityListAll(PlatformData inData, PlatformData outData) throws Exception {
    	List<MenuAuthorityBean> menuAuthorityList = baseServiceFacade.findMenuAuthorityListAll();
        datasetBeanMapper.beansToDataset(outData, menuAuthorityList, MenuAuthorityBean.class);
    }
   
    /* 메뉴권한관련해서 일괄처리하는 메서드 */
    public void batchAuthority(PlatformData inData, PlatformData outData) throws Exception {
    	//System.out.println(inData);
    	//권한은 변동을 하지 않아도 실행이 된다. 
    	AuthorityInfoBean authorityInfoBean=new AuthorityInfoBean();
    	authorityInfoBean.setAuthorityList(datasetBeanMapper.datasetToBeans(inData, AuthorityBean.class));
    	authorityInfoBean.setMenuAuthorityList(datasetBeanMapper.datasetToBeans(inData, MenuAuthorityBean.class));
        baseServiceFacade.batchAuthority(authorityInfoBean);
        findAuthorityList(inData,outData);
        findMenuAuthorityListAll(inData,outData);
    }

}

package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.to.AuthorityBean;
import com.test4th.base.to.AuthorityInfoBean;
import com.test4th.base.to.MenuAuthorityBean;

public interface AuthorityAppService {
    public List<MenuAuthorityBean> findMenuAuthorityList(String authorityCode);
    public List<AuthorityBean> findAuthorityList();
    public List<MenuAuthorityBean> findMenuAuthorityListAll();
    public void batchAuthority(AuthorityInfoBean authorityInfoBean);
   
}
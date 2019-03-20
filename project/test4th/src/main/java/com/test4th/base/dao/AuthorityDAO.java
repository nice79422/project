package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.AuthorityBean;
import com.test4th.base.to.MenuAuthorityBean;

public interface AuthorityDAO {
    public List<MenuAuthorityBean> selectMenuAuthorityList(String authorityCode);
    public List<AuthorityBean> selectAuthorityList();
    public List<MenuAuthorityBean> selectMenuAuthorityListAll();
    
    public void insertAuthority(AuthorityBean authorityBean);
    public void updateAuthority(AuthorityBean authorityBean);
    public void deleteAuthority(AuthorityBean authorityBean);
    public void insertMenuAuthority(MenuAuthorityBean menuAuthorityBean);
    //public void updateMenuAuthority(MenuAuthorityBean menuAuthorityBean);
    public void deleteMenuAuthority(MenuAuthorityBean menuAuthorityBean);
  
}

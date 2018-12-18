package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.dao.AuthorityDAO;
import com.test4th.base.to.AuthorityBean;
import com.test4th.base.to.AuthorityInfoBean;
import com.test4th.base.to.MenuAuthorityBean;

public class AuthorityAppServiceImpl implements AuthorityAppService {

    private AuthorityDAO authorityDAO;
    public void setAuthorityDAO(AuthorityDAO authorityDAO) {
		this.authorityDAO = authorityDAO;
	}
    /* 권한에따른 메뉴목록을 들고오는 메서드 */
	@Override
	public List<MenuAuthorityBean> findMenuAuthorityList(String authorityCode) {
		return authorityDAO.selectMenuAuthorityList(authorityCode);
	}
	
	/* 권한목록을 가져오는 메서드 */
	@Override
	public List<AuthorityBean> findAuthorityList() {
		return authorityDAO.selectAuthorityList();
	}
	
	/* 모든권한의 메뉴를 다 가져오는 메서드 */
	@Override
	public List<MenuAuthorityBean> findMenuAuthorityListAll() {
		return authorityDAO.selectMenuAuthorityListAll();
	}

	
	/* 권한, 메뉴에 관련된 일괄처리를 하는 메서드 */
	@Override
	public void batchAuthority(AuthorityInfoBean authorityInfoBean) {
		for(AuthorityBean authorityBean:authorityInfoBean.getAuthorityList()){
			switch(authorityBean.getStatus()){
				case "insert" : authorityDAO.insertAuthority(authorityBean); break;
				case "update" : authorityDAO.updateAuthority(authorityBean); break;
				case "delete" : authorityDAO.deleteAuthority(authorityBean); break;
			}
		}
		for(MenuAuthorityBean menuAuthorityBean:authorityInfoBean.getMenuAuthorityList()){
			switch(menuAuthorityBean.getStatus()){
				case "insert" : authorityDAO.insertMenuAuthority(menuAuthorityBean); break;
				//case "update" : authorityDAO.updateMenuAuthority(menuAuthorityBean); break;
				case "delete" : authorityDAO.deleteMenuAuthority(menuAuthorityBean); break;
			}
		}
	}
	
}

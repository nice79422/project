package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.AuthorityBean;
import com.test4th.base.to.MenuAuthorityBean;
import com.test4th.common.dao.IBatisDAO;

public class AuthorityDAOImpl extends IBatisDAO implements AuthorityDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 권한에따른 메뉴목록을 가져오는 메서드 */
	public List<MenuAuthorityBean> selectMenuAuthorityList(String authorityCode) {
		return getSqlMapClientTemplate().queryForList("authority.selectMenuAuthorityList",authorityCode);
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 모든 권한목록을 가져오는 메서드 */
	public List<AuthorityBean> selectAuthorityList() {
		return getSqlMapClientTemplate().queryForList("authority.selectAuthorityList");
	}


	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 모든권한에 따른 메뉴를 다가져오는 메서드 */
	public List<MenuAuthorityBean> selectMenuAuthorityListAll() {
		return getSqlMapClientTemplate().queryForList("authority.selectMenuAuthorityListAll");
	}


	/* 권한을 추가하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void insertAuthority(AuthorityBean authorityBean) {
		getSqlMapClientTemplate().insert("authority.insertAuthority",authorityBean);
	}

	/* 권한을 수정하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void updateAuthority(AuthorityBean authorityBean) {
		getSqlMapClientTemplate().update("authority.updateAuthority",authorityBean);
	}


	@SuppressWarnings("deprecation")
	@Override
	public void deleteAuthority(AuthorityBean authorityBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().delete("authority.deleteAuthority", authorityBean);
	}


	/* 권한에 메뉴를 추가하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void insertMenuAuthority(MenuAuthorityBean menuAuthorityBean) {
		getSqlMapClientTemplate().insert("authority.insertMenuAuthority",menuAuthorityBean);
	}


	@SuppressWarnings("deprecation")
	@Override
	/* 권한에 해당하는 메뉴를 삭제하는 메서드 */
	public void deleteMenuAuthority(MenuAuthorityBean menuAuthorityBean) {
		getSqlMapClientTemplate().delete("authority.deleteMenuAuthority",menuAuthorityBean);
	}
}
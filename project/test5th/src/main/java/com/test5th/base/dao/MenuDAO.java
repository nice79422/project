package com.test5th.base.dao;

import java.util.List;

import com.test5th.base.to.MenuBean;

public interface MenuDAO {
	List<MenuBean> selectMenuList();
}

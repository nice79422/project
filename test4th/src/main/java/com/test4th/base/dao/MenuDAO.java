package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.MenuBean;

public interface MenuDAO {
	List<MenuBean> selectMenuList();
}

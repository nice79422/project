package kr.co.seoulit.sys.dao;

import java.util.List;

import kr.co.seoulit.sys.to.MenuTO;

public interface MenuDAO {

	public List<MenuTO> selectMenuList();
}

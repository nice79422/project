package kr.co.seoulit.sys.dao;

import java.util.List;

import kr.co.seoulit.sys.to.AuthorityTO;
import kr.co.seoulit.sys.to.MenuAuthorityTO;

public interface AuthorityDAO{

	public List<MenuAuthorityTO> selectMenuAuthorityList(String authorityCode);

	public List<AuthorityTO> selectAuthorityList();

}

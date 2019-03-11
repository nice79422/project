package kr.co.seoulit.sys.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.sys.dao.AuthorityDAO;
import kr.co.seoulit.sys.to.AuthorityTO;
import kr.co.seoulit.sys.to.MenuAuthorityTO;

@Component
public class AuthorityAppServiceImpl implements AuthorityAppService {

	@Autowired
    private AuthorityDAO authorityDAO;

    /* 권한에따른 메뉴목록을 들고오는 메서드 */
	@Override
	public List<MenuAuthorityTO> findMenuAuthorityList(String authorityCode) {
		return authorityDAO.selectMenuAuthorityList(authorityCode);
	}

	@Override
	public List<AuthorityTO> findAuthorityList() {
		// TODO Auto-generated method stub
		return authorityDAO.selectAuthorityList();
	}

}

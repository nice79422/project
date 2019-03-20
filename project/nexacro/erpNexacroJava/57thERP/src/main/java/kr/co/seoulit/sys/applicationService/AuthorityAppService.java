package kr.co.seoulit.sys.applicationService;

import java.util.List;

import kr.co.seoulit.sys.to.AuthorityTO;
import kr.co.seoulit.sys.to.MenuAuthorityTO;

public interface AuthorityAppService {
    public List<MenuAuthorityTO> findMenuAuthorityList(String authorityCode);
    public List<AuthorityTO> findAuthorityList();

}
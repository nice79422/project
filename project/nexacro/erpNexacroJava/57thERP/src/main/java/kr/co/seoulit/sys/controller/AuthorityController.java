package kr.co.seoulit.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.AuthorityTO;
import kr.co.seoulit.sys.to.MenuAuthorityTO;

@Controller
public class AuthorityController {
	/* BaseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

    /* 권한에따른 menu목록을 가져오는 메서드 */
	@RequestMapping("sys/findMenuAuthorityList.do")
	public void findMenuAuthorityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String authorityCode = inData.getVariable("authorityCode").getString();
    	List<MenuAuthorityTO> menuAuthorityList = baseServiceFacade.findMenuAuthorityList(authorityCode);
        datasetBeanMapper.beansToDataset(outData, menuAuthorityList, MenuAuthorityTO.class);
    }

   /* 권한목록을 가져오는 메서드 */
	@RequestMapping("sys/findAuthorityList.do")
    public void findAuthorityList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	PlatformData outData = (PlatformData) request.getAttribute("outData");
    	List<AuthorityTO> authorityList = baseServiceFacade.findAuthorityList();
		datasetBeanMapper.beansToDataset(outData, authorityList, AuthorityTO.class);
    }
//
//    /* 모든 메뉴를 다가져오는 메서드 */
//	@RequestMapping("base/findMenuAuthorityListAll.do")
//    public void findMenuAuthorityListAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
//    	PlatformData outData = (PlatformData) request.getAttribute("outData");
//    	List<MenuAuthorityBean> menuAuthorityList = baseServiceFacade.findMenuAuthorityListAll();
//        datasetBeanMapper.beansToDataset(outData, menuAuthorityList, MenuAuthorityBean.class);
//    }
//
//    /* 메뉴권한관련해서 일괄처리하는 메서드 */
	@RequestMapping("sys/batchAuthority.do")
    public void batchAuthority(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//System.out.println(inData);
    	//권한은 변동을 하지 않아도 실행이 된다.
/*    	PlatformData inData = (PlatformData) request.getAttribute("inData");
    	AuthorityInfoBean authorityInfoBean=new AuthorityInfoBean();
    	authorityInfoBean.setAuthorityList(datasetBeanMapper.datasetToBeans(inData, AuthorityBean.class));
    	authorityInfoBean.setMenuAuthorityList(datasetBeanMapper.datasetToBeans(inData, MenuAuthorityBean.class));
        baseServiceFacade.batchAuthority(authorityInfoBean);
        findAuthorityList(request,response);
        findMenuAuthorityListAll(request,response);
*/    }

}

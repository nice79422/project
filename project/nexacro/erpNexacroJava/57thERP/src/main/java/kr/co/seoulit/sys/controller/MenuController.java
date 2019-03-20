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
import kr.co.seoulit.sys.to.MenuTO;

@Controller
public class MenuController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("sys/findMenuList.do")
	public String findMenuList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<MenuTO> menuList = baseServiceFacade.findMenuList();
		datasetBeanMapper.beansToDataset(outData, menuList, MenuTO.class);
		return "dataSetView";
	}

	@RequestMapping("sys/test.do")
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {


	}
}

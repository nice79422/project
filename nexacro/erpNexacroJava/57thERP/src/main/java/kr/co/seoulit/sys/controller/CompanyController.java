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
import kr.co.seoulit.sys.to.CompanyTO;
import kr.co.seoulit.sys.to.DepartmentTO;

@Controller
public class CompanyController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("sys/findCompanyList.do")
	public String findCompanyList(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<CompanyTO> companyList = baseServiceFacade.findCompanyList();
		datasetBeanMapper.beansToDataset(outData, companyList, CompanyTO.class);
		return "dataSetView";
	}
	
	@RequestMapping("sys/updateCompanyInfo.do")
	public void updateCompanyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<CompanyTO> companyInfo=datasetBeanMapper.datasetToBeans(inData, CompanyTO.class);
	    baseServiceFacade.updateCompanyInfo(companyInfo);

	}
}

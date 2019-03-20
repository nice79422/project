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
import kr.co.seoulit.sys.to.CustomerTO;
import kr.co.seoulit.sys.to.DepartmentTO;

@Controller
public class CustomerController {
	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("sys/findCustomerList.do")
	public String findCustomerList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<CustomerTO> customerList = baseServiceFacade.findCustomerList();
		datasetBeanMapper.beansToDataset(outData, customerList, CustomerTO.class);
		return "dataSetView";
	}
	
	@RequestMapping("sys/batchCustomer.do")
	public void batchCustomer(HttpServletRequest request, HttpServletResponse response)throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<CustomerTO> batchCustomerList=datasetBeanMapper.datasetToBeans(inData, CustomerTO.class);
	    baseServiceFacade.batchCustomer(batchCustomerList);
	    findCustomerList(request,response);
		
	}
}

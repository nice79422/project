package kr.co.seoulit.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.DepartmentTO;
import kr.co.seoulit.sys.to.SequenceTo;

@Controller
public class DepartmentController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("sys/findDepartmentList.do")
	public String findDepartmentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		Map<String,Object> vList=new HashMap<>();
		String businessPlaceCode=inData.getVariable("businessPlaceCode").getString().toUpperCase();;
		vList.put("businessPlaceCode", businessPlaceCode);
		List<DepartmentTO> deptList = baseServiceFacade.findDepartmentList(vList);
		datasetBeanMapper.beansToDataset(outData, deptList, DepartmentTO.class);
		return "dataSetView";
	}

	@RequestMapping("sys/findAllDeptList.do")
	public String findCodeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<DepartmentTO> allDeptList = baseServiceFacade.findAllDeptList();
		datasetBeanMapper.beansToDataset(outData, allDeptList, DepartmentTO.class);
		return "dataSetView";
	}
	
	@RequestMapping("sys/batchDept.do")
	public void batchDept(HttpServletRequest request, HttpServletResponse response)throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DepartmentTO> batchDeptList=datasetBeanMapper.datasetToBeans(inData, DepartmentTO.class);
	    baseServiceFacade.batchDept(batchDeptList);
	    findCodeList(request,response);
		
	}
}


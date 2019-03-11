package kr.co.seoulit.hr.circumstance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import kr.co.seoulit.hr.circumstance.to.BaseWorkTimeTO;
import kr.co.seoulit.hr.circumstance.to.DeductionTaxTO;
import kr.co.seoulit.hr.circumstance.to.IncomeTaxTO;




@Controller
public class BaseWorkTimeController {
	
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("hr/circumstance/findBaseWorkTimeList.do")
	public void findBaseWorkTimeList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		PlatformData outData=(PlatformData) request.getAttribute("outData");
		List<BaseWorkTimeTO> baseWorkTimeList=circumstanceServiceFacade.findBaseWorkTimeList();
		datasetBeanMapper.beansToDataset(outData, baseWorkTimeList, BaseWorkTimeTO.class);
	}
	
	@RequestMapping("hr/circumstance/addBaseWorkTime.do")
		public void addBaseWorkTime(HttpServletRequest request, HttpServletResponse reponse)throws Exception{
		PlatformData inData=(PlatformData) request.getAttribute("inData");
		BaseWorkTimeTO baseWorkTimeTO=datasetBeanMapper.datasetToBean(inData, BaseWorkTimeTO.class);
		circumstanceServiceFacade.addBaseWorkTime(baseWorkTimeTO);
		findBaseWorkTimeList(request, reponse);
	}
	
	@RequestMapping("hr/circumstance/editBaseWorkTime.do")
		public void editBaseWorkTime(HttpServletRequest request, HttpServletResponse reponse) throws Exception{
			PlatformData inData=(PlatformData)request.getAttribute("inData");
			BaseWorkTimeTO baseWorkTimeTO=datasetBeanMapper.datasetToBean(inData, BaseWorkTimeTO.class);
			circumstanceServiceFacade.editBaseWorkTime(baseWorkTimeTO);
			findBaseWorkTimeList(request, reponse);			
	}
	
	@RequestMapping("hr/circumstance/removeBaseWorkTime.do")
	public void removeBaseWorkTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<BaseWorkTimeTO> baseWorkTimeList=datasetBeanMapper.datasetToBeans(inData, BaseWorkTimeTO.class);
		circumstanceServiceFacade.removeBaseWorkTimeList(baseWorkTimeList);
		findBaseWorkTimeList(request,response);
	}
	
}
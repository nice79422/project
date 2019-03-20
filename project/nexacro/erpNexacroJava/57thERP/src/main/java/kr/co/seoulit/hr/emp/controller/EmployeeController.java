package kr.co.seoulit.hr.emp.controller;


import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.DataSet;
import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.emp.serviceFacade.EmpServiceFacade;
import kr.co.seoulit.hr.emp.to.EmployeeTO;


@Controller
public class EmployeeController{
	@Autowired
	private EmpServiceFacade empServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("hr/emp/findEmployeeList.do")
	public void findEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		request.getAttribute("inData");


		List<EmployeeTO> employeeList = empServiceFacade.findEmployeeList();

		datasetBeanMapper.beansToDataset(outData, employeeList, EmployeeTO.class);

	}

	@RequestMapping("hr/emp/batchEmployeeList.do")
	public void batchEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<EmployeeTO> employeeList = datasetBeanMapper.datasetToBeans(inData, EmployeeTO.class);

		empServiceFacade.batchEmployeeList(employeeList);
		saveEmpImg(request,response);

	}


	@RequestMapping("hr/emp/saveEmpImg.do")
	public void saveEmpImg(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");


		DataSet dataset = inData.getDataSet("ds_Img");
		FileOutputStream out = null;
		String fileName = dataset.getColumn("EMP_FILENAME").toString();
		try {
			if (fileName != null) {
				out = new FileOutputStream("C:\\eclipse-workspaceLogi\\HOHZERP\\src\\main\\webapp\\Photos\\empPhoto\\" + fileName);//사진저장 경로


				byte[] file = dataset.getBlob(0,"IMG_FILE_DATA");
				BufferedOutputStream bufferedOut = new BufferedOutputStream(out);
				bufferedOut.write(file);
				bufferedOut.flush();
				bufferedOut.close();
				out.close();
				bufferedOut = null;
				out = null;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

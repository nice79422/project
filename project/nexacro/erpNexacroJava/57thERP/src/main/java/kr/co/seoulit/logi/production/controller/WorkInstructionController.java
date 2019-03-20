package kr.co.seoulit.logi.production.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.production.serviceFacade.ProductionServiceFacade;
import kr.co.seoulit.logi.production.to.MaterialCheckTempTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.controller
@Class			WorkInstructionController.java
@Author			wonminlee
@Description	작업지시 컨트롤러
@Create			2019.02.11
@Last Update    2019.02.22 : 작업지시관련 메서드생성
************************************************************************/


@Controller
public class WorkInstructionController {

	@Autowired
	private ProductionServiceFacade productionServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	//1작업지시 추가버튼 메서드: PKG_WORK_INSTRUCTION.P_WORK_INSTRUCTION_OPEN 프로시저호출
	@RequestMapping("logi/production/findMaterialCheckTempList.do")
	public void findMaterialCheckTempList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		HashMap<String, Object> paramMap = new HashMap<>();
		String mrpGno = inData.getVariable("mrpGno").getString();
		paramMap.put("mrpGno", mrpGno);
		System.out.println(mrpGno);
		productionServiceFacade.findMaterialCheckTempList(paramMap);
		List<MaterialCheckTempTO> materialCheckList =(List<MaterialCheckTempTO>)paramMap.get("result");
		int errorCode=(Integer)paramMap.get("errorCode");
		String errorMsg=(String)paramMap.get("errorMsg");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@에러코드@@@@@@@@@@@@@@@@@@@@@"+errorCode);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@errorMsg@@@@@@@@@@@@@@@@@@@@@"+errorMsg);
		outData.getVariableList().add("errorCode", errorCode);
		outData.getVariableList().add("errMsg", errorMsg);
		datasetBeanMapper.beansToDataset(outData, materialCheckList, MaterialCheckTempTO.class);

	}
	
	//2프로시저 호출 실행후 콜백에서 워크인스트럭션 펑션 호출하면 실행되는 트랜잭션에서 아래메서드호출
	@RequestMapping("logi/production/findWorkInstructionList.do")
	public void findWorkInstructionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		List<WorkInstructionTO> workInstructionList = productionServiceFacade.findWorkInstructionList();
		
		datasetBeanMapper.beansToDataset(outData, workInstructionList, WorkInstructionTO.class);

	}
	
	//작업지시에서
	@RequestMapping("logi/production/findWorkInstructionList2.do")
	public void findWorkInstructionList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<WorkInstructionTO> workInstructionList = productionServiceFacade.findWorkInstructionList2();

		datasetBeanMapper.beansToDataset(outData, workInstructionList, WorkInstructionTO.class);

	}

}

package kr.co.seoulit.hr.circumstance.controller;

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
import kr.co.seoulit.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import kr.co.seoulit.hr.circumstance.to.EtcSudangTO;
import kr.co.seoulit.hr.circumstance.to.OvertimeSudangTO;
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;
import kr.co.seoulit.hr.circumstance.to.SudangInfoTO;

@Controller
public class SudangMgnController {

	@Autowired
	private CircumstanceServiceFacade circumstanceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//연장,야간,휴일,기타 수당관련 목록을 불러오는 메서드
	@RequestMapping("hr/circumstance/findSudangList.do")
	public void findSudangList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		Map<String,Object> sudangInfoList=new HashMap<>();
		sudangInfoList = circumstanceFacade.findSudangList();

		List<OvertimeSudangTO> overtimeSudangList=(List<OvertimeSudangTO>) sudangInfoList.get("overtimeSudangList");
		List<EtcSudangTO> etcSudangList=(List<EtcSudangTO>) sudangInfoList.get("etcSudangList");

		datasetBeanMapper.beansToDataset(outData, overtimeSudangList, OvertimeSudangTO.class);
		datasetBeanMapper.beansToDataset(outData, etcSudangList, EtcSudangTO.class);
    }

	@RequestMapping("hr/circumstance/batchSudangProcess.do")
	public void batchSudangProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		Map<String,Object> sudangInfoList=new HashMap<>();

		List<OvertimeSudangTO> overtimeSudangList = datasetBeanMapper.datasetToBeans(inData, OvertimeSudangTO.class);
		List<EtcSudangTO> etcSudangList = datasetBeanMapper.datasetToBeans(inData, EtcSudangTO.class);

		sudangInfoList.put("overtimeSudangList", overtimeSudangList);
		sudangInfoList.put("etcSudangList", etcSudangList);

		circumstanceFacade.batchSudangProcess(sudangInfoList);
    }

}
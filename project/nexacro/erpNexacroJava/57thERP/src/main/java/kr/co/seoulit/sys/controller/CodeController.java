package kr.co.seoulit.sys.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.sys.serviceFacade.BaseServiceFacade;
import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.SequenceTo;

@Controller
public class CodeController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("sys/findCodeList.do")
	public String findCodeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<CodeTO> codeList = baseServiceFacade.findCodeList();
		datasetBeanMapper.beansToDataset(outData, codeList, CodeTO.class);
		return "dataSetView";
	}

/*
	@RequestMapping("sys/takeSerialCode.do")
	public String takeSerialCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SequenceTo seqTo=new SequenceTo();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String findSeq = inData.getVariable("findSeq").getString();
		System.out.println(findSeq);
		String serialNo = baseServiceFacade.findSerialCode(findSeq);
		seqTo.setSeqNo(serialNo);
		datasetBeanMapper.beanToDataset(outData, seqTo, SequenceTo.class);
		//outData.getVariableList().add("serialCode", serialNo);
		return "dataSetView";

	}
*/	
	@RequestMapping("sys/takeSerialCodeTest.do")
	public void takeSerialCodeTest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		HashMap<String, Object> findSeq=new HashMap<String, Object>(); 
		String Seq = inData.getVariable("findSeq").getString();
		findSeq.put("findSeq", Seq);
		System.out.println(findSeq);
		String serialNo=baseServiceFacade.takeSerialCodeTest(findSeq);
		SequenceTo seqTO=new SequenceTo();
		seqTO.setSeqNo(serialNo);
		datasetBeanMapper.beanToDataset(outData, seqTO, SequenceTo.class);
	
	}
}
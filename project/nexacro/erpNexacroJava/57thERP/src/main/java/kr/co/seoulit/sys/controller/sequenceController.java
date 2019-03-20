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
import kr.co.seoulit.sys.to.BusinessPlaceTO;
import kr.co.seoulit.sys.to.CodeTO;
import kr.co.seoulit.sys.to.SequenceTo;

@Controller
public class sequenceController {

	@Autowired
	private BaseServiceFacade baseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


	@RequestMapping("sys/findSequenceNo.do")
	public String findSequenceNo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SequenceTo seqTo=new SequenceTo();
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String findSeq = inData.getVariable("findSeq").getString();
		// System.out.println("@@@"+findSeq);
		String squenceNo = baseServiceFacade.findSequenceNo(findSeq);
		seqTo.setSeqNo(squenceNo);
		datasetBeanMapper.beanToDataset(outData, seqTo, SequenceTo.class);
		outData.getVariableList().add("squenceNo", squenceNo);
		return "dataSetView";

	}

}
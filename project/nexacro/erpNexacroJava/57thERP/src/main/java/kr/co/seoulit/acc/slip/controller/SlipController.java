package kr.co.seoulit.acc.slip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.acc.slip.serviceFacade.SlipServiceFacade;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.common.mapper.DatasetBeanMapper;

@Controller
public class SlipController{
	
	@Autowired
	private SlipServiceFacade slipServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


	//승인,미결에따른 전표조회 메서드 
	@RequestMapping("acc/slip/findSlipListByCondition.do")
	public void findSlipListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" getSlipList 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		HashMap<String, Object> slipMap = new HashMap<>();

		String startDate=inData.getVariable("startDate").getString();
		String endDate=inData.getVariable("endDate").getString();
		String slipStatus=inData.getVariable("slipStatus").getString();
		
		slipMap.put("startDate", startDate);
		slipMap.put("endDate", endDate);
		slipMap.put("slipStatus", slipStatus);
		
		List<SlipTO> sliplist=slipServiceFacade.findSlipListByCondition(slipMap);
    
        List<JournalTO> journalList=new ArrayList<>();
		List<JournalDetailTO> journalDetailList=new ArrayList<>();
		
		for(SlipTO slipTo:sliplist) {
			List<JournalTO> journalBeanList=slipTo.getJournalToList();
			       for(JournalTO journalTO:journalBeanList) 
			       {
			        List<JournalDetailTO> journalDetailBeanList=journalTO.getJournalDetailToList();
			        journalDetailList.addAll(journalDetailBeanList);
			       }
			journalList.addAll(journalBeanList);
		}

		datasetBeanMapper.beansToDataset(outData, sliplist, SlipTO.class);
		datasetBeanMapper.beansToDataset(outData, journalList, JournalTO.class);
		datasetBeanMapper.beansToDataset(outData, journalDetailList, JournalDetailTO.class);
	}

	
	

	/*//전표 추가할때 전표 있는지 당일작성날짜꺼 조회해서 유효성 검사 메서드
	@RequestMapping("acc/slip/findSlipByReportingDate.do")
	public void findSlipByReportingDate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		System.out.println(" getSlipRowCount 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String reportingDate=inData.getVariable("reportingDate").getString();
		System.out.println("reportingDate : " + reportingDate);

		List<SlipTO> slipRowCount=slipServiceFacade.findSlipByReportingDate(reportingDate);
		System.out.println(" sf -> findSlipByReportingDate ");

		datasetBeanMapper.beansToDataset(outData, slipRowCount, SlipTO.class);
	}
*/
	//전표일괄저장
	@RequestMapping("acc/slip/batchSlipAndJournalAndJournalDetailInfo.do")
	public void batchSlipAndJournalAndJournalDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" batchSlipAndJournalAndJournalDetailInfo 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		List<SlipTO> slipInfo = datasetBeanMapper.datasetToBeans(inData, SlipTO.class);
		List<JournalTO> journalInfo = datasetBeanMapper.datasetToBeans(inData, JournalTO.class);
        List<JournalDetailTO> journalDetailInfo= datasetBeanMapper.datasetToBeans(inData, JournalDetailTO.class);
		slipServiceFacade.batchSlipAndJournalAndJournalDetailInfo(slipInfo,journalInfo,journalDetailInfo);
		System.out.println(" batchSlipAndJournalAndJournalDetailInfo 종료 ");
	}
	
	
	//승인으로 업데이트
	@RequestMapping("acc/slip/modifyApprovalSlipList.do")
	public void modifyApprovalSlipList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" modifyApprovalSlipList 시작 ");

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		List<SlipTO> slipInfo = datasetBeanMapper.datasetToBeans(inData, SlipTO.class);
		
		slipServiceFacade.modifyApprovalSlipList(slipInfo);
		
		System.out.println(" modifyApprovalSlipList 종료 ");
		
		datasetBeanMapper.beansToDataset(outData, slipInfo, SlipTO.class);
	}
	
	
}

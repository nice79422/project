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
import kr.co.seoulit.logi.production.to.MpsTO;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.production.to.MrpOpenTempTO;
import kr.co.seoulit.logi.production.to.MrpTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.controller
@Class			MrpController.java
@Author			wonminlee,konghokyoeng
@Description	소요량 전개 취합 관련 컨트롤러
@Create			2019.02.11
@Last Update    2019.02.20 : 소요량 개별,일괄전개 메서드 생성 // 소요량 취합 메서드 생성(wonminlee)
				2019.02.22 : 소요량취합 저장(konghokyoeng)
************************************************************************/


@Controller
public class MrpController {
	@Autowired
	private ProductionServiceFacade productionServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	// mrp리스트 조회 메서드
	@RequestMapping("logi/production/findMrpList.do")
	public void findMrpList (HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		HashMap<String, Object> paramMap = new HashMap<>();
		String mrpgStatus = inData.getVariable("mrpgStatus").getString();
		paramMap.put("mrpgStatus", mrpgStatus);


		List<MrpTO> mrpList = productionServiceFacade.findMrpList(paramMap);

		datasetBeanMapper.beansToDataset(outData, mrpList, MrpTO.class);

	}
	// 소요량 개별 전개 MRP_OPEN_TEMP 프로시져 메서드  
	@RequestMapping("logi/production/findMrpOpenTempProcess.do")
	public void findMrpOpenTempProcess(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");


		HashMap<String, Object> paramMap = new HashMap<>();
		String mps = inData.getVariable("mpsNo").getString();
		String[] mpsNo=new String[1];
		mpsNo[0]=mps;
		paramMap.put("mpsNo", mpsNo);
		List<MrpOpenTempTO> mrpOpenTempList = productionServiceFacade.findMrpOpenTempProcessList(paramMap);

		datasetBeanMapper.beansToDataset(outData, mrpOpenTempList, MrpOpenTempTO.class);

	}
	
	// 소요량 일괄 전개 MRP_OPEN_TEMP 프로시져 메서드
		@RequestMapping("logi/production/findMrpOpenTempList.do")
		public void findMrpOpenTempList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// result Object 생성
			PlatformData outData = (PlatformData) request.getAttribute("outData");
			PlatformData inData = (PlatformData) request.getAttribute("inData");
		HashMap<String, Object> paramMap = new HashMap<>();

		List<MpsTO> mpsList = datasetBeanMapper.datasetToBeans(inData, MpsTO.class);
		int flag=0;
		for(@SuppressWarnings("unused") MpsTO mp :mpsList){
			flag++;
		}
		String[] mpsNo=new String[flag];
		int index=0;
		for(MpsTO mpt :mpsList){
			System.out.println(mpt.getMpsNo());
			mpsNo[index]=(String)mpt.getMpsNo();
			index++;
			//System.out.println(index+"uuu"+mpsNo[index-1]);
		}

		paramMap.put("mpsNo", mpsNo);

		List<MrpOpenTempTO> mrpOpenTempList = productionServiceFacade.findMrpOpenTempProcessList(paramMap);

		datasetBeanMapper.beansToDataset(outData, mrpOpenTempList, MrpOpenTempTO.class);

		}
		
		// 소요량 취합 프로시져 메서드
		@RequestMapping("logi/production/registMrpGathering.do")
		public void registMrpGathering(HttpServletRequest request, HttpServletResponse response) throws Exception{
			PlatformData inData = (PlatformData) request.getAttribute("inData");
			request.getAttribute("outData");

		HashMap<String,Object> paramMap = new HashMap<>();
		//타입핸들러는 string[] 형식으로만 사용이 가능
		List<MrpTO> mrpList = datasetBeanMapper.datasetToBeans(inData, MrpTO.class);
		int flag=0;
		String mpsn="a";
		for(MrpTO mp :mrpList){
			if(!(mpsn).equals(mp.getMpsNo())) {
			mpsn=mp.getMpsNo();
			flag++;}
		}//mps넘버가 중복으로 넘어가는것을 방지
		String[] mpsNo=new String[flag];
		int index=0;
		for(MrpTO mpt :mrpList){
			System.out.println(mpt.getMpsNo());

			if(index==0){
			mpsNo[index]=(String)mpt.getMpsNo();
			index++;
			}
			if(index!=0) {
				if(!(mpsNo[index-1]).equals(mpt.getMpsNo())) {
					System.out.println(index+"들어갈소요량취합"+mpt.getMpsNo());
					System.out.println(index+"들어있는소요량취합"+mpsNo[index-1]);
					mpsNo[index]=(String)mpt.getMpsNo();
					index++;
				}else {
					System.out.println("진입실패");
				}
			}

		}

		paramMap.put("mpsNo", mpsNo);

		productionServiceFacade.registMrpGathering(paramMap);

		}
		
		
		@RequestMapping("logi/production/findMrpGatheringList.do")
		public void findMrpGatheringList (HttpServletRequest request, HttpServletResponse response) throws Exception{
			PlatformData inData = (PlatformData) request.getAttribute("inData");
			PlatformData outData = (PlatformData) request.getAttribute("outData");
			HashMap<String, Object> paramMap = new HashMap<>();
			String gatheringStatus = inData.getVariable("gatheringStatus").getString();
			paramMap.put("gatheringStatus", gatheringStatus);
			List<MrpGatheringTO> mrpGatheringList = productionServiceFacade.findMrpGatheringList(paramMap);

			datasetBeanMapper.beansToDataset(outData, mrpGatheringList, MrpGatheringTO.class);


		}
		
		//소요량취합 저장
		@RequestMapping("logi/production/batchMrpGathering.do")
		public void batchMrpGathering(HttpServletRequest request, HttpServletResponse response) throws Exception{
			PlatformData inData = (PlatformData) request.getAttribute("inData");

			List<MrpGatheringTO> mrpGatheringList = datasetBeanMapper.datasetToBeans(inData, MrpGatheringTO.class);

			productionServiceFacade.batchMrpGathering(mrpGatheringList);


		}

}

package kr.co.seoulit.logi.production.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.production.dao.MrpDAO;
import kr.co.seoulit.logi.production.dao.MrpGatheringDAO;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.production.to.MrpOpenTempTO;
import kr.co.seoulit.logi.production.to.MrpTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.applicationService
@Class			ProductionApplicationServiceImpl.java
@Author			wonminlee, kong
@Description	상품 AS
@Create			2019.02.11
@Last Update    2019.02.15 : 주생산계획 조회 메서드 추가 (wonminlee)
				2019.02.20 : 소요량전개,취합 메서드추가 // 소요량전개 조회메서드추가 (wonminlee)
				 			 MPS적용메서드 추가(kong)
				2019.02.22 : 작업지시관련메서드 추가
************************************************************************/

@Component
public class MrpApplicationServiceImpl implements MrpApplicationService {
	@Autowired
	private MrpDAO mrpDAO;
	@Autowired
	private MrpGatheringDAO mrpGatheringDAO;
	
	@Override
	public List<MrpTO> findMrpList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return mrpDAO.selectMrpList(paramMap);
	}
	
	@Override
	public List<MrpOpenTempTO> findMrpOpenTempProcessList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		 mrpDAO.MrpOpenTempProcessList(paramMap);
		 List<MrpOpenTempTO> mrpOpenList = (List<MrpOpenTempTO>)paramMap.get("result");

		 for(MrpOpenTempTO mpt : mrpOpenList) {
			 System.out.println(mpt.getMpsNo());
		 }

		return mrpOpenList;
	}
	
	@Override
	public void registMrpGathering(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub

		mrpGatheringDAO.mrpGathering(paramMap);
		for(String s:paramMap.keySet()) {
			System.out.println("+++++"+s);
		}
			System.out.println("+++++"+paramMap.get("errorMsg"));
	}
	
	@Override
	public List<MrpGatheringTO> findMrpGatheringList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return mrpGatheringDAO.selectMrpGatheringList(paramMap);
	}
	
	//소요량취합 저장
	@Override
	public void batchMrpGathering(List<MrpGatheringTO> mrpGatheringList) {
		// TODO Auto-generated method stub

		for(MrpGatheringTO mrpGatheringTO : mrpGatheringList) {
			
			System.out.println(mrpGatheringTO.getStatus());

			switch(mrpGatheringTO.getStatus()) {
			case "updated" : mrpGatheringDAO.updateMrpGathering(mrpGatheringTO); break;
			//case "inserted" : mrpGatheringDAO.insertMrpGathering(mrpGatheringTO); break;x
			case "deleted" : mrpGatheringDAO.deleteMrpGathering(mrpGatheringTO); break;

			}

		}

	}
}

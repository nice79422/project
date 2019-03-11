package kr.co.seoulit.logi.production.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.to.MpsTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.applicationService
@Class			ProductionApplicationServiceImpl.java
@Author			wonminlee, kong
@Description	상품 AS
@Create			2019.02.11
@Last Update    2019.02.15 : 주생산계획 조회 메서드 추가 (wonminlee)
				2019.02.20 : 소요량전개,취합 메서드추가 // 소요량전개 조회메서드추가 (wonminlee)
				 			 MPS적용메서드 추가(kong)
				2019.02.22 : 작업지시관련메서드 추가 //소요량취합 저장 (kong)
************************************************************************/

public interface MpsApplicationService {

	List<MpsTO> findMpsList(HashMap<String, Object> searchDate);

	public void registMps(List<MpsTO> mpsList, List<ContractDetailTO> contractDetailList);
	
	//List<MrpTO> findMrpList(HashMap<String, Object> paramMap);

	//List<MrpOpenTempTO> findMrpOpenTempProcessList(HashMap<String, Object> paramMap);

	//void registMrpGathering(Map<String, Object> paramMap);

	//List<MrpGatheringTO> findMrpGatheringList(HashMap<String, Object> paramMap);

	/*List<MaterialCheckTempTO> findMaterialCheckTempList(Map<String, Object> paramMap);

	List<WorkInstructionTO> findWorkInstructionList();
	
	List<WorkInstructionTO> findWorkInstructionList2();*/

/*	//소요량취합 저장
	public void batchMrpGathering(List<MrpGatheringTO> mrpGatheringList);

	List<PrmTO> findPrmList();

	void registPrm(List<WorkInstructionTO> workInstructionList, List<PrmTO> prmList, List<StockTO> stockList,
			List<WarehousingTO> warehousingList);

	void registWorkInstruction(List<WorkInstructionTO> workInstructionList, List<MrpGatheringTO> mrpGatheringList,
			List<MaterialPaymentTO> materialPaymentList, List<StockTO> stockList);*/

	
}

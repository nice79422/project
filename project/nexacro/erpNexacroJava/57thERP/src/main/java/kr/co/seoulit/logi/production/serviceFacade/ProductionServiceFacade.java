package kr.co.seoulit.logi.production.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.to.MaterialCheckTempTO;
import kr.co.seoulit.logi.production.to.MpsTO;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.production.to.MrpOpenTempTO;
import kr.co.seoulit.logi.production.to.MrpTO;
import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.serviceFacade
@Class			ProductionServiceFacadeImpl.java
@Author			wonminlee, kong
@Description	상품 서비스 퍼사드
@Create			2019.02.11
@Last Update    2019.02.15 : 주생산계획 조회 메서드 추가 (wonminlee)
				2019.02.20 : 소요량전개,취합 메서드추가 // 소요량전개 조회메서드추가 (wonminlee)
							 MPS적용메서드 추가(kong)
				2019.02.22 : 작업지시관련메서드추가,소요량취합 저장(kong)
************************************************************************/
public interface ProductionServiceFacade {

	List<MpsTO> findMpsList(HashMap<String, Object> searchDate);

	public void registMps(List<MpsTO> mpsList, List<ContractDetailTO> contractDetailList);
	
	List<MrpTO> findMrpList(HashMap<String, Object> paramMap);

	List<MrpOpenTempTO> findMrpOpenTempProcessList(HashMap<String, Object> paramMap);

	void registMrpGathering(Map<String, Object> paramMap);

	List<MrpGatheringTO> findMrpGatheringList(HashMap<String, Object> paramMap);

	List<MaterialCheckTempTO> findMaterialCheckTempList(Map<String, Object> paramMap);

	List<WorkInstructionTO> findWorkInstructionList();

	//소요량취합 저장
	public void batchMrpGathering(List<MrpGatheringTO> mrpGatheringList);

	List<PrmTO> findPrmList();

	void registPrm(List<WorkInstructionTO> workInstructionList, List<PrmTO> prmList, List<StockTO> stockList,
			List<WarehousingTO> warehousingList);

	List<WorkInstructionTO> findWorkInstructionList2();





}

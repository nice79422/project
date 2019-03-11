package kr.co.seoulit.logi.production.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.applicationService.MpsApplicationService;
import kr.co.seoulit.logi.production.applicationService.WorkInstructionApplicationService;
import kr.co.seoulit.logi.production.applicationService.MrpApplicationService;
import kr.co.seoulit.logi.production.applicationService.PrmApplicationService;
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
				2019.02.22 : 작업지시관련메서드추가 // 소요량취합 저장(kong)
************************************************************************/

@Service
public class ProductionServiceFacadeImpl implements ProductionServiceFacade {

	@Autowired
	private MpsApplicationService productionApplicationService;
	@Autowired
	private MrpApplicationService mrpApplicationService;
	@Autowired
	private WorkInstructionApplicationService workInstructionApplicationService;
	@Autowired
	private PrmApplicationService prmApplicationService;


	//@@@@@@@@@@@@@@@@@@@@@  mps  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<MpsTO> findMpsList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return productionApplicationService.findMpsList(searchDate);
	}

	@Override
	public void registMps(List<MpsTO> mpsList, List<ContractDetailTO> contractDetailList) {
		// TODO Auto-generated method stub
		productionApplicationService.registMps(mpsList, contractDetailList);
	}
	
	
	//@@@@@@@@@@@@@@@@@@@@@  mrp  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<MrpTO> findMrpList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return mrpApplicationService.findMrpList(paramMap);
	}
	
	@Override
	public List<MrpOpenTempTO> findMrpOpenTempProcessList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return mrpApplicationService.findMrpOpenTempProcessList(paramMap);
	}
	
	@Override
	public void registMrpGathering(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		mrpApplicationService.registMrpGathering(paramMap);
	}
	
	@Override
	public List<MrpGatheringTO> findMrpGatheringList(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return mrpApplicationService.findMrpGatheringList(paramMap);
	}
	
	//소요량취합 저장
	@Override
	public void batchMrpGathering(List<MrpGatheringTO> mrpGatheringList) {
		// TODO Auto-generated method stub
		mrpApplicationService.batchMrpGathering(mrpGatheringList);
	}
	
	//@@@@@@@@@@@@@@@@@@@@@  작업지시  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<MaterialCheckTempTO> findMaterialCheckTempList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return workInstructionApplicationService.findMaterialCheckTempList(paramMap);
	}
	
	@Override
	public List<WorkInstructionTO> findWorkInstructionList() {
		// TODO Auto-generated method stub
		return workInstructionApplicationService.findWorkInstructionList();
	}
	
	@Override
	public List<WorkInstructionTO> findWorkInstructionList2() {
		// TODO Auto-generated method stub
		return workInstructionApplicationService.findWorkInstructionList2();
	}
	
	//생산실적리스트 조회
	@Override
	public List<PrmTO> findPrmList() {
		// TODO Auto-generated method stub
		return prmApplicationService.findPrmList();
	}
	
	@Override
	public void registPrm(List<WorkInstructionTO> workInstructionList, List<PrmTO> prmList, List<StockTO> stockList , List<WarehousingTO> warehousingList ) {
		// TODO Auto-generated method stub
		prmApplicationService.registPrm(workInstructionList, prmList, stockList, warehousingList);
	}

}

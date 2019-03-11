package kr.co.seoulit.logi.production.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.business.applicationService.ContractApplicationService;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.dao.MpsDAO;
import kr.co.seoulit.logi.production.to.MpsTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.applicationService
@Class			ProductionApplicationServiceImpl.java
@Author			wonminlee, kong
@Description	상품 AS
@Create			2019.02.11
@Last Update    2019.03.06 : AS분리
************************************************************************/

@Component
public class MpsApplicationServiceImpl implements MpsApplicationService {

	@Autowired
	private MpsDAO mpsDAO;
	@Autowired
	private ContractApplicationService contractApplicationService;
	
	
	//@@@@@@@@@@@@@@@@@@@@@  mps  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<MpsTO> findMpsList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return mpsDAO.selectMpsList(searchDate);
	}
	
	@Override
	public void registMps(List<MpsTO> mpsList, List<ContractDetailTO> contractDetailList) {
		// TODO Auto-generated method stub

		if(mpsList!=null) {
			for(MpsTO mpsTO : mpsList) {
				switch(mpsTO.getStatus()) {
				case "inserted" : mpsDAO.insertMps(mpsTO);	break;
				//case "updated" : contractDAO.updateContract(contractTO); break;
				//case "deleted" : contractDAO.deleteContract(contractTO); break;
				}
			}

		}

		if(contractDetailList!=null) {
			contractApplicationService.batchContract(null, contractDetailList);
		}
	}

	
/*	//@@@@@@@@@@@@@@@@@@@@@  mrp  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
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
	}*/

	
	//@@@@@@@@@@@@@@@@@@@@@  작업지시  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
/*	@Override
	public List<MaterialCheckTempTO> findMaterialCheckTempList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return workInstructionDAO.materialCheckTempList(paramMap);
	}
	
	@Override
	public List<WorkInstructionTO> findWorkInstructionList() {
		// TODO Auto-generated method stub
		return workInstructionDAO.selectWorkInstructionList();
	}
	
	@Override
	public List<WorkInstructionTO> findWorkInstructionList2() {
		// TODO Auto-generated method stub
		return workInstructionDAO.selectWorkInstructionList2();
	}*/
	
	/*//소요량취합 저장
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
	
	
	
	@Override //옮김
	public void registWorkInstruction(List<WorkInstructionTO> workInstructionList,
			List<MrpGatheringTO> mrpGatheringList, List<MaterialPaymentTO> materialPaymentList, List<StockTO> stockList) {
		// TODO Auto-generated method stub
		for(WorkInstructionTO workInstructionTO : workInstructionList) {
			switch(workInstructionTO.getStatus()) {
				case "inserted" : workInstructionDAO.insertWorkInstruction(workInstructionTO); break;
				case "updated" : workInstructionDAO.updateWorkInstruction(workInstructionTO); break;
				case "deleted" : workInstructionDAO.deleteWorkInstruction(workInstructionTO); break;
			}
		}
		if(mrpGatheringList!=null) {
			batchMrpGathering(mrpGatheringList);
		}

		if(materialPaymentList!=null) {
			purchaseApplicationService.registMaterialPayment(materialPaymentList);
		}

		if(stockList!=null) {
			purchaseApplicationService.batchStock(stockList);     
		}

	}

	@Override
	public List<PrmTO> findPrmList() {
		// TODO Auto-generated method stub
		return prmDAO.selectPrmList();
	}
	
	
	@Override
	public void registPrm(List<WorkInstructionTO> workInstructionList, List<PrmTO> prmList, List<StockTO> stockList , List<WarehousingTO> warehousingList) {
		// TODO Auto-generated method stub


		for(PrmTO prmTO : prmList) {

			switch(prmTO.getStatus()) {
			case "insert" : prmDAO.insertPrm(prmTO); break;
			//case "update" : prmDAO.updatePrm(prmTO); break;          
			//case "delete" : prmDAO.deletePrm(prmTO); break;

			}

		}

		registWorkInstruction(workInstructionList,null,null,null);     
		purchaseApplicationService.batchStock(stockList);				

		purchaseApplicationService.registWarehousing(null,null,null,warehousingList);			
	}
	*/
	

}

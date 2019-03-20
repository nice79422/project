package kr.co.seoulit.logi.production.applicationService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.business.applicationService.DeliveryApplicationService;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.production.dao.MpsDAO;
import kr.co.seoulit.logi.production.dao.MrpDAO;
import kr.co.seoulit.logi.production.dao.MrpGatheringDAO;
import kr.co.seoulit.logi.production.dao.PrmDAO;
import kr.co.seoulit.logi.production.dao.WorkInstructionDAO;
import kr.co.seoulit.logi.production.to.MaterialCheckTempTO;
import kr.co.seoulit.logi.production.to.MpsTO;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.production.to.MrpOpenTempTO;
import kr.co.seoulit.logi.production.to.MrpTO;
import kr.co.seoulit.logi.production.to.PrmTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

/************************************************************************
@Package		kr.co.seoulit.logi.production.applicationService
@Class			ProductionApplicationServiceImpl.java
@Author			wonminlee, kong
@Description	상품 AS
@Create			2019.02.11
@Last Update    2019.03.07 새로만들고 이전
************************************************************************/

@Component
public class WorkInstructionApplicationServiceImpl implements WorkInstructionApplicationService{
	@Autowired
	private WorkInstructionDAO workInstructionDAO;
	@Autowired
	private StockApplicationService purchaseApplicationService;
	@Autowired
	private MrpApplicationService mrpApplicationService;
	
	@Override
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
	}
	
	@Override
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
			mrpApplicationService.batchMrpGathering(mrpGatheringList);
		}

		if(materialPaymentList!=null) {
			purchaseApplicationService.registMaterialPayment(materialPaymentList);
		}

		if(stockList!=null) {
			purchaseApplicationService.batchStock(stockList);     
		}

	}
	
}

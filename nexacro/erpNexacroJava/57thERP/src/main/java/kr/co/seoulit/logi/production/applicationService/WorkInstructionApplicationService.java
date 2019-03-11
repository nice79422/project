package kr.co.seoulit.logi.production.applicationService;

import java.util.List;
import java.util.Map;

import kr.co.seoulit.logi.production.to.MaterialCheckTempTO;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.production.to.WorkInstructionTO;
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.StockTO;

public interface WorkInstructionApplicationService {

	List<MaterialCheckTempTO> findMaterialCheckTempList(Map<String, Object> paramMap);

	List<WorkInstructionTO> findWorkInstructionList();

	List<WorkInstructionTO> findWorkInstructionList2();

	void registWorkInstruction(List<WorkInstructionTO> workInstructionList, List<MrpGatheringTO> mrpGatheringList,
			List<MaterialPaymentTO> materialPaymentList, List<StockTO> stockList);

}

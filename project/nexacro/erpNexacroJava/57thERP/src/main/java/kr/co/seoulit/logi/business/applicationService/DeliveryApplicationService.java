package kr.co.seoulit.logi.business.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;
import kr.co.seoulit.logi.purchase.to.StockTO;

public interface DeliveryApplicationService {
	
/*//	Estimate
	public List<EstimateTO> findEstimateList(HashMap<String, Object> searchDate);

	public List<EstimateTO> findEstimateDialog();

	public void batchEstimate(List<EstimateTO> estimateList, List<EstimateDetailTO> estimateDetailList);

	public List<EstimateDetailTO> findEstimateDetailList(String estimateNo);

//	Contract
	public List<ContractTO> findContractList(HashMap<String, Object> searchDate);

	public List<ContractDetailTO> findContractDetailList(String contractNo);

	public void batchContract(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList);
	
	public List<ContractDetailTO> findRangedContractDetailList(HashMap<String, Object> searchDate);
	
	public List<ContractTO> findContractTo(String contractNo);*/

	public List<DeliveryResultTO> findDeliveryResultList();

	void registDeliveryResult(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList,
			List<StockTO> stockList, List<DeliveryResultTO> deliveryResultList);

	public List<CompleteDeliveryResultTO> findCompleteDeliveryResultList(HashMap<String, Object> searchDate);

	//List<ContractDetailTO> findAllContractDetailList();

}
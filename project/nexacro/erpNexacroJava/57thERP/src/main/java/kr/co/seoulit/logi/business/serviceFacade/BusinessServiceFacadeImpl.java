package kr.co.seoulit.logi.business.serviceFacade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.logi.business.applicationService.DeliveryApplicationService;
import kr.co.seoulit.logi.business.applicationService.ContractApplicationService;
import kr.co.seoulit.logi.business.applicationService.EstimateApplicationService;
import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;
import kr.co.seoulit.logi.business.to.EstimateTO;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;
import kr.co.seoulit.logi.purchase.to.StockTO;

@Service
public class BusinessServiceFacadeImpl implements BusinessServiceFacade{
	@Autowired
	private DeliveryApplicationService deliveryApplicationService;
	@Autowired
	private EstimateApplicationService estimateApplicationService;
	@Autowired
	private ContractApplicationService contractApplicationService;

	@Override
	public List<EstimateTO> findEstimateList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return estimateApplicationService.findEstimateList(searchDate);
	}

	@Override
	public List<EstimateTO> findEstimateDialog() {
		// TODO Auto-generated method stub
		return estimateApplicationService.findEstimateDialog();
	}

	@Override
	public void registEstimateDetail(List<EstimateTO> estimateList, List<EstimateDetailTO> estimateDetailList) {
		// TODO Auto-generated method stub
		System.out.println("b");
		estimateApplicationService.batchEstimate(estimateList,estimateDetailList);
	}

	@Override
	public List<EstimateDetailTO> findEstimateDetailList(String estimateNo) {
		// TODO Auto-generated method stub
		return estimateApplicationService.findEstimateDetailList(estimateNo);
	}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@contract@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<ContractTO> findContractList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return contractApplicationService.findContractList(searchDate);
	}

	@Override
	public List<ContractDetailTO> findContractDetailList(String contractNo) {
		// TODO Auto-generated method stub
		return contractApplicationService.findContractDetailList(contractNo);
	}
	
	@Override
	public List<ContractDetailTO> findAllContractDetailList() {
		// TODO Auto-generated method stub
		return contractApplicationService.findAllContractDetailList();
	}

	@Override
	public void registContract(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList) {
		// TODO Auto-generated method stub
		contractApplicationService.batchContract(contractList,contractDetailList);
	}

	@Override
	public List<ContractDetailTO> findRangedContractDetailList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return contractApplicationService.findRangedContractDetailList(searchDate);
	}

	@Override
	public List<ContractTO> findContractTo(String contractNo) {
		// TODO Auto-generated method stub
		return contractApplicationService.findContractTo(contractNo);
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@delivery@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@22
	@Override
	public List<DeliveryResultTO> findDeliveryResultList() {
		// TODO Auto-generated method stub
		return deliveryApplicationService.findDeliveryResultList();
	}
	
	@Override
	public void registDeliveryResult(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList,
			List<StockTO> stockList, List<DeliveryResultTO> deliveryResultList) {
		// TODO Auto-generated method stub
		deliveryApplicationService.registDeliveryResult(contractList,contractDetailList,stockList,deliveryResultList);
	}
	
	@Override
	public List<CompleteDeliveryResultTO> findCompleteDeliveryResultList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return deliveryApplicationService.findCompleteDeliveryResultList(searchDate);
	}

}
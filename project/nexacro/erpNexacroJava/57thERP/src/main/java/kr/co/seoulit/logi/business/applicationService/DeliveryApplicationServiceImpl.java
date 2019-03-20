package kr.co.seoulit.logi.business.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.business.dao.DeliveryResultDAO;
import kr.co.seoulit.logi.business.to.CompleteDeliveryResultTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.DeliveryResultTO;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.purchase.to.StockTO;

@Component
public class DeliveryApplicationServiceImpl implements DeliveryApplicationService {
	@Autowired
	private DeliveryResultDAO deliveryResultDAO;
	@Autowired
	private StockApplicationService purchaseApplicationService;
	@Autowired
	private ContractApplicationService contractApplicationService;

/*	@Override
	public List<EstimateTO> findEstimateList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return estimateDAO.selectEstimateList(searchDate);
	}

	@Override
	public List<EstimateTO> findEstimateDialog() {
		// TODO Auto-generated method stub
		return estimateDAO.selectEstimateDialog();
	}

	@Override
	public void batchEstimate(List<EstimateTO> estimateList, List<EstimateDetailTO> estimateDetailList) {
		// TODO Auto-generated method stub
		System.out.println("c");
		for (EstimateTO estimateTO : estimateList) {
			switch (estimateTO.getStatus()) {
			case "inserted": estimateDAO.insertEstimate(estimateTO); break;
			case "updated": estimateDAO.updateEstimate(estimateTO); break;
			case "deleted": estimateDAO.deleteEstimate(estimateTO); break;

			}

		}
		System.out.println("d");
		if (estimateDetailList != null) {
			System.out.println("+++"+estimateDetailList.get(0).getEstimateNo());
			for (EstimateDetailTO estimateDetailTO : estimateDetailList) {
				switch (estimateDetailTO.getStatus()) {
				case "inserted": estimateDetailDAO.insertEstimateDetail(estimateDetailTO); break;
				case "updated": estimateDetailDAO.updateEstimateDetail(estimateDetailTO); break;
				case "deleted": estimateDetailDAO.deleteEstimateDetail(estimateDetailTO); break;

				}

			}

		}

	}

	@Override
	public List<EstimateDetailTO> findEstimateDetailList(String estimateNo) {
		// TODO Auto-generated method stub
		return estimateDetailDAO.selectEstimateDetailList(estimateNo);
	}*/

/*	//contract@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<ContractTO> findContractList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return contractDAO.selectContractList(searchDate);
	}

	@Override
	public List<ContractDetailTO> findContractDetailList(String contractNo) {
		// TODO Auto-generated method stub
		return contractDetailDAO.selectContractDetailList(contractNo);
	}
	
	@Override
	public List<ContractDetailTO> findAllContractDetailList() {
		// TODO Auto-generated method stub
		return contractDetailDAO.selectAllContractDetailList();
	}

	@Override
	public void batchContract(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList) {
		// TODO Auto-generated method stub
		List<EstimateTO> estimateList = null;
		EstimateTO estimateTO = null;
		if (contractList != null) {
			for (ContractTO contractTO : contractList) {
				System.out.println(contractTO.getStatus());
				switch (contractTO.getStatus()) {
				case "inserted": contractDAO.insertContract(contractTO);
					estimateList = new ArrayList<>();
					estimateTO = new EstimateTO();
					estimateTO.setEstimateNo(contractTO.getEstimateNo());
					estimateTO.setContractStatus("Y");
					estimateTO.setStatus("updated");
					estimateList.add(estimateTO); break;
				case "updated": contractDAO.updateContract(contractTO); break;
				case "deleted": contractDAO.deleteContract(contractTO);
					estimateList = new ArrayList<>();
					estimateTO = new EstimateTO();
					estimateTO.setEstimateNo(contractTO.getEstimateNo());
					estimateTO.setContractStatus("N");
					estimateTO.setStatus("updated");
					estimateList.add(estimateTO); break;

				}

			}

		}

			for (ContractDetailTO contractDetailTO : contractDetailList) {
				System.out.println("++++++++++"+contractDetailTO.getStatus());
				System.out.println("++++++++++"+contractDetailTO.getSlipRegistStatus());
				switch (contractDetailTO.getStatus()) {
				case "inserted": contractDetailDAO.insertContractDetail(contractDetailTO);
				case "updated": contractDetailDAO.updateContractDetail(contractDetailTO); break;
				case "deleted": contractDetailDAO.deleteContractDetail(contractDetailTO); break;
			}
		}
	}

	@Override
	public List<ContractDetailTO> findRangedContractDetailList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return contractDetailDAO.selectRangedContractDetailList(searchDate);
	}

	@Override
	public List<ContractTO> findContractTo(String contractNo) {
		// TODO Auto-generated method stub
		return contractDAO.selectContractTo(contractNo);
	}*/
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@Override
	public List<DeliveryResultTO> findDeliveryResultList() {
		// TODO Auto-generated method stub
		return deliveryResultDAO.selectDeliveryResultList();
	}

	@Override
	public void registDeliveryResult(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList,
			List<StockTO> stockList, List<DeliveryResultTO> deliveryResultList) {
		// TODO Auto-generated method stub

		for (DeliveryResultTO deliveryResultTO : deliveryResultList) {

			switch (deliveryResultTO.getStatus()) {

				case "inserted":
					deliveryResultDAO.insertDeliveryResult(deliveryResultTO);
					break;
				case "updated" : 
				//case "update" :

					deliveryResultDAO.updateDeliveryResult(deliveryResultTO);
					break;
			// case "delete" : deliveryResultDAO.deleteDeliveryResult(deliveryResultTO);
			// break;

			}

		}

		contractApplicationService.batchContract(contractList, contractDetailList);

		purchaseApplicationService.batchStock(stockList);

	}

	@Override
	public List<CompleteDeliveryResultTO> findCompleteDeliveryResultList(HashMap<String, Object> searchDate) {
		// TODO Auto-generated method stub
		return deliveryResultDAO.selectCompleteDeliveryResultList(searchDate);
	}

}
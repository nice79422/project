package kr.co.seoulit.logi.business.applicationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.business.dao.ContractDAO;
import kr.co.seoulit.logi.business.dao.ContractDetailDAO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateTO;

@Component
public class ContractApplicationServiceImpl implements ContractApplicationService {
	@Autowired
	private ContractDAO contractDAO;
	@Autowired
	private ContractDetailDAO contractDetailDAO;
	
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
	}
}

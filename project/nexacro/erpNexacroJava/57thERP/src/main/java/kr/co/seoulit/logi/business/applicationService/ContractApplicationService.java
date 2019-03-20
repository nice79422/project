package kr.co.seoulit.logi.business.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.business.to.ContractTO;

public interface ContractApplicationService {

	List<ContractTO> findContractList(HashMap<String, Object> searchDate);

	List<ContractDetailTO> findContractDetailList(String contractNo);

	List<ContractDetailTO> findAllContractDetailList();

	void batchContract(List<ContractTO> contractList, List<ContractDetailTO> contractDetailList);

	List<ContractDetailTO> findRangedContractDetailList(HashMap<String, Object> searchDate);

	List<ContractTO> findContractTo(String contractNo);

}

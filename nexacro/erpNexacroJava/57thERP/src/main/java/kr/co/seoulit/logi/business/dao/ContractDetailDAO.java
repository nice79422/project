package kr.co.seoulit.logi.business.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.ContractDetailTO;

public interface ContractDetailDAO {

	public List<ContractDetailTO> selectContractDetailList(String contractNo);
	
	public List<ContractDetailTO> selectAllContractDetailList();

	public List<ContractDetailTO> selectRangedContractDetailList(HashMap<String, Object> searchDate);

	public void insertContractDetail(ContractDetailTO contractDetailTO);

	public void updateContractDetail(ContractDetailTO contractDetailTO);

	public void deleteContractDetail(ContractDetailTO contractDetailTO);
}

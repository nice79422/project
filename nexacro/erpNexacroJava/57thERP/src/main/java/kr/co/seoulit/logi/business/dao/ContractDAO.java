package kr.co.seoulit.logi.business.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.ContractTO;
import kr.co.seoulit.logi.business.to.EstimateDetailTO;

public interface ContractDAO {

	public List<ContractTO> selectContractList(HashMap<String, Object> searchDate);

	public void insertContract(ContractTO contractTO);

	public void updateContract(ContractTO contractTO);

	public void deleteContract(ContractTO contractTO);
	
	public List<ContractTO> selectContractTo(String contractNo);
}
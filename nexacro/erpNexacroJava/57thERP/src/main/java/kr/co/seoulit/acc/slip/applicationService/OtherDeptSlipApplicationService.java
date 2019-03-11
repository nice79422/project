package kr.co.seoulit.acc.slip.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.acc.slip.to.AccountControlDetailTO;
import kr.co.seoulit.acc.slip.to.JournalDetailTO;
import kr.co.seoulit.acc.slip.to.JournalTO;
import kr.co.seoulit.acc.slip.to.SlipTO;
import kr.co.seoulit.logi.business.to.ContractDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;


public interface OtherDeptSlipApplicationService {
	//물류전표획득
	public List<ContractDetailTO> findContractSlipDetailList(HashMap<String, Object> logiSlipReq);
	public void registLogiSlipList(HashMap<String,Object> logiSlipMap);
	public List<OrderDetailTO> findOrderSlipDetailList(HashMap<String, Object> logiSlipReq);

	//인사전표획득
//	public List<TO> findContractSlipDetailList(HashMap<String, Object> logiSlipReq);

}

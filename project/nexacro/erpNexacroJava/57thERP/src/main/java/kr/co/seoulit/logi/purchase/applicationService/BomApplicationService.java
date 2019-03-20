package kr.co.seoulit.logi.purchase.applicationService;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.BomDeployTO;
import kr.co.seoulit.logi.purchase.to.BomTO;

public interface BomApplicationService {

	List<BomDeployTO> findBomDeployList(String itemCode, String condition);

	List<BomTO> findBomList();

}

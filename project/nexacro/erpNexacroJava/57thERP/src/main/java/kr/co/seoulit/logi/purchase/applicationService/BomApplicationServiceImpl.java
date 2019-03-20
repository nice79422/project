package kr.co.seoulit.logi.purchase.applicationService;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.production.applicationService.MrpApplicationService;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.purchase.dao.BomDAO;
import kr.co.seoulit.logi.purchase.dao.ItemDAO;
import kr.co.seoulit.logi.purchase.dao.MaterialPaymentDAO;
import kr.co.seoulit.logi.purchase.dao.OrderDetailDAO;
import kr.co.seoulit.logi.purchase.dao.OrderInfoDAO;
import kr.co.seoulit.logi.purchase.dao.StockDAO;
import kr.co.seoulit.logi.purchase.dao.WarehousingDAO;
import kr.co.seoulit.logi.purchase.to.BomDeployTO;
import kr.co.seoulit.logi.purchase.to.BomTO;
import kr.co.seoulit.logi.purchase.to.ItemTO;
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderGatheringTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

@Component
public class BomApplicationServiceImpl implements BomApplicationService {
	@Autowired
	private BomDAO bomDAO;
	
	//BOM목록 조회
	@Override
	public List<BomTO> findBomList() {
		// TODO Auto-generated method stub
		return bomDAO.selectBomList();
	}

	//BOM전개 조회
	@Override
	public List<BomDeployTO> findBomDeployList(String itemCode, String condition) {
		// TODO Auto-generated method stub
		HashMap<String, Object> deployCondition=new HashMap<>();

		deployCondition.put("deployCondition", condition);
		deployCondition.put("itemCode", itemCode);

		return bomDAO.selectBomDeployList(deployCondition);
	}
}

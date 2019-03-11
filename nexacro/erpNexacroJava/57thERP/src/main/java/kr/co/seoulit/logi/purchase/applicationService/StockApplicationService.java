package kr.co.seoulit.logi.purchase.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.purchase.to.BomDeployTO;
import kr.co.seoulit.logi.purchase.to.BomTO;
import kr.co.seoulit.logi.purchase.to.ItemTO;
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderGatheringTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

public interface StockApplicationService {

	//재고목록 조회
	public List<StockTO> findStockList();

	//부품재고이력 조회
	public List<MaterialPaymentTO> findMaterialPaymentList();
	
	public List<WarehousingTO> findWarehousingList();

	void registMaterialPayment(List<MaterialPaymentTO> materialPaymentList);

	void batchStock(List<StockTO> stockList);

	void registWarehousing(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList,
			List<StockTO> stockList, List<WarehousingTO> warehousingList);

	/*//발주취합
	List<OrderGatheringTO> findOrderGatheringList();

	//발주목록 조회
	List<OrderInfoTO> findOrderInfoList();

	//발주상세목록 조회
	List<OrderDetailTO> findOrderDetailList();
	List<OrderDetailTO> findOrderDetailList(HashMap<String, Object> logiSlipReq);

	//발주등록
	public void registOrder(List<OrderInfoTO> orderInfoList,List<OrderDetailTO> orderDetailList,List<MrpGatheringTO> mrpGatheringList);

	//품목조회
	public List<ItemTO> findItemList();
*/
	//


/*	//BOM목록 조회
	List<BomTO> findBomList();

	//BOM전개 조회
	List<BomDeployTO> findBomDeployList(String itemCode,String deployCondition);*/

}

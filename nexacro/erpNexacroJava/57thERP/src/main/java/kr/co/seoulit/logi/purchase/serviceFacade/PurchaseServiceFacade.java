package kr.co.seoulit.logi.purchase.serviceFacade;

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

public interface PurchaseServiceFacade {

	//재고목록 조회
	public List<StockTO> findStockList();

	//부품재고이력 조회
	public List<MaterialPaymentTO> findMaterialPaymentList();
	
	//발주취합
	public List<OrderGatheringTO> findOrderGatheringList();

	//발주목록 조회
	public List<OrderInfoTO> findOrderInfoList();

	//반주상세 조회
	public List<OrderDetailTO> findOrderDetailList();
	
	//발주등록
	public void registOrder(List<OrderInfoTO> orderInfoList,List<OrderDetailTO> orderDetailList,List<MrpGatheringTO> mrpGatheringList);
	
	//품목조회
	public List<ItemTO> findItemList(); 

	//품목저장
	public void batchItem(List<ItemTO> itemList);
	
	public List<WarehousingTO> findWarehousingList();
	
	//BOM목록 조회
	public List<BomTO> findBomList();

	//BOM전개 조회
	public List<BomDeployTO> findBomDeployList(String itemCode,String deployCondition);
	
	public void registWarehousing(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList, List<StockTO> stockList,List<WarehousingTO> warehousingList);
	
}

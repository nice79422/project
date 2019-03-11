package kr.co.seoulit.logi.purchase.serviceFacade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.purchase.applicationService.BomApplicationService;
import kr.co.seoulit.logi.purchase.applicationService.ItemApplicationService;
import kr.co.seoulit.logi.purchase.applicationService.OrderApplicationService;
import kr.co.seoulit.logi.purchase.applicationService.StockApplicationService;
import kr.co.seoulit.logi.purchase.to.BomDeployTO;
import kr.co.seoulit.logi.purchase.to.BomTO;
import kr.co.seoulit.logi.purchase.to.ItemTO;
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderGatheringTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

@Service
public class PurchaseServiceFacadeImpl implements PurchaseServiceFacade{
	
	@Autowired
	private StockApplicationService stockApplicationService;
	@Autowired
	private BomApplicationService bomApplicationService;
	@Autowired
	private ItemApplicationService itemApplicationService;
	@Autowired
	private OrderApplicationService orderApplicationService;
	//재고목록 조회
	@Override
	public List<StockTO> findStockList() {
		// TODO Auto-generated method stub
		return stockApplicationService.findStockList();
	}

	//부품재고이력 조회
	@Override
	public List<MaterialPaymentTO> findMaterialPaymentList() {
		// TODO Auto-generated method stub
		return stockApplicationService.findMaterialPaymentList();
	}

	//발주취합
	@Override
	public List<OrderGatheringTO> findOrderGatheringList() {
		// TODO Auto-generated method stub
		return orderApplicationService.findOrderGatheringList();
	}
	
	//발주목록 조회
	@Override
	public List<OrderInfoTO> findOrderInfoList() {
		// TODO Auto-generated method stub
		return orderApplicationService.findOrderInfoList();
	}
	
	//발주상세목록 조회
	@Override
	public List<OrderDetailTO> findOrderDetailList() {
		// TODO Auto-generated method stub
		return orderApplicationService.findOrderDetailList();
	}
	
	//발주등록
	@Override
	public void registOrder(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList,
			List<MrpGatheringTO> mrpGatheringList) {
		// TODO Auto-generated method stub
		orderApplicationService.registOrder(orderInfoList,orderDetailList,null);
	}
	
	//품목조회
	@Override
	public List<ItemTO> findItemList() {
		// TODO Auto-generated method stub
		return itemApplicationService.findItemList();
	}
	
	//품목저장
	@Override
	public void batchItem(List<ItemTO> itemList) {
		// TODO Auto-generated method stub
		itemApplicationService.batchItem(itemList);
	}
	
	//
	@Override
	public List<WarehousingTO> findWarehousingList() {
		// TODO Auto-generated method stub
		return stockApplicationService.findWarehousingList();
	}


	//BOM목록 조회
	@Override
	public List<BomTO> findBomList() {
		// TODO Auto-generated method stub
		return bomApplicationService.findBomList();
	}

	//BOM전개 조회
	@Override
	public List<BomDeployTO> findBomDeployList(String itemCode, String deployCondition) {
		// TODO Auto-generated method stub
		return bomApplicationService.findBomDeployList(itemCode, deployCondition);
	}

	@Override
	public void registWarehousing(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList,
			List<StockTO> stockList, List<WarehousingTO> warehousingList) {
		// TODO Auto-generated method stub
		stockApplicationService.registWarehousing(orderInfoList,orderDetailList,stockList,warehousingList);
	}
	
}

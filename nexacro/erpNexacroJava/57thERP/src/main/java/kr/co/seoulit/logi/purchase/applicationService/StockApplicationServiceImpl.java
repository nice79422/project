package kr.co.seoulit.logi.purchase.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.purchase.dao.MaterialPaymentDAO;
import kr.co.seoulit.logi.purchase.dao.StockDAO;
import kr.co.seoulit.logi.purchase.dao.WarehousingDAO;
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

@Component
public class StockApplicationServiceImpl implements StockApplicationService {

	@Autowired
	private StockDAO stockDAO;
	@Autowired
	private MaterialPaymentDAO materialPaymentDAO;
	@Autowired
	private WarehousingDAO	warehousingDAO;
	@Autowired
	private OrderApplicationService orderApplicationService;
	//재고목록 조회
	@Override
	public List<StockTO> findStockList() {
		// TODO Auto-generated method stub
		return stockDAO.selectStockList();
	}

	//부품재고이룍 조회
	@Override
	public List<MaterialPaymentTO> findMaterialPaymentList() {
		// TODO Auto-generated method stub
		return materialPaymentDAO.selectMaterialPaymentList();
	}
	@Override
	public List<WarehousingTO> findWarehousingList() {
		// TODO Auto-generated method stub
		return warehousingDAO.selectWarehousingList();
	}

	//
	@Override
	public void registMaterialPayment(List<MaterialPaymentTO> materialPaymentList) {
		// TODO Auto-generated method stub

		for (MaterialPaymentTO materialPaymentTO : materialPaymentList) {

			switch (materialPaymentTO.getStatus()) {
			case "insert":
				materialPaymentDAO.insertMaterialPayment(materialPaymentTO);
				break;
			// case "update" : materialPaymentDAO.updatematerialPayment(materialPaymentTO);
			// break;
			// case "delete" : materialPaymentDAO.deletematerialPayment(materialPaymentTO);
			// break;

			}

		}

	}

	//
	@Override
	public void batchStock(List<StockTO> stockList) {
		// TODO Auto-generated method stub

		for (StockTO stockTO : stockList) {
			stockDAO.updateStock(stockTO);

			/*
			 * switch(stockTO.getStatus()) { case "insert" : stockDAO.insertStock(stockTO);
			 * break; case "update" : stockDAO.updateStock(stockTO); break; case "delete" :
			 * stockDAO.deleteStock(stockTO); break;
			 *
			 * }
			 */

		}
	}

	//
	@Override
	public void registWarehousing(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList,
			List<StockTO> stockList, List<WarehousingTO> warehousingList) {
		// TODO Auto-generated method stub

//		if (orderInfoList != null && orderDetailList != null) {
//			orderApplicationService.registOrder(orderInfoList, orderDetailList, null);
//			System.out.println("aaaa");
//		}

//		for (StockTO stockTO : stockList) {
//			stockDAO.updateStock(stockTO);
//		}
		for (WarehousingTO warehousingTO : warehousingList) {

			warehousingDAO.insertWarehousing(warehousingTO);
			/*
			 * switch(warehousingTO.getStatus()) { case "insert" :
			 * warehousingDAO.insertWarehousing(warehousingTO); break; case "update" :
			 * warehousingDAO.updateWarehousing(warehousingTO); break;
			 *
			 *
			 * }
			 */

		}

	}

	/*//발주취합
	@Override
	public List<OrderGatheringTO> findOrderGatheringList() {
		// TODO Auto-generated method stub
		return orderDetailDAO.selectOrderGatheringList();
	}

	//발주목록 조회
	@Override
	public List<OrderInfoTO> findOrderInfoList() {
		// TODO Auto-generated method stub
		return orderInfoDAO.selectOrderInfoList();
	}

	//발주상세목록 조회
	@Override
	public List<OrderDetailTO> findOrderDetailList() {
		// TODO Auto-generated method stub
		return orderDetailDAO.selectOrderDetailList();
	}

	//발주등록
	@Override
	public void registOrder(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList,
			List<MrpGatheringTO> mrpGatheringList) {
		// TODO Auto-generated method stub

		for (OrderInfoTO orderInfoTO : orderInfoList) {
			switch (orderInfoTO.getStatus()) {
				case "inserted":
					orderInfoDAO.insertOrderInfo(orderInfoTO);
					break;
				case "updated":
					orderInfoDAO.updateOrderInfo(orderInfoTO);
					break;
			}
		}

		for (OrderDetailTO orderDetailTO : orderDetailList) {
			System.out.println(orderDetailTO.getOrderAmount() + orderDetailTO.getItemName());
			switch (orderDetailTO.getStatus()) {
				case "inserted":
					orderDetailDAO.insertOrderDetail(orderDetailTO);
					break;
				case "updated":
					orderDetailDAO.updateOrderDetail(orderDetailTO);
					break;
			}
		}

		if (mrpGatheringList != null) {
			mrpApplicationService.batchMrpGathering(mrpGatheringList);
		}

	}*/

/*	//품목조회
	@Override
	public List<ItemTO> findItemList() {
		// TODO Auto-generated method stub
		return itemDAO.selectItemList();
	}*/

	//


/*	//BOM목록 조회
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
	}*/

/*	@Override
	public List<OrderDetailTO> findOrderDetailList(HashMap<String, Object> logiSlipReq) {
		// TODO Auto-generated method stub
		return orderDetailDAO.selectOrderDetailList2(logiSlipReq);
	}*/

}

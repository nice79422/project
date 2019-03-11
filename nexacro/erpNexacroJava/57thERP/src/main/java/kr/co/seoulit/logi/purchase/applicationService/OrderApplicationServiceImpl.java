package kr.co.seoulit.logi.purchase.applicationService;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.production.applicationService.MrpApplicationService;
import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.purchase.dao.OrderDetailDAO;
import kr.co.seoulit.logi.purchase.dao.OrderInfoDAO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderGatheringTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;

@Component
public class OrderApplicationServiceImpl implements OrderApplicationService {
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	@Autowired
	private OrderInfoDAO orderInfoDAO;
	@Autowired
	private MrpApplicationService mrpApplicationService;
	
	//발주취합
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

		}
		
		@Override
		public List<OrderDetailTO> findOrderDetailList(HashMap<String, Object> logiSlipReq) {
			// TODO Auto-generated method stub
			return orderDetailDAO.selectOrderDetailList2(logiSlipReq);
		}
}

package kr.co.seoulit.logi.purchase.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.production.to.MrpGatheringTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderGatheringTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;

public interface OrderApplicationService {

	List<OrderGatheringTO> findOrderGatheringList();

	List<OrderInfoTO> findOrderInfoList();

	List<OrderDetailTO> findOrderDetailList();

	void registOrder(List<OrderInfoTO> orderInfoList, List<OrderDetailTO> orderDetailList,
			List<MrpGatheringTO> mrpGatheringList);

	List<OrderDetailTO> findOrderDetailList(HashMap<String, Object> logiSlipReq);

}

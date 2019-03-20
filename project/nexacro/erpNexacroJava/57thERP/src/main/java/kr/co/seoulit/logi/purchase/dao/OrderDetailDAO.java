package kr.co.seoulit.logi.purchase.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderGatheringTO;

public interface OrderDetailDAO {

	//발주취합
	public List<OrderGatheringTO> selectOrderGatheringList();

	//발주상세목록 조회
	public List<OrderDetailTO> selectOrderDetailList();
	public List<OrderDetailTO> selectOrderDetailList2(HashMap<String, Object> logiSlipReq);

	//발주상세등록
	public void insertOrderDetail(OrderDetailTO orderDetailTO);

	//발주상세수정
	public void updateOrderDetail(OrderDetailTO orderDetailTO);

}

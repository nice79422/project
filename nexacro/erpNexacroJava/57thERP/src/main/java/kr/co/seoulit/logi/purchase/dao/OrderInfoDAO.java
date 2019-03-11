package kr.co.seoulit.logi.purchase.dao;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.OrderInfoTO;

public interface OrderInfoDAO {
	
	//발주목록 조회
	public List<OrderInfoTO> selectOrderInfoList();

	//발주등록
	public void insertOrderInfo(OrderInfoTO orderInfoTO);

	//발주수정
	public void updateOrderInfo(OrderInfoTO orderInfoTO);
	
}

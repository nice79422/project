package kr.co.seoulit.logi.purchase.dao;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;

public interface MaterialPaymentDAO {

	//부품재고이력 조회
	public List<MaterialPaymentTO> selectMaterialPaymentList();
	//인설트
	public void insertMaterialPayment(MaterialPaymentTO materialPaymentTO);
}

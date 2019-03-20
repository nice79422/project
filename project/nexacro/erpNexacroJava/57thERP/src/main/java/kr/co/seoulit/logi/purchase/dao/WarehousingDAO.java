package kr.co.seoulit.logi.purchase.dao;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.WarehousingTO;

public interface WarehousingDAO {

	public List<WarehousingTO> selectWarehousingList();
	
	public void insertWarehousing(WarehousingTO warehousingTO);
	
	public void updateWarehousing(WarehousingTO warehousingTO);
}

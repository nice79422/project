package kr.co.seoulit.logi.purchase.dao;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.StockTO;

public interface StockDAO {

	public List<StockTO> selectStockList();
	
	public void updateStock(StockTO stockTO);
	
}

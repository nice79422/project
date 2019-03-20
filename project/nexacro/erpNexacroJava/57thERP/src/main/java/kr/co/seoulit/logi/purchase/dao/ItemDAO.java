package kr.co.seoulit.logi.purchase.dao;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.ItemTO;

public interface ItemDAO {

	//품목조회
	List<ItemTO> selectItemList();

	//품목수정
	public void updateItem(ItemTO itemTO);
	
	//품목등록
	public void insertItem(ItemTO itemTO);
	
	//품목삭제
	public void deleteItem(ItemTO itemTO);
	
}

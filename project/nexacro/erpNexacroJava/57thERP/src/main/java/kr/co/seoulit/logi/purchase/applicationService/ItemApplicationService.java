package kr.co.seoulit.logi.purchase.applicationService;

import java.util.List;

import kr.co.seoulit.logi.purchase.to.ItemTO;

public interface ItemApplicationService {

	//품목조회
	List<ItemTO> findItemList();

	//품목저장
	void batchItem(List<ItemTO> itemList);

}

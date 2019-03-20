package kr.co.seoulit.logi.purchase.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.logi.purchase.dao.ItemDAO;
import kr.co.seoulit.logi.purchase.to.ItemTO;

@Component
public class ItemApplicationServiceImpl implements ItemApplicationService {
	@Autowired
	private ItemDAO	itemDAO;
	
	//품목조회
	@Override
	public List<ItemTO> findItemList() {
		// TODO Auto-generated method stub
		return itemDAO.selectItemList();
	}
	
	//품목저장
	@Override
	public void batchItem(List<ItemTO> itemList) {
		// TODO Auto-generated method stub

		for(ItemTO itemTO : itemList) {
		
			System.out.println(itemTO.getStatus());

			switch(itemTO.getStatus()) {
				case "updated" : itemDAO.updateItem(itemTO); break;
				case "inserted" : itemDAO.insertItem(itemTO); break;
				case "deleted" : itemDAO.deleteItem(itemTO); break;
			}

		}

	}
}

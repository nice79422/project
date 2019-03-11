package kr.co.seoulit.logi.purchase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.logi.purchase.serviceFacade.PurchaseServiceFacade;
import kr.co.seoulit.logi.purchase.to.ItemTO;

@Controller
public class ItemController {

	@Autowired
	private PurchaseServiceFacade purchaseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("logi/purchase/findItemList.do")
	public void findItemList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<ItemTO> itemList = purchaseServiceFacade.findItemList();

		datasetBeanMapper.beansToDataset(outData, itemList, ItemTO.class);

	}
	
	@RequestMapping("logi/purchase/batchItem.do")
	public void batchItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<ItemTO> itemList = datasetBeanMapper.datasetToBeans(inData, ItemTO.class);

		purchaseServiceFacade.batchItem(itemList);
	}
}

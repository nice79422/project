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
import kr.co.seoulit.logi.purchase.to.MaterialPaymentTO;
import kr.co.seoulit.logi.purchase.to.OrderDetailTO;
import kr.co.seoulit.logi.purchase.to.OrderInfoTO;
import kr.co.seoulit.logi.purchase.to.StockTO;
import kr.co.seoulit.logi.purchase.to.WarehousingTO;

/************************************************************************
@Package		kr.co.seoulit.logi.purchase.controller
@Class			StockController.java
@Author			kanghokyeong
@Description	재고 관련 컨트롤러
@Create			2019.02.15
@Last Update   	2019.02.21 재고이력조회
				2019.02.26 wherehousing 리스트 조회 (wonminlee)
************************************************************************/

@Controller
public class StockController {
	@Autowired
	private PurchaseServiceFacade purchaseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//재고목록 조회
	@RequestMapping("logi/purchase/findStockList.do")
	public void findStockList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<StockTO> stockList = purchaseServiceFacade.findStockList();
		for (StockTO st : stockList) {
			System.out.println(st.getItemCode());
		}
		datasetBeanMapper.beansToDataset(outData, stockList, StockTO.class);
	}

	//부품재고이력 조회
	@RequestMapping("logi/purchase/findMaterialPaymentList.do")
	public void findMaterialPaymentList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<MaterialPaymentTO> materialPaymentList = purchaseServiceFacade.findMaterialPaymentList();

		datasetBeanMapper.beansToDataset(outData, materialPaymentList, MaterialPaymentTO.class);
	}
	
	//WarehousingList조회
	@RequestMapping("logi/purchase/findWarehousingList.do")
	public void findWarehousingList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<WarehousingTO> warehousingList = purchaseServiceFacade.findWarehousingList();

		datasetBeanMapper.beansToDataset(outData, warehousingList, WarehousingTO.class);

	}
	
	@RequestMapping("logi/purchase/registWarehousing.do")
	public void registWarehousing(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<OrderInfoTO> orderInfoList = datasetBeanMapper.datasetToBeans(inData, OrderInfoTO.class);

		List<OrderDetailTO> orderDetailList = datasetBeanMapper.datasetToBeans(inData, OrderDetailTO.class);

		List<StockTO> stockList = datasetBeanMapper.datasetToBeans(inData, StockTO.class);

		List<WarehousingTO> warehousingList = datasetBeanMapper.datasetToBeans(inData, WarehousingTO.class);

		purchaseServiceFacade.registWarehousing(orderInfoList, orderDetailList, stockList, warehousingList);

		findStockList(request, response);

		findWarehousingList(request, response);

	}
	
	
}

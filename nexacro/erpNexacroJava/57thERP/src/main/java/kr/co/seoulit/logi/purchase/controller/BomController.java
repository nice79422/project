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
import kr.co.seoulit.logi.purchase.to.BomDeployTO;
import kr.co.seoulit.logi.purchase.to.BomTO;

@Controller
public class BomController {

	@Autowired
	private PurchaseServiceFacade purchaseServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//BOM목록  조회
	@RequestMapping("logi/purchase/findBomList.do")
	public void findBomList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");

		List<BomTO> bomList = purchaseServiceFacade.findBomList();

		datasetBeanMapper.beansToDataset(outData, bomList, BomTO.class);

	}

	//BOM전개 조회
	@RequestMapping("logi/purchase/findBomDeployList.do")
	public void findBomDeployList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		String itemCode = inData.getVariable("itemCode").getString();
		String deployCondition = inData.getVariable("deployCondition").getString();

		List<BomDeployTO> bomDeployList = purchaseServiceFacade.findBomDeployList(itemCode, deployCondition);

		datasetBeanMapper.beansToDataset(outData, bomDeployList, BomDeployTO.class);
	}
	
}

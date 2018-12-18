package com.test4th.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.AddressBean;
import com.test4th.base.to.SidoBean;
import com.test4th.base.to.SigunguBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.PlatformData;

public class PostController extends AbstractMiplatformMultiActionController {
    /* BaseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
    public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
        this.baseServiceFacade = baseServiceFacade;
    }

    /* 특별시, 광역시, 도를 조회하는 메서드 */
	public void findSido(PlatformData inData, PlatformData outData) throws Exception{
		List<SidoBean> sidoList=baseServiceFacade.findSido();
		datasetBeanMapper.beansToDataset(outData, sidoList, SidoBean.class);
	}

	/* 선택된 특별시, 광역시, 도에 해당하는 시,군,구를 조회하는 메서드 */
	public void findSiGunGuList(PlatformData inData, PlatformData outData) throws Exception{
		String sido = inData.getVariable("sido").getValue().getString();
		
		
		List<SigunguBean> sigunguList=baseServiceFacade.findSiGunGuList(sido);
		datasetBeanMapper.beansToDataset(outData, sigunguList, SigunguBean.class);
	}

	/* 주소를 찾는 메서드 */
	public void findAddrList(PlatformData inData, PlatformData outData) throws Exception{
		String sido = inData.getVariable("sido").getValue().getString();
		String sigungu = inData.getVariable("sigungu").getValue().getString();
		String roadName = inData.getVariable("roadName").getValue().getString();

		System.out.println("sido===>"+sido+"::sigungu===>"+sigungu+"::roadName===>"+roadName);
	

		Map<String, Object> addrList=new HashMap<>();
		addrList.put("sido", sido);
		addrList.put("sigungu", sigungu);
		addrList.put("roadName", roadName);

		List<AddressBean> addressList=baseServiceFacade.findAddrList(addrList);
		datasetBeanMapper.beansToDataset(outData, addressList, AddressBean.class);
	}
}

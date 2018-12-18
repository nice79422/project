package com.test4th.base.controller;

import java.util.ArrayList;
import java.util.List;

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.CodeBean;
import com.test4th.base.to.CodeInfoBean;
import com.test4th.base.to.DetailCodeBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.PlatformData;

public class CodeController extends AbstractMiplatformMultiActionController{
	/* BaseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
		this.baseServiceFacade = baseServiceFacade;
	}

	/* 코드목록을 반환하는 메서드 3차 때랑 좀 다름. 상위bean의 하위List에 넣었는데 여기선 addAll사용.. */
	public void findCodeList(PlatformData inData, PlatformData outData) throws Exception{
		List<CodeBean> codeList=baseServiceFacade.findCodeList(); /*뒤에서 상위bean속에 담아온 것을 꺼내여 담는다. out이 2개라서 .*/
		List<DetailCodeBean> detailCodeList=new ArrayList<DetailCodeBean>();
		for(CodeBean codeBean : codeList){
			List<DetailCodeBean> detailCodeBeanList=codeBean.getDetailCodeList();
			/* 자바로 ArrayList복사하는 메서드 : addAll */
			detailCodeList.addAll(detailCodeBeanList);
		}
		datasetBeanMapper.beansToDataset(outData, codeList, CodeBean.class);
		datasetBeanMapper.beansToDataset(outData, detailCodeList, DetailCodeBean.class);
	}

	public void batchCode(PlatformData inData, PlatformData outData) throws Exception{
		Dataset dataset =inData.getDataset("dsCode");
		/*존재하지 않으면 unknown 나오는 듯? 코드가 변동이 있을 경우. 삭제를 지웠기 때문에  없오도 될듯 . 있어도 되고 or이기 때문에*/
		if(dataset.getRowStatus(0)!="unknown" || dataset.getDeleteRowCount() >0){
			CodeInfoBean codeInfoBean=new CodeInfoBean(); /*코드와 상세코드 list를 담아 둠.(여러개를 고칠경우 )*/
			codeInfoBean.setCodeList(datasetBeanMapper.datasetToBeans(inData, CodeBean.class));
			codeInfoBean.setDetailCodeList(datasetBeanMapper.datasetToBeans(inData, DetailCodeBean.class));
			baseServiceFacade.batchCode(codeInfoBean);
		}else{
			List<DetailCodeBean> codeDetailList=datasetBeanMapper.datasetToBeans(inData, DetailCodeBean.class);
			baseServiceFacade.batchDetailCode(codeDetailList);
		}

		findCodeList(inData,outData);
	}


}
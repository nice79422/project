package com.test5th.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.CodeBean;
import com.test5th.base.to.CodeInfoBean;
import com.test5th.base.to.DetailCodeBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.PlatformData;


@Controller
public class CodeController{
	/* BaseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("base/findCodeList.do")
	public void findCodeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<CodeBean> codeList=baseServiceFacade.findCodeList();
		List<DetailCodeBean> detailCodeList=new ArrayList<DetailCodeBean>();
		for(CodeBean codeBean : codeList){
			List<DetailCodeBean> detailCodeBeanList=codeBean.getDetailCodeList();
			/* ArrayList를  ArrayList에 담을 수 있는 메서드 : addAll   ->add의 경우 ArrayList는 못담는듯 하다 에러가 난다 */
			detailCodeList.addAll(detailCodeBeanList);
		}
		datasetBeanMapper.beansToDataset(outData, codeList, CodeBean.class);
		datasetBeanMapper.beansToDataset(outData, detailCodeList, DetailCodeBean.class);
	}

	
	@RequestMapping("base/batchCode.do")
	public void batchCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		DataSet dataset =inData.getDataSet("dsCode");
		/*존재하지 않으면 unknown 나오는 듯? 코드가 변동이 있을 경우. 삭제를 지웠기 때문에  없오도 될듯 . 있어도 되고 or이기 때문에*/
		if(dataset.getRowCount() != 0 || dataset.getRemovedRowCount() >0){
			CodeInfoBean codeInfoBean=new CodeInfoBean(); /*코드와 상세코드 list를 담아 둠.(여러개를 고칠경우 )*/
			codeInfoBean.setCodeList(datasetBeanMapper.datasetToBeans(inData, CodeBean.class));
			codeInfoBean.setDetailCodeList(datasetBeanMapper.datasetToBeans(inData, DetailCodeBean.class));
			baseServiceFacade.batchCode(codeInfoBean);
		}else{
			List<DetailCodeBean> codeDetailList=datasetBeanMapper.datasetToBeans(inData, DetailCodeBean.class);
			baseServiceFacade.batchDetailCode(codeDetailList);
		}

		findCodeList(request,response);
	}


}
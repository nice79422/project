package com.test5th.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.DepartmentBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class DeptController {
	/* baseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	

    /* 부서목록을 조회하는 메서드 */
	@RequestMapping("base/findDeptList.do")
	public void findDeptList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<DepartmentBean> deptList=baseServiceFacade.findDeptList();
        datasetBeanMapper.beansToDataset(outData, deptList, DepartmentBean.class);
        /*
         * 	beansToDataset : bean --> dataset
         * */
    }
	
	/*부서관리 (등록/삭제/수정)*/
	@RequestMapping("base/batchDept.do")
	public void batchDept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * datasetToBean도 있다.
		 *
		 *  datasetToBeans : dataset --> bean
		 *
		 * */
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DepartmentBean> deptList=datasetBeanMapper.datasetToBeans(inData, DepartmentBean.class);
        baseServiceFacade.batchDept(deptList);

        findDeptList(request,response);
    }

	
}

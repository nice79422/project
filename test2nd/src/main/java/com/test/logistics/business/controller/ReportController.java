package com.test.logistics.business.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.logistics.business.sf.BusinessServiceFacade;
import com.test.logistics.business.to.EstimateReportBean;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportController extends MultiActionController {
	
	
    private ModelAndView modelAndView = null;
    private ModelMap modelMap = new ModelMap();
    private BusinessServiceFacade businessServiceFacade;

	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}


    public ModelAndView estimateReport(HttpServletRequest request, HttpServletResponse response) {
    	try {
			String orderDraftNo = request.getParameter("orderDraftNo");
			System.out.println("orderDraftNo:"+orderDraftNo);
			String format = request.getParameter("format");
			System.out.println("format:"+format);
			List<EstimateReportBean> estimateReportList = businessServiceFacade.findEstimateReport(orderDraftNo);

			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(estimateReportList);
			modelMap.put("source", source);
			modelMap.put("format", format);

			if (format.equals("pdf")) {
				modelAndView = new ModelAndView("pdfView", modelMap);
			}/* else if (format.equals("xls")) {
				modelAndView = new ModelAndView("xlsView", modelMap);
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
}


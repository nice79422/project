package com.test.common.servlet.mvc;

import java.io.FileInputStream;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;
import com.test.common.sl.ServiceLocator;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ReportController extends MultiActionController {

    private ModelAndView modelAndView = null;

    protected final Log logger = LogFactory.getLog(this.getClass());



    public ModelAndView estimateReport(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> parameters = new HashMap<>();
        if (logger.isDebugEnabled()) {
            logger.debug("ReportController -시작");
        }
        try {
    		response.setCharacterEncoding("utf-8");
        	String orderDraftNo = request.getParameter("orderDraftNo");
            parameters.put("orderDraftNo", orderDraftNo);

            DataSource dataSource = ServiceLocator.getInstance().getDataSource("jdbc/test");
            Connection conn = dataSource.getConnection();
            InputStream inputStream = new FileInputStream("C:/Users/hello/eclipse-workspace0/test/WebContent/report/estimate.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);

            out.println();
            out.flush();

        } catch (Exception e) {
        	if (logger.isFatalEnabled()) { logger.fatal("ReportController-에러");}
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("ReportController -끝");
        }
        return modelAndView;
    }
}


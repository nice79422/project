package com.test.logistics.business.dao;

import java.util.List;

import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.business.to.EstimateReportBean;

public interface EstimateDAO {
	int selectRowCount();
	List<EstimateBean> selectEstimate();
	List<EstimateDetailBean> selectEstimateDetail(String code);
	List<EstimateBean> selectEstimateList(int sr, int er);
	void insertEstimate(EstimateBean estimateBean, String clientCode);
	void updateEstimate(EstimateBean estimateBean);
	public List<EstimateReportBean> selectEstimateReport(String estimateCode);
	
	int selectEstimateDetailCount();
	int selectEstimateDetailRowCount(String searchCode);

	List<EstimateDetailBean> selectEstimateDetailList(int sr, int er, String searchCode);
	void insertEstimateDetail(EstimateDetailBean estimateDetailBean);
	void updateEstimateDetail(EstimateDetailBean estimateDetailBean);
	
	List<EstimateItemBean> selectEstimateReviewList(String startDate, String endDate);
	void updateStandByEstimate(EstimateBean estimateBean);
	
}

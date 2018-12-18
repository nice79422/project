package com.test.logistics.business.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;

public interface EstimateDAO {
	int selectRowCount();
	List<EstimateBean> selectEstimate();
	List<EstimateDetailBean> selectEstimateDetail(String code);
	List<EstimateBean> selectEstimateList(int sr, int er);
	void insertEstimate(EstimateBean estimateBean, String clientCode) throws DataAccessException;
	void updateEstimate(EstimateBean estimateBean);
	List<EstimateItemBean> selectEstimateReviewList(String startDate, String endDate);
	void updateStandByEstimate(EstimateBean estimateBean);
	void updateStandByContract(ContractBean contractBean);
	
}

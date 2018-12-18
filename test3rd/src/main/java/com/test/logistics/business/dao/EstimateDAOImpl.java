package com.test.logistics.business.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.business.to.EstimateReportBean;

public class EstimateDAOImpl extends IBatisDAO implements EstimateDAO{
	
	
	@SuppressWarnings("deprecation")
	public int selectRowCount() {
	return (int) getSqlMapClientTemplate().queryForObject("estimate.selectRowCount");
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<EstimateBean> selectEstimate(){
		return (List<EstimateBean>) getSqlMapClientTemplate().queryForList("estimate.selectEstimate");
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<EstimateDetailBean> selectEstimateDetail(String estimateCode) {
		return (List<EstimateDetailBean>) getSqlMapClientTemplate().queryForList("estimate.selectEstimateDetail",estimateCode);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EstimateBean> selectEstimateList(int sr, int er) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<EstimateBean>) getSqlMapClientTemplate().queryForList("estimate.selectEstimateList", map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void insertEstimate(EstimateBean estimateBean, String clientCode) {
		getSqlMapClientTemplate().update("estimate.insertEstimate", estimateBean);
	}

	@SuppressWarnings("deprecation")
	public void updateEstimate(EstimateBean estimateBean) {
		getSqlMapClientTemplate().update("estimate.updateEstimate", estimateBean);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<EstimateReportBean> selectEstimateReport(String estimateCode){
	return (List<EstimateReportBean>) getSqlMapClientTemplate().queryForList("estimate.selectEstimateReport", estimateCode);
	}

	
	@SuppressWarnings("deprecation")
	public int selectEstimateDetailCount() {
	return (int) getSqlMapClientTemplate().queryForObject("estimate.selectEstimateDetailCount");
	}
	
	@SuppressWarnings("deprecation")
	public int selectEstimateDetailRowCount(String searchCode) {
		return (int) getSqlMapClientTemplate().queryForObject("estimate.selectEstimateDetailRowCount", searchCode);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<EstimateDetailBean> selectEstimateDetailList(int sr, int er, String searchCode) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("estimateCode", searchCode);
		return (List<EstimateDetailBean>) getSqlMapClientTemplate().queryForList("estimate.selectEstimateDetailList", map);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void insertEstimateDetail(EstimateDetailBean estimateDetailBean) {
		getSqlMapClientTemplate().update("estimate.insertEstimateDetail", estimateDetailBean);
	}
	
	@SuppressWarnings("deprecation")
	public void updateEstimateDetail(EstimateDetailBean estimateDetailBean) {
		getSqlMapClientTemplate().update("estimate.updateEstimateDetail", estimateDetailBean);
	}

	@Override
	public List<EstimateItemBean> selectEstimateReviewList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return (List<EstimateItemBean>) getSqlMapClientTemplate().queryForList("estimate.selectEstimateReviewList", map);
	}

	@Override
	public void updateStandByEstimate(EstimateBean estimateBean) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("estimate.updateStandByEstimate", estimateBean);
	}
}
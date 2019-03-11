package kr.co.seoulit.logi.business.dao;


import java.util.List;

import kr.co.seoulit.logi.business.to.EstimateDetailTO;

public interface EstimateDetailDAO {

	public List<EstimateDetailTO> selectEstimateDetailList(String estimateNo);

	public void insertEstimateDetail(EstimateDetailTO estimateDetailTO);

	public void updateEstimateDetail(EstimateDetailTO estimateDetailTO);

	public void deleteEstimateDetail(EstimateDetailTO estimateDetailTO);
}
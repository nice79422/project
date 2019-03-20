package kr.co.seoulit.logi.business.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logi.business.to.EstimateTO;

public interface EstimateDAO {

	public List<EstimateTO> selectEstimateList(HashMap<String, Object> searchDate);

	public List<EstimateTO> selectEstimateDialog();

	public void insertEstimate(EstimateTO estimateTO);

	public void updateEstimate(EstimateTO estimateTO);

	public void deleteEstimate(EstimateTO estimateTO);
}
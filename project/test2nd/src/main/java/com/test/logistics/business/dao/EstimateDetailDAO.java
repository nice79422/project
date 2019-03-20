package com.test.logistics.business.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.logistics.business.to.EstimateDetailBean;

public interface EstimateDetailDAO {
	int selectRowCount();
	int selectRowCount(String searchCode);

	List<EstimateDetailBean> selectEstimateDetailLists(int sr, int er, String searchCode);
	void insertEstimateDetail(EstimateDetailBean estimateDetailBean) throws DataAccessException;
	void updateEstimateDetail(EstimateDetailBean estimateDetailBean);

}

package com.test.logistics.product.dao;

import java.util.List;

import com.test.common.exception.DataAccessException;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public interface MrpDAO {
	int selectTotalMrpListRowCount(String supply);

	int selectMrpListRowCount(String lineNo);

	List<MrpBean> selectMrpOpenList(String lineNo, String startDate, String endDate);

	List<MrpBean> selectTotalMrp(int sr, int er, String supply);

	
	void insertMrpGathering(MrpBean GatheringmrpBean) throws DataAccessException;

	int selectPurchaseOrderRowCount(String code);

	List<MrpBean> selectPurchaseOrder(int sr, int er, String code);

	void updateMrp(MrpBean bean);
	List<MrpBean> selectMrpCodeList(MrpBean mrpCode);

	List<MrpBean> dateMrp(String startDate, String endDate);

	int selectMrpTotalReviewCount();

	List<MrpTotalBean> selectMrpTotalReviewList(int startRow, int endRow);
}


package com.test.logistics.product.applicationservice;

import java.util.List;

import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public interface ProductApplicationService {
	int getMpsRowCount(String mpsStatus);
	int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo);
	List<MpsBean> findMpsList(int sr, int er);
	List<MpsBean> findMpsList(int sr, int er, String mpsStatus);
	List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus,String workplaceCode,String sDate, String eDate);
	/*List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus);*/
	void batchMpsProcess(List<MpsBean> mpsList);
	void mpsModify(List<MpsBean> mpsList);

	int getTotalMrpRowCount(String supply);
	int getMrpRowCount(String lineNo);
	List<MrpBean> mrpOpenOut(String lineNo, String startDate, String endDate);
	List<MrpBean> mrpGathering(int sr, int er, String workspace);
	
	void batchMrpGatheringProcess(List<MrpBean> mrpGatheringList);
	List<MrpBean> getMrpCodeList(List<MrpBean> mrpCode);
	List<MrpBean> dateMrp(String startDate, String endDate);
	int findMrpTotalReviewCount();
	List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow);
}

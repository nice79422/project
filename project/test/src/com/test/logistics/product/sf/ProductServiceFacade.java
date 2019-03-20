package com.test.logistics.product.sf;

import java.util.List;

import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public interface ProductServiceFacade {
	int getMpsRowCount(String mpsStatus);
	int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo);
	List<MpsBean> findMpsList(int sr, int er);
	List<MpsBean> findMpsList(int sr, int er, String mpsStatus);
	List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus,String workplaceCode,String sDate, String eDate);
	List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus);
	void batchMpsProcess(List<MpsBean> mpsList);
	
	List<MrpBean> dateMrp(String startDate,String endDate);
	
	void mpsModify(List<MpsBean> mpsList);

	int getTotalMrpRowCount(String supply);
	int getMrpRowCount(String lineNo);
	List<MrpBean> mrpOpenOut(String lineNo, String startDate, String endDate);
	List<MrpBean> getMrpGathering(int sr, int er, String workspace);
	
	void batchMrpGatheringProcess(List<MrpBean> mpsGatheringList);
	List<MrpBean> getMrpCodeList(List<MrpBean> mrpCode);
	
	public int findMrpTotalReviewCount();
	/*public List<MrpTotalBean> findMrpTotalReviewList();*/
	public List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow);
}

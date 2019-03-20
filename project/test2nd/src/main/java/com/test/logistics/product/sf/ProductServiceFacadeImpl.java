package com.test.logistics.product.sf;

import java.util.List;

import com.test.logistics.product.applicationservice.ProductApplicationService;
import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public class ProductServiceFacadeImpl implements ProductServiceFacade {

	ProductApplicationService productApplicationService;

	public void setProductApplicationService(ProductApplicationService productApplicationService) {
		this.productApplicationService = productApplicationService;
	}

	
	public int findMrpTotalReviewCount() {
		// TODO Auto-generated method stub
			return productApplicationService.findMrpTotalReviewCount();
		}

	
	public int getMpsRowCount(String mpsStatus) {
		return productApplicationService.getMpsRowCount(mpsStatus);
			
	}

	public int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		return productApplicationService.getMpsRowCount(mpsStatus, mrpStatus, lineNo);
	}

	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus) {
		return productApplicationService.findMpsList(sr, er, mpsStatus);
		
	}
	
	public List<MpsBean> findMpsList(int sr, int er) {
		return productApplicationService.findMpsList(sr, er);
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus) {
		return productApplicationService.findMpsList(sr, er, mpsStatus, mrpStatus);
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus, String lineNo, String sDate, String eDate) {
		return productApplicationService.findMpsList(sr, er, mpsStatus, mrpStatus , lineNo, sDate, eDate);
	}

	public void batchMpsProcess(List<MpsBean> mpsList) {
			productApplicationService.batchMpsProcess(mpsList);
	}
	
	public void mpsModify(List<MpsBean> mpsList) {
			productApplicationService.mpsModify(mpsList);
	}

	public int getTotalMrpRowCount(String supply) {
		return productApplicationService.getTotalMrpRowCount(supply);
	}

	public int getMrpRowCount(String lineNo) {
		return productApplicationService.getMrpRowCount(lineNo);
	}

	public List<MrpBean> mrpOpenOut(String lineNo, String startDate, String endDate) {
		return productApplicationService.mrpOpenOut(lineNo, startDate, endDate);
	}
	public List<MrpBean> getMrpCodeList(List<MrpBean> mrpCode){
		return productApplicationService.getMrpCodeList(mrpCode);
	}
	

	@Override
	public List<MrpBean> getMrpGathering(int sr, int er, String workspace) {
		return productApplicationService.mrpGathering(sr, er, workspace);
	}


	public void batchMrpGatheringProcess(List<MrpBean> mrpGatheringList) {
			productApplicationService.batchMrpGatheringProcess(mrpGatheringList);
	}
	
	public List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow) {
		return productApplicationService.findMrpTotalReviewList(startRow, endRow);
		}

	public int findMrpTotalCount() {
			return productApplicationService.findMrpTotalCount();
	}


	@Override
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		return productApplicationService.dateMrp(startDate, endDate);
	}
}

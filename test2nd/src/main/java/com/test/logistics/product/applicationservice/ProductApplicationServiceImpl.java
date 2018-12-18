package com.test.logistics.product.applicationservice;

import java.util.ArrayList;
import java.util.List;

import com.test.logistics.business.dao.ContractDAO;
import com.test.logistics.business.dao.ContractDetailDAO;
import com.test.logistics.item.dao.StockDAO;
import com.test.logistics.product.dao.MpsDAO;
import com.test.logistics.product.dao.MrpDAO;
import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public class ProductApplicationServiceImpl implements ProductApplicationService {

	
	private ContractDetailDAO contractDetailDAO;
	private MpsDAO mpsDAO;
	private MrpDAO mrpDAO;
	private StockDAO stockDAO;
	private ContractDAO contractDAO;
	
	

	public void setContractDetailDAO(ContractDetailDAO contractDetailDAO) {
		this.contractDetailDAO = contractDetailDAO;
	}

	public void setMpsDAO(MpsDAO mpsDAO) {
		this.mpsDAO = mpsDAO;
	}

	public void setMrpDAO(MrpDAO mrpDAO) {
		this.mrpDAO = mrpDAO;
	}

	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	public void setContractDAO(ContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}

	public int getMpsRowCount(String mpsStatus) {
		return mpsDAO.selectMpsListRowCount(mpsStatus);
	}

	public int findMrpTotalReviewCount() {
		return mrpDAO.selectMrpTotalReviewCount();
	}
	
	public int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		return mpsDAO.selectMpsListRowCount(mpsStatus, mrpStatus, lineNo);
	}

	public List<MpsBean> findMpsList(int sr, int er) {
		return mpsDAO.selectMpsList(sr, er);
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus) {
		return mpsDAO.selectMpsList(sr, er, mpsStatus);
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus) {
		return mpsDAO.selectMpsList(sr, er, mpsStatus, mrpStatus);
	}

	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus,String lineNo,String sDate, String eDate) {
		return mpsDAO.selectMpsList(sr, er, mpsStatus, mrpStatus,lineNo, sDate, eDate);
	}

	public void batchMpsProcess(List<MpsBean> mpsList) {
		
		String status = "";

		for (MpsBean mpsBean : mpsList) {
			status = mpsBean.getStatus();
			if (status.equals("INSERT")) {
				mpsDAO.insertMps(mpsBean);
				
				contractDetailDAO.updateContractDetail(mpsBean.getItemBean().getContractDetailBean());
				
				stockDAO.updateOutputScheduleStock(mpsBean.getPlanDate(), mpsBean.getPlanQuantity(),
						mpsBean.getItemCode());
			
				contractDAO.updateContract(mpsBean.getItemBean().getContractDetailBean());
				
			} else if (status.equals("UPDATE")) {
				mpsDAO.updateMps(mpsBean);
			}
		}
	}


	public void mpsModify(List<MpsBean> mpsList){
			for (MpsBean mpsbean : mpsList) {
					mpsDAO.updateMps(mpsbean);
			}


	}

	public int getTotalMrpRowCount(String supply) {
		return mrpDAO.selectTotalMrpListRowCount(supply);
	}

	public int getMrpRowCount(String lineNo) {
		return mrpDAO.selectMrpListRowCount(lineNo);
	}

	public List<MrpBean> mrpOpenOut( String lineNo, String startDate, String endDate) {
		List<MrpBean> list = mrpDAO.selectMrpOpenList(lineNo, startDate, endDate);
		return list;
	}

	@Override
	public List<MrpBean> mrpGathering(int sr, int er, String workspace){
		List<MrpBean> mrpBeanList = mrpDAO.selectTotalMrp(sr, er, workspace);
		return mrpBeanList;

	}

	
	
	public void batchMrpGatheringProcess(List<MrpBean> mrpGatheringList) {

		for (MrpBean mrpGatheringBean : mrpGatheringList) {
			mrpDAO.insertMrpGathering(mrpGatheringBean);
		}

	}
	
	public List<MrpBean> getMrpCodeList(List<MrpBean> mrpCode){
		
			List<MrpBean> mrpCodeList= new ArrayList<>();
			for (MrpBean mrpBean : mrpCode ){
			mrpCodeList=mrpDAO.selectMrpCodeList(mrpBean); }
			return mrpCodeList;
	}
	
	public List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		return mrpDAO.selectMrpTotalReviewList(startRow, endRow);
	}
	public int findMrpTotalCount() {
		// TODO Auto-generated method stub

		return mrpDAO.selectMrpTotalCount();
	}

	@Override
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<MrpBean> list = mrpDAO.dateMrp(startDate, endDate);
	
		return list;
	}
	}

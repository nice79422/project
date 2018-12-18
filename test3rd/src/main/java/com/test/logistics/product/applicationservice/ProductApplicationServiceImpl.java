package com.test.logistics.product.applicationservice;

import java.util.ArrayList;
import java.util.List;

import com.test.logistics.business.dao.ContractDAO;
import com.test.logistics.item.dao.StockDAO;
import com.test.logistics.product.dao.MpsDAO;
import com.test.logistics.product.dao.MrpDAO;
import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public class ProductApplicationServiceImpl implements ProductApplicationService {

	
	private ContractDAO contractDAO;
	private MpsDAO mpsDAO;
	private MrpDAO mrpDAO;
	private StockDAO stockDAO;

	
	public void setStockDAO(StockDAO stockDAO) {
		this.stockDAO = stockDAO;
	}

	public void setContractDAO(ContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}

	public void setMpsDAO(MpsDAO mpsDAO) {
		this.mpsDAO = mpsDAO;
	}

	public void setMrpDAO(MrpDAO mrpDAO) {
		this.mrpDAO = mrpDAO;
	}

	
	
	
	
	
	public int getMpsRowCount(String mpsStatus) {

		return mpsDAO.selectMpsListRowCount(mpsStatus);
	}

	public int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		return mpsDAO.selectMpsLineListRowCount(mpsStatus, mrpStatus, lineNo);
	}

	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus) {
		List<MpsBean> mpsBeanList = mpsDAO.selectNotApplyMpsList(sr, er, mpsStatus);
		 return mpsBeanList;
	}
	


	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus,String lineNo,String sDate, String eDate) {
		List<MpsBean> mpsList = mpsDAO.selectMpsList(sr, er, mpsStatus, mrpStatus,lineNo, sDate, eDate);
			 return mpsList;
	}

	public void batchMpsProcess(List<MpsBean> mpsList) {
			String status = "";

		for (MpsBean mpsBean : mpsList) {
			status = mpsBean.getStatus();
			if (status.equals("INSERT")) {
				mpsDAO.insertMps(mpsBean);
				contractDAO.updateContractDetail(mpsBean.getItemBean().getContractDetailBean());
				
			/*stockDAO.updateOutputScheduleStock(mpsBean.getPlanDate(), mpsBean.getPlanQuantity(),
						mpsBean.getItemCode());
			*/
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

	@Override
	public List<MpsBean> findMpsList(int sr, int er) {
		List<MpsBean> mpsApplyBeanList =mpsDAO.selectApplyMpsList(sr, er);
		return mpsApplyBeanList;
	}

	@Override
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		// TODO Auto-generated method stub
		List<MrpBean> list = mrpDAO.dateMrp(startDate, endDate);
		return list;
	}

	@Override
	public int findMrpTotalReviewCount() {
		return mrpDAO.selectMrpTotalReviewCount();
	}

	@Override
	public List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow) {
		return mrpDAO.selectMrpTotalReviewList(startRow, endRow);
	}

}


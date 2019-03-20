package com.test.logistics.product.applicationservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.logistics.business.dao.ContractDAO;
import com.test.logistics.business.dao.ContractDAOImpl;
import com.test.logistics.business.dao.ContractDetailDAO;
import com.test.logistics.business.dao.ContractDetailDAOImpl;
import com.test.logistics.item.dao.StockDAO;
import com.test.logistics.item.dao.StockDAOImpl;
import com.test.logistics.product.dao.MpsDAO;
import com.test.logistics.product.dao.MpsDAOImpl;
import com.test.logistics.product.dao.MrpDAO;
import com.test.logistics.product.dao.MrpDAOImpl;
import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public class ProductApplicationServiceImpl implements ProductApplicationService {
	protected final Log logger = LogFactory.getLog(getClass());

	private ProductApplicationServiceImpl() {
	}

	private static ProductApplicationService instance = new ProductApplicationServiceImpl();

	public static ProductApplicationService getInstance() {
		return instance;
	}

	private ContractDetailDAO contractItemDAO = ContractDetailDAOImpl.getInstance();
	private MpsDAO mpsDAO = MpsDAOImpl.getInstance();
	private MrpDAO mrpDAO = MrpDAOImpl.getInstance();
	private StockDAO stockDAO = StockDAOImpl.getInstance();
	private ContractDAO contractDAO = ContractDAOImpl.getInstance();

	public int getMpsRowCount(String mpsStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-끝");
		}
		return mpsDAO.selectMpsListRowCount(mpsStatus);
	}

	public int findMrpTotalReviewCount() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled())
			logger.debug("START");

		if (logger.isDebugEnabled())
			logger.debug("END");

		return mrpDAO.selectMrpTotalReviewCount();
	}
	
	public int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-끝");
		}

		return mpsDAO.selectMpsListRowCount(mpsStatus, mrpStatus, lineNo);
	}

	public List<MpsBean> findMpsList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsDAO.selectMpsList(sr, er);
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsDAO.selectMpsList(sr, er, mpsStatus);
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsDAO.selectMpsList(sr, er, mpsStatus, mrpStatus);
	}

	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus,String lineNo,String sDate, String eDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsDAO.selectMpsList(sr, er, mpsStatus, mrpStatus,lineNo, sDate, eDate);
	}

	public void batchMpsProcess(List<MpsBean> mpsList) {
		if (logger.isDebugEnabled()) {
			logger.debug("batchMpsProcess(...)-시작");
		}

		String status = "";

		for (MpsBean mpsBean : mpsList) {
			status = mpsBean.getStatus();
			if (status.equals("INSERT")) {
				mpsDAO.insertMps(mpsBean);
				contractItemDAO.updateContractDetail(mpsBean.getItemBean().getContractDetailBean());
				stockDAO.updateOutputScheduleStock(mpsBean.getPlanDate(), mpsBean.getPlanQuantity(),
						mpsBean.getItemCode());
				contractDAO.updateContract(mpsBean.getItemBean().getContractDetailBean());
			} else if (status.equals("UPDATE")) {
				mpsDAO.updateMps(mpsBean);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("batchMpsProcess(...)-끝");
		}
	}

	public void mpsModify(List<MpsBean> mpsList){
		if (logger.isDebugEnabled()) {logger.debug("mpsModify() - end");}

			for (MpsBean mpsbean : mpsList) {
					mpsDAO.updateMps(mpsbean);
			}

			if (logger.isDebugEnabled()) {logger.debug("mpsModify() - end");}

	}

	public int getTotalMrpRowCount(String supply) {
		if (logger.isDebugEnabled()) {
			logger.debug("getTotalMrpRowCount(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getTotalMrpRowCount(...)-끝");
		}
		return mrpDAO.selectTotalMrpListRowCount(supply);
	}

	public int getMrpRowCount(String lineNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMrpRowCount(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getMrpRowCount(...)-끝");
		}
		return mrpDAO.selectMrpListRowCount(lineNo);
	}

	public List<MrpBean> mrpOpenOut( String lineNo, String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-끝");
		}
		List<MrpBean> list = mrpDAO.selectMrpOpenList(lineNo, startDate, endDate);
	
		return list;
	}

	@Override
	public List<MrpBean> mrpGathering(int sr, int er, String workspace){
		if (logger.isDebugEnabled()) {logger.debug("mrpGathering() - start");}


		List<MrpBean> mrpBeanList = mrpDAO.selectTotalMrp(sr, er, workspace);
	
		if (logger.isDebugEnabled()) {logger.debug("mrpGathering() - end");}
		return mrpBeanList;

	}

	
	
	public void batchMrpGatheringProcess(List<MrpBean> mrpGatheringList) {
		if (logger.isDebugEnabled()) {
			logger.debug("mrpCrud(...)-시작");
		}

		for (MrpBean mrpGatheringBean : mrpGatheringList) {
			mrpDAO.insertMrpGathering(mrpGatheringBean);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mrpCrud(...)-끝");
		}
	}
	
	public List<MrpBean> getMrpCodeList(List<MrpBean> mrpCode){
		if (logger.isDebugEnabled()) {logger.debug("getMrpCodeList - start");}
		
			List<MrpBean> mrpCodeList= new ArrayList<>();
			for (MrpBean mrpBean : mrpCode ){
			mrpCodeList=mrpDAO.selectMrpCodeList(mrpBean); }
			
			
			if (logger.isDebugEnabled()) {logger.debug("getMrpCodeList - end");}
			return mrpCodeList;
	}
	
	public List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled())
			logger.debug("START");

		if (logger.isDebugEnabled())
			logger.debug("END");

		return mrpDAO.selectMrpTotalReviewList(startRow, endRow);
	}
	public int findMrpTotalCount() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled())
			logger.debug("START");

		if (logger.isDebugEnabled())
			logger.debug("END");

		return mrpDAO.selectMrpTotalCount();
	}

	@Override
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-끝");
		}
		List<MrpBean> list = mrpDAO.dateMrp(startDate, endDate);
	
		return list;
	}
	}

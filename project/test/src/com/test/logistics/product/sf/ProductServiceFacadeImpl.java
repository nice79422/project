package com.test.logistics.product.sf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.product.applicationservice.ProductApplicationService;
import com.test.logistics.product.applicationservice.ProductApplicationServiceImpl;
import com.test.logistics.product.to.MpsBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

public class ProductServiceFacadeImpl implements ProductServiceFacade {
	protected final Log logger = LogFactory.getLog(getClass());
	DataSourceTransactionManager dataSourceTransactionManager;

	private ProductServiceFacadeImpl() {
		dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	}

	private static ProductServiceFacade instance = new ProductServiceFacadeImpl();

	public static ProductServiceFacade getInstance() {
		return instance;
	}

	ProductApplicationService productApplicationService = ProductApplicationServiceImpl.getInstance();

	
	public int findMrpTotalReviewCount() {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled())
			logger.debug("START");

		dataSourceTransactionManager.beginTransaction();
		int mrpTotalReviewCount=0;

		try{
			mrpTotalReviewCount=productApplicationService.findMrpTotalReviewCount();
			dataSourceTransactionManager.commitTransaction();
		}catch (DataAccessException e) {
			// TODO: handle exception
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();

			throw e;
		}

		if(logger.isDebugEnabled())
			logger.debug("END");

		return mrpTotalReviewCount;
	}

	
	public int getMpsRowCount(String mpsStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		int mpsCount;
		try {
			mpsCount = productApplicationService.getMpsRowCount(mpsStatus);
			
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getMpsRowCount(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-끝");
		}
		return mpsCount;
	}

	public int getMpsRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		int mpsCount = 0;
		try {
			mpsCount = productApplicationService.getMpsRowCount(mpsStatus, mrpStatus, lineNo);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getMpsRowCount(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getMpsRowCount(...)-끝");
		}
		return mpsCount;
	}

	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		List<MpsBean> mpsList = null;
		try {
			mpsList = productApplicationService.findMpsList(sr, er, mpsStatus);
		
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("MpsList(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsList;
	}
	
	public List<MpsBean> findMpsList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		List<MpsBean> mpsList = null;
		try {
			mpsList = productApplicationService.findMpsList(sr, er);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("MpsList(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsList;
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		List<MpsBean> mpsList = null;
		try {
			mpsList = productApplicationService.findMpsList(sr, er, mpsStatus, mrpStatus);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("MpsList(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsList;
	}
	
	public List<MpsBean> findMpsList(int sr, int er, String mpsStatus, String mrpStatus, String lineNo, String sDate, String eDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		List<MpsBean> mpsList = null;
		try {
			mpsList = productApplicationService.findMpsList(sr, er, mpsStatus, mrpStatus , lineNo, sDate, eDate);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("MpsList(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("MpsList(...)-끝");
		}
		return mpsList;
	}

	public void batchMpsProcess(List<MpsBean> mpsList) {
		if (logger.isDebugEnabled()) {
			logger.debug("batchMpsProcess(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			productApplicationService.batchMpsProcess(mpsList);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("batchMpsProcess(...)-끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("batchMpsProcess(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	
	

	public void mpsModify(List<MpsBean> mpsList) {
		if (logger.isDebugEnabled()) {
			logger.debug("mpsModify() - end");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			productApplicationService.mpsModify(mpsList);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("mpsModify() - end");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public int getTotalMrpRowCount(String supply) {
		if (logger.isDebugEnabled()) {
			logger.debug("getTotalMrpRowCount(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		int mrpCount = 0;
		try {
			mrpCount = productApplicationService.getTotalMrpRowCount(supply);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getTotalMrpRowCount(...)에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getTotalMrpRowCount(...)-끝");
		}
		return mrpCount;
	}

	public int getMrpRowCount(String lineNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("getMrpRowCount(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		int mrpCount = 0;
		try {
			mrpCount = productApplicationService.getMrpRowCount(lineNo);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getMrpRowCount(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getMrpRowCount(...)-끝");
		}
		return mrpCount;
	}

	public List<MrpBean> mrpOpenOut(String lineNo, String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		List<MrpBean> mrpOpenOutList = null;
		try {
			mrpOpenOutList = productApplicationService.mrpOpenOut(lineNo, startDate, endDate);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("mrpOpenOut(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-끝");
		}
		return mrpOpenOutList;
	}
	public List<MrpBean> getMrpCodeList(List<MrpBean> mrpCode){
		if (logger.isDebugEnabled()) {logger.debug("getMrpCodeList - start");}
		dataSourceTransactionManager.beginTransaction();
		List<MrpBean> mrpCodeList = new ArrayList<>();
		try {
			mrpCodeList=productApplicationService.getMrpCodeList(mrpCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {logger.debug("getMrpCodeList - end");}
			return mrpCodeList;
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	

	@Override
	public List<MrpBean> getMrpGathering(int sr, int er, String workspace) {
		if (logger.isDebugEnabled()) {
			logger.debug("mrpGathering() - start");
		}
		dataSourceTransactionManager.beginTransaction();

		List<MrpBean> mrpBeanList = new ArrayList<>();

		try {
			mrpBeanList = productApplicationService.mrpGathering(sr, er, workspace);

			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("mrpGathering() - end");
			}
			return mrpBeanList;
		} catch (DataAccessException e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}


	public void batchMrpGatheringProcess(List<MrpBean> mrpGatheringList) {
		if (logger.isDebugEnabled()) {
			logger.debug("mpsCrud() - start");
		}
		dataSourceTransactionManager.beginTransaction();

		try {

			productApplicationService.batchMrpGatheringProcess(mrpGatheringList);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("mpsCrud() - end");
			}
		} catch (Exception e) {
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	
	public List<MrpTotalBean> findMrpTotalReviewList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled())
			logger.debug("START");

		dataSourceTransactionManager.beginTransaction();
		List<MrpTotalBean> mrpTotalReviewList = null;

		try{
			mrpTotalReviewList=productApplicationService.findMrpTotalReviewList(startRow, endRow);
			dataSourceTransactionManager.commitTransaction();
		}catch (DataAccessException e) {
			// TODO: handle exception
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();

			throw e;
		}
		if (logger.isDebugEnabled())
			logger.debug("END");

		return mrpTotalReviewList;
	}

	public int findMrpTotalCount() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled())
			logger.debug("START");

		dataSourceTransactionManager.beginTransaction();
		int mrpTotalCount = 0;

		try{
			mrpTotalCount=productApplicationService.findMrpTotalCount();
			dataSourceTransactionManager.commitTransaction();
		}catch (DataAccessException e) {
			// TODO: handle exception
			logger.fatal(e.getMessage());
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if(logger.isDebugEnabled())
			logger.debug("END");

		return mrpTotalCount;
	}


	@Override
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-시작");
		}
		dataSourceTransactionManager.beginTransaction();
		List<MrpBean> dateMrpList = null;
		try {
			dateMrpList = productApplicationService.dateMrp(startDate, endDate);
			dataSourceTransactionManager.commitTransaction();
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("mrpOpenOut(...)-에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut(...)-끝");
		}
		return dateMrpList;
	}

}

package com.test.logistics.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;

public class EstimateDAOImpl implements EstimateDAO{
	private static EstimateDAO instance;
	private EstimateDAOImpl(){}
	public static EstimateDAO getInstance() {
		if(instance ==null)
			instance =new EstimateDAOImpl();
		return instance;
	}

	protected final Log logger = LogFactory.getLog(getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();


	public int selectRowCount() {
		if (logger.isDebugEnabled()) { logger.debug("selectRowCount()-시작");}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM ESTIMATE");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
		
			if (logger.isDebugEnabled()) { logger.debug("selectRowCount()-끝");}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectRowCount()-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	public List<EstimateBean> selectEstimate() {
		if (logger.isDebugEnabled()) { logger.debug("selectEstimate(...)-시작");}
		
		List<EstimateBean> estimateList = new ArrayList<EstimateBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM ESTIMATE  ");
			query.append(" ORDER BY ESTIMATE_CODE  ");
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				EstimateBean estimate = new EstimateBean();
				estimate.setEstimateCode(rs.getString("ESTIMATE_CODE"));
				estimate.setEstimateDate(rs.getString("ESTIMATE_DATE"));
				estimate.setClientCode(rs.getString("CLIENT_CODE"));
				estimate.setContractStatus(rs.getString("CONTRACT_STATUS"));
				estimateList.add(estimate);
				
			}
			
			if (logger.isDebugEnabled()) { logger.debug("selectEstimate(...)-끝");}
			return estimateList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectEstimate(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	public List<EstimateDetailBean> selectEstimateDetail(String code) {
		if (logger.isDebugEnabled()) { logger.debug("selectEstimateDetail(...)-시작");}
		List<EstimateDetailBean> estimateList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT d.ITEM_CODE,d.ESTIMATE_AMOUNT,e.CLIENT_CODE FROM ESTIMATE e ,ESTIMATE_DETAIL d ");
			query.append(" WHERE e.ESTIMATE_CODE=d.ESTIMATE_CODE AND e.CONTRACT_STATUS='N' ");
			query.append(" AND e.ESTIMATE_CODE= ? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EstimateDetailBean estimateDetail = new EstimateDetailBean();
				estimateDetail.setItemCode(rs.getString("ITEM_CODE"));
				estimateDetail.setEstimateAmount(rs.getString("ESTIMATE_AMOUNT"));
				estimateDetail.setClientCode(rs.getString("CLIENT_CODE"));
						
				estimateList.add(estimateDetail);
				
			}
			if (logger.isDebugEnabled()) { logger.debug("selectEstimateDetail(...)-끝");}
			return estimateList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectEstimateDetail(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	
	
	
	

	public List<EstimateBean> selectEstimateList(int sr, int er) {
		if (logger.isDebugEnabled()) { logger.debug("selectEstimateList(...)-시작");}
		List<EstimateBean> estimateList = new ArrayList<EstimateBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM ESTIMATE ");
			query.append(" WHERE ROWNUM BETWEEN ? AND ? ");
			/*query.append(" AND CONTRACT_STATUS='N' ");*/
			query.append(" ORDER BY ESTIMATE_CODE ");
			

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EstimateBean estimate = new EstimateBean();
				estimate.setEstimateCode(rs.getString("ESTIMATE_CODE"));
				estimate.setEstimateDate(rs.getString("ESTIMATE_DATE"));
				estimate.setClientCode(rs.getString("CLIENT_CODE"));
				estimate.setContractStatus(rs.getString("CONTRACT_STATUS"));

				estimateList.add(estimate);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectEstimateList(...)-끝");}
			return estimateList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectEstimateList(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	@Override
	public void insertEstimate(EstimateBean estimateBean, String clientCode) throws DataAccessException {
		if (logger.isDebugEnabled()) { logger.debug("insertEstimate(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt=null;

		StringBuffer query = new StringBuffer();
		
		query.append(" INSERT INTO ESTIMATE VALUES ( ? , ? , ? , ? , ?  ) ");

		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, estimateBean.getEstimateCode());
			pstmt.setString(2, estimateBean.getEstimateDate());
			pstmt.setString(3, estimateBean.getClientCode());
			pstmt.setString(4, estimateBean.getContractStatus());
			pstmt.setString(5, estimateBean.getStandByStatus());
			
			

			pstmt.executeUpdate();

			if (logger.isDebugEnabled()) { logger.debug("insertEstimate(...)-끝");}
		} catch (SQLException e) {
			if (logger.isFatalEnabled()) { logger.fatal("insertEstimate(...)-에러");}
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateEstimate(EstimateBean estimateBean) {
		
		if (logger.isDebugEnabled()) { logger.debug("updateEstimate(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer query = new StringBuffer();
		query.append(" UPDATE ESTIMATE SET CONTRACT_STATUS='Y' ");
		query.append(" WHERE ESTIMATE_CODE=? ");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, estimateBean.getEstimateCode());
			pstmt.executeUpdate();

			if (logger.isDebugEnabled()) { logger.debug("updateEstimate(...)-끝");}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) { logger.fatal("updateEstimate(...)-에러");}
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	
	public List<EstimateItemBean> selectEstimateReviewList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled())
			logger.debug("START");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EstimateItemBean> estimateItemList = new ArrayList<>();

		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM ESTIMATE ");
			query.append(" WHERE ESTIMATE_DATE BETWEEN ? AND ? ORDER BY ESTIMATE_CODE DESC ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				EstimateItemBean estimateItemBean = new EstimateItemBean();
				/*estimateItemBean.setEstimateItemCode(rs.getString("ESTIMATE_DETAIL_CODE"));*/
				estimateItemBean.setEstimateCode(rs.getString("ESTIMATE_CODE"));
				estimateItemBean.setClientCode(rs.getString("CLIENT_CODE"));
				estimateItemBean.setContractStatus(rs.getString("CONTRACT_STATUS"));
				/*estimateItemBean.setItemCode(rs.getString("ITEM_CODE"));*/
				/*estimateItemBean.setEstimateAmount(rs.getString("ESTIMATE_AMOUNT"));*/
				estimateItemBean.setEstimateDate(rs.getString("ESTIMATE_DATE"));
				estimateItemBean.setStandByStatus(rs.getString("STANDBY_STATUS"));
				/*estimateItemBean.setItemName(rs.getString("ITEM_NAME"));
				estimateItemBean.setUnit(rs.getString("UNIT"));
				estimateItemBean.setUnitPrice(rs.getString("UNIT_PRICE"));*/
				estimateItemList.add(estimateItemBean);
			}

			if (logger.isDebugEnabled())
				logger.debug("END");

			return estimateItemList;
		}catch (Exception e) {
			// TODO: handle exception
			logger.fatal(e.getMessage());
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	public void updateStandByEstimate(EstimateBean estimateBean) {
		// TODO Auto-generated method stub
		
		if (logger.isDebugEnabled()) { logger.debug("updateEstimate(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer query = new StringBuffer();
		query.append(" UPDATE ESTIMATE SET STANDBY_STATUS='Y' ");
		query.append(" WHERE ESTIMATE_CODE=? ");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, estimateBean.getEstimateCode());
			pstmt.executeUpdate();
			System.out.println("@@@@@@@@@@@@@@@"+estimateBean.getEstimateCode());
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) { logger.debug("updateEstimate(...)-끝");}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) { logger.fatal("updateEstimate(...)-에러");}
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
		
	}
	@Override
	public void updateStandByContract(ContractBean contractBean) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) { logger.debug("updateEstimate(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer query = new StringBuffer();
		query.append(" UPDATE CONTRACT SET STANDBY_STATUS='Y' ");
		query.append(" WHERE CONTRACT_CODE=? ");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, contractBean.getContractCode());
			pstmt.executeUpdate();
			
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) { logger.debug("updateEstimate(...)-끝");}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) { logger.fatal("updateEstimate(...)-에러");}
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
}



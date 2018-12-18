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
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class EstimateDetailDAOImpl implements EstimateDetailDAO {
	protected final Log logger = LogFactory.getLog(getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	private static EstimateDetailDAO instance;

	private EstimateDetailDAOImpl() {
	}

	public static EstimateDetailDAO getInstance() {
		if (instance == null)
			instance = new EstimateDetailDAOImpl();

		return instance;
	}

	public int selectRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("selectRowCount()-시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM ESTIMATE_DETAIL");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) {
				logger.debug("selectRowCount()-끝");
			}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectRowCount()-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public int selectRowCount(String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectRowCount(...)-시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM ESTIMATE_DETAIL WHERE ESTIMATE_CODE=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, searchCode);
			rs = pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) {
				logger.debug("selectRowCount(...)-끝");
			}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectRowCount(...)-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}


	public List<EstimateDetailBean> selectEstimateDetailLists(int sr, int er, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectEstimateDetailLists(...)-시작");
		}
		List<EstimateDetailBean> contractDetailList = new ArrayList<EstimateDetailBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT  * FROM ESTIMATE_DETAIL ");
			query.append(" WHERE ESTIMATE_CODE = ? ");
			query.append(" AND ROWNUM BETWEEN ? AND ? ");
			query.append(" ORDER BY ESTIMATE_DETAIL_CODE ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
		
			pstmt.setString(1, searchCode);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EstimateDetailBean estimateDetail = new EstimateDetailBean();
				estimateDetail.setEstimateDetailCode(rs.getString("ESTIMATE_DETAIL_CODE"));
				estimateDetail.setItemBean(new ItemBean());
				estimateDetail.setItemCode(rs.getString("ITEM_CODE"));
				estimateDetail.getItemBean().setItemCode(rs.getString("ITEM_CODE"));
				estimateDetail.setEstimateAmount(rs.getString("ESTIMATE_AMOUNT"));
				estimateDetail.setEstimateDetailUnitPrice(rs.getString("ESTIMATE_DETAIL_UNIT_PRICE"));

				contractDetailList.add(estimateDetail);
			
			}
			if (logger.isDebugEnabled()) {
				logger.debug("selectEstimateDetailLists(...)-끝");
			}
			return contractDetailList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectEstimateDetailLists(...)-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}


	@Override
	public void insertEstimateDetail(EstimateDetailBean estimateDetailBean) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("insertEstimateDetail(...)-시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();
	
		query.append(" INSERT INTO ESTIMATE_DETAIL VALUES ( ?||'-'||lpad(ESTIMATE_DETAIL_CODE_SEQ.NEXTVAL,5,'00000') , ? , ? , ?, ? ) ");
		
		try {
			con = dataSourceTransactionManager.getConnection();	
			pstmt = con.prepareStatement(query.toString());

			 pstmt.setString(1, estimateDetailBean.getEstimateCode());
			 pstmt.setString(2, estimateDetailBean.getItemCode());
			 pstmt.setString(3, estimateDetailBean.getEstimateCode());
			 pstmt.setString(4, estimateDetailBean.getEstimateAmount());
			 pstmt.setString(5, estimateDetailBean.getEstimateDetailUnitPrice());
			 
			pstmt.executeUpdate();
		
			if (logger.isDebugEnabled()) {
				logger.debug("insertEstimateDetail(...)-끝");
			}
		} catch (SQLException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("insertEstimateDetail(...)-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateEstimateDetail(EstimateDetailBean estimateDetailBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateEstimateDetail(...)-시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CONTRACT_DETAIL SET MPS_STATUS='Y' ");
		query.append("WHERE CONTRACT_DETAIL_CODE=?");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			//pstmt.setString(1, estimateDetailBean.getContractDetailCode());

			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("updateEstimateDetail(...)-끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("updateEstimateDetail(...)-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	public ArrayList<EstimateDetailBean> selectEstimateDetailList(String estimateNo) {

	      if (logger.isDebugEnabled()) {
	         logger.debug("EstimateDetailDAOImpl : selectEstimateDetailList 시작");
	      }

	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      ArrayList<EstimateDetailBean> estimateDetailTOList = new ArrayList<EstimateDetailBean>();

	      try {
	         conn = dataSourceTransactionManager.getConnection();

	         StringBuffer query = new StringBuffer();
	         query.append("SELECT * FROM ESTIMATE_DETAIL WHERE ESTIMATE_CODE =?");
	         pstmt = conn.prepareStatement(query.toString());
	         pstmt.setString(1, estimateNo);

	         rs = pstmt.executeQuery();

	         EstimateDetailBean bean = null;

	         while (rs.next()) {

	            bean = new EstimateDetailBean();

	            bean.setEstimateDetailCode(rs.getString("ESTIMATE_DETAIL_CODE"));
	            bean.setItemCode(rs.getString("ITEM_CODE"));
	            bean.setEstimateCode(rs.getString("ESTIMATE_CODE"));
	            bean.setEstimateAmount(rs.getString("ESTIMATE_AMOUNT"));
	            bean.setEstimateDetailUnitPrice(rs.getString("ESTIMATE_DETAIL_UNIT_PRICE"));
	            
	           
	        

	            estimateDetailTOList.add(bean);

	         }

	         return estimateDetailTOList;

	      } catch (Exception sqle) {

	         throw new DataAccessException(sqle.getMessage());

	      } finally {

	         dataSourceTransactionManager.close(pstmt, rs);

	      }
	   }
	
	
}

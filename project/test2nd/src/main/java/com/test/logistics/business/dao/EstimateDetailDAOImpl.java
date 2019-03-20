package com.test.logistics.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.item.to.ItemBean;

public class EstimateDetailDAOImpl implements EstimateDetailDAO {
	
	private DataSourceTransactionManager dataSourceTransactionManager ;


	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}
	

	public int selectRowCount() {
	
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
		
			return rs.getInt(1);
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public int selectRowCount(String searchCode) {
		
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
		
			return rs.getInt(1);
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}


	public List<EstimateDetailBean> selectEstimateDetailLists(int sr, int er, String searchCode) {
		
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
		
			return contractDetailList;
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}



	@Override
	public void insertEstimateDetail(EstimateDetailBean estimateDetailBean) throws DataAccessException {
	
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
		
			
		} catch (SQLException e) {
			
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateEstimateDetail(EstimateDetailBean estimateDetailBean) {
		
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
		
		} catch (Exception e) {
		
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

}

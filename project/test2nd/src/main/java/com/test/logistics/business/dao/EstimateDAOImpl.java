package com.test.logistics.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;
import com.test.logistics.business.to.EstimateReportBean;

public class EstimateDAOImpl implements EstimateDAO{
	
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
			query.append("SELECT COUNT(*) FROM ESTIMATE");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
		
		
			return rs.getInt(1);
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	public List<EstimateBean> selectEstimate() {
		
		
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
			
		
			return estimateList;
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	public List<EstimateDetailBean> selectEstimateDetail(String code) {
		
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
		
			return estimateList;
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	
	
	
	

	public List<EstimateBean> selectEstimateList(int sr, int er) {
		
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
		
			return estimateList;
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	@Override
	public void insertEstimate(EstimateBean estimateBean, String clientCode) throws DataAccessException {
		
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

		
		} catch (SQLException e) {
		
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateEstimate(EstimateBean estimateBean) {
		
		
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

		
		} catch (Exception e) {
		
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	
	public List<EstimateItemBean> selectEstimateReviewList(String startDate, String endDate) {
		// TODO Auto-generated method stub
		
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

		
			return estimateItemList;
		}catch (Exception e) {
			// TODO: handle exception
		
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	public void updateStandByEstimate(EstimateBean estimateBean) {
		// TODO Auto-generated method stub
		
		
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
		
		} catch (Exception e) {
		
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
		
	}
	@Override
	public void updateStandByContract(ContractBean contractBean) {
		// TODO Auto-generated method stub
		
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
		
		} catch (Exception e) {
		
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
		
	}
		public List<EstimateReportBean> selectEstimateReport(String estimateCode){

			List<EstimateReportBean> EstimateReportBeanList = new ArrayList<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT e.ESTIMATE_CODE, ei.ESTIMATE_DETAIL_CODE, ei.ITEM_CODE, i.ITEM_NAME, v.CLIENT_NAME, e.ESTIMATE_DATE, ");
				query.append(" ei.ESTIMATE_AMOUNT, ei.ESTIMATE_DETAIL_UNIT_PRICE, ei.ESTIMATE_AMOUNT*ei.ESTIMATE_DETAIL_UNIT_PRICE ESTIMATE_SUM ");
				query.append(" FROM ESTIMATE e , ESTIMATE_DETAIL ei, CLIENT v, ITEM i WHERE e.ESTIMATE_CODE = ei.ESTIMATE_CODE ");
				query.append(" AND v.CLIENT_CODE = e.CLIENT_CODE AND i.item_code = ei.ITEM_CODE AND e.ESTIMATE_CODE = ? ");
			

				con = dataSourceTransactionManager.getConnection();
				pstmt = con.prepareStatement(query.toString());
				pstmt.setString(1, estimateCode);

				rs = pstmt.executeQuery();
				while (rs.next()) {
					EstimateReportBean estimate = new EstimateReportBean();

					estimate.setItemCode(rs.getString("ITEM_CODE"));
					estimate.setItemName(rs.getString("ITEM_NAME"));
					estimate.setClientName(rs.getString("CLIENT_NAME"));
					estimate.setEstimateCode(rs.getString("ESTIMATE_CODE"));
					estimate.setEstimateDetailCode(rs.getString("ESTIMATE_DETAIL_CODE"));
					estimate.setEstimateDate(rs.getString("ESTIMATE_DATE"));
					estimate.setEstimateAmount(rs.getString("ESTIMATE_AMOUNT"));
					estimate.setEstimatePrice(rs.getString("ESTIMATE_DETAIL_UNIT_PRICE"));
					estimate.setEstimateSum(String.valueOf(rs.getString("ESTIMATE_SUM")));

					EstimateReportBeanList.add(estimate);
				}

				return EstimateReportBeanList;
			} catch (Exception sqle) {
						throw new DataAccessException(sqle.getMessage());
			} finally {
				dataSourceTransactionManager.close(pstmt,rs);
			}
		}
}



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
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;

public class ContractDAOImpl implements ContractDAO{

	private DataSourceTransactionManager dataSourceTransactionManager;


	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public int selectRowCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM CONTRACT");
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

	public List<ContractBean> selectContractList(int sr, int er) {
		
		List<ContractBean> contractList = new ArrayList<ContractBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM CONTRACT ");
			query.append(" WHERE ROWNUM BETWEEN ? AND ?  ");
			query.append(" ORDER BY CONTRACT_CODE  ");
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ContractBean contract = new ContractBean();
				contract.setContractCode(rs.getString("CONTRACT_CODE"));
				contract.setEstimateCode(rs.getString("ESTIMATE_CODE"));
				contract.setContractDate(rs.getString("CONTRACT_DATE"));
				contract.setProduceStatus(rs.getString("PRODUCE_STATUS"));
				
				
				

				contractList.add(contract);
			}
			
			return contractList;
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	@Override
	public void insertContract(ContractBean contractBean) throws DataAccessException {
	
		Connection con = null;
		PreparedStatement pstmt=null;

		StringBuffer query = new StringBuffer();
	
		query.append("INSERT INTO CONTRACT VALUES (? , ? , ? , ?, ?)");

		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, contractBean.getContractCode());
			pstmt.setString(2, contractBean.getEstimateCode());
			pstmt.setString(3, contractBean.getContractDate());
			pstmt.setString(4, contractBean.getProduceStatus());
			pstmt.setString(5, contractBean.getStandByStatus());

			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	public List<ContractItemBean> selectContractReviewList(String startDate, String endDate)
			throws DataAccessException {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ContractItemBean> contractItemList = new ArrayList<>();

		try {
			StringBuffer query = new StringBuffer();
/*			query.append(" SELECT C.*,E.CONTRACT_STATUS FROM CONTRACT C,ESTIMATE E");
			query.append(" WHERE CONTRACT_DATE BETWEEN ? AND ? AND C.ESTIMATE_CODE=E.ESTIMATE_CODE(+) ");
			query.append(" ORDER BY CONTRACT_CODE DESC ");
*/			query.append(" SELECT * FROM CONTRACT");
			query.append(" WHERE CONTRACT_DATE BETWEEN ? AND ? ORDER BY CONTRACT_CODE DESC ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ContractItemBean contractItemBean = new ContractItemBean();
				/*contractItemBean.setContractItemCode(rs.getString("CONTRACT_DETAIL_CODE"));*/
				contractItemBean.setContractCode(rs.getString("CONTRACT_CODE"));
				contractItemBean.setProduceStatus(rs.getString("PRODUCE_STATUS"));
				/*contractItemBean.setDeliveryScheduleDate(rs.getString("DELIVERY_SCHEDULE_DATE"));*/
				/*contractItemBean.setContractAmount(rs.getString("CONTRACT_QUANTITY"));
				contractItemBean.setMpsStatus(rs.getString("MPS_STATUS"));
				contractItemBean.setItemCode(rs.getString("ITEM_CODE"));
				contractItemBean.setItemName(rs.getString("ITEM_NAME"));*/
				/*contractItemBean.setUnit(rs.getString("UNIT"));*/
				contractItemBean.setContractDate(rs.getString("CONTRACT_DATE"));
				/*contractItemBean.setAccountCode(rs.getString("CLIENT_CODE"));*/
				contractItemBean.setEstimateCode(rs.getString("ESTIMATE_CODE"));
				contractItemBean.setStandByStatus(rs.getString("STANDBY_STATUS"));
				/*contractItemBean.setContractStatus(rs.getString("CONTRACT_STATUS"));*/
				contractItemList.add(contractItemBean);
			}
		
			return contractItemList;

		} catch (Exception e) {
			
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	   public void updateContract(ContractDetailBean contractDetailBean) {
	      // TODO Auto-generated method stub
	     
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      System.out.println(contractDetailBean.getContractCode());
	      StringBuffer query = new StringBuffer();
	      
	      query.append("UPDATE CONTRACT SET PRODUCE_STATUS ='Y' ");
	      query.append(" WHERE CONTRACT_CODE = ( SELECT cd.CONTRACT_CODE FROM CONTRACT c,CONTRACT_DETAIL cd  ");
	      query.append("  WHERE c.CONTRACT_CODE=cd.CONTRACT_CODE ");
	      query.append("  AND cd.CONTRACT_DETAIL_CODE= ?) ");
	      try {
	         con = dataSourceTransactionManager.getConnection();
	         pstmt = con.prepareStatement(query.toString());
	         pstmt.setString(1, contractDetailBean.getContractDetailCode());
	      

	         pstmt.executeUpdate();
	         
	      } catch (SQLException e) {
	         
	         e.printStackTrace();
	         throw new DataAccessException(e.getMessage());
	      } finally {
	         dataSourceTransactionManager.close(pstmt);
	      }

	      
	   }
	
}



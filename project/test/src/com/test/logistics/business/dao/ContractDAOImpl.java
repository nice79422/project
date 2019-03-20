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
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;

public class ContractDAOImpl implements ContractDAO{
	private static ContractDAO instance;
	private ContractDAOImpl(){}
	public static ContractDAO getInstance() {
		if(instance ==null)
			instance =new ContractDAOImpl();
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
			query.append("SELECT COUNT(*) FROM CONTRACT");
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

	public List<ContractBean> selectContractList(int sr, int er) {
		if (logger.isDebugEnabled()) { logger.debug("selectContractList(...)-시작");}
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
			if (logger.isDebugEnabled()) { logger.debug("selectContractList(...)-끝");}
			return contractList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectContractList(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	@Override
	public void insertContract(ContractBean contractBean) throws DataAccessException {
		if (logger.isDebugEnabled()) { logger.debug("insertContract(...)-시작");}
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

			if (logger.isDebugEnabled()) { logger.debug("insertContract(...)-끝");}
		} catch (SQLException e) {
			if (logger.isFatalEnabled()) { logger.fatal("insertContract(...)-에러");}
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	public List<ContractItemBean> selectContractReviewList(String startDate, String endDate)
			throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("END");
			}
			return contractItemList;

		} catch (Exception e) {
			logger.fatal(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	@Override
	   public void updateContract(ContractDetailBean contractDetailBean) {
	      // TODO Auto-generated method stub
	      if (logger.isDebugEnabled()) {
	         logger.debug("updateContract(...)-시작");
	      }
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
	         if (logger.isDebugEnabled()) {
	            logger.debug("updateContract(...)-끝");
	         }
	      } catch (SQLException e) {
	         if (logger.isFatalEnabled()) {
	            logger.fatal("updateContract(...)-에러");
	         }
	         e.printStackTrace();
	         throw new DataAccessException(e.getMessage());
	      } finally {
	         dataSourceTransactionManager.close(pstmt);
	      }

	      
	   }
	
}



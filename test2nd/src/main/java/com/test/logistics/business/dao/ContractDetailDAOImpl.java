package com.test.logistics.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.item.to.ItemBean;

public class ContractDetailDAOImpl implements ContractDetailDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;
	
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	
	
	public int selectRowCount() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM CONTRACT_DETAIL");
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
			query.append("SELECT COUNT(*) FROM CONTRACT_DETAIL WHERE CONTRACT_CODE=?");
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
	
	

	public int selectShippingRowCount(String code) {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM ORDER_LIST JOIN CONTRACT USING(CONTRACT_NO) WHERE CUSTOMER_NO=? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			rs.next();
	
			return rs.getInt(1);
		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public int selectOrderListRowCount(String mrpUse) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM ORDER_LIST WHERE MPS_USE=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, mrpUse);
			rs = pstmt.executeQuery();
			rs.next();

			return rs.getInt(1);
		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public List<ContractDetailBean> selectContractDetailLists(int sr, int er, String searchCode) {

		List<ContractDetailBean> contractDetailList = new ArrayList<ContractDetailBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT  * FROM CONTRACT_DETAIL a,ITEM b");
			query.append(" WHERE CONTRACT_CODE = ? ");
			query.append(" AND ROWNUM BETWEEN ? AND ? AND a.ITEM_CODE=b.ITEM_CODE");
			query.append(" ORDER BY CONTRACT_DETAIL_CODE ");


			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, searchCode);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ContractDetailBean contractDetail = new ContractDetailBean();
				contractDetail.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				contractDetail.setContractCode(rs.getString("CONTRACT_CODE"));
				contractDetail.setClientCode(rs.getString("CLIENT_CODE"));
				contractDetail.setContractQuantity(rs.getString("CONTRACT_QUANTITY"));
				contractDetail.setDeliveryScheduleDate(rs.getString("DELIVERY_SCHEDULE_DATE"));
				contractDetail.setItemBean(new ItemBean());
				contractDetail.getItemBean().setItemCode(rs.getString("ITEM_CODE"));
				/*contractDetail.getItemBean().setItemName(rs.getString("ITEM_NAME"));*/
				contractDetail.setMpsStatus(rs.getString("MPS_STATUS"));
				contractDetailList.add(contractDetail);
			}

			return contractDetailList;
		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	
	public List<ContractDetailBean> selectOrderList(int sr, int er, String mpsUse) {

		List<ContractDetailBean> orderLists = new ArrayList<ContractDetailBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM (SELECT ROWNUM AS rn, t.* FROM ");
			query.append("(SELECT * FROM ORDER_LIST WHERE MPS_USE=? ORDER BY DEMAND_DATE) t) tt ");
			query.append("WHERE tt.rn BETWEEN ? AND ?");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, mpsUse);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ContractDetailBean orderList = new ContractDetailBean();
				// orderList.setOrderListNo(rs.getString("ORDER_LIST_NO"));
				// orderList.setItemBean(new ItemBean());
				// orderList.getItemBean().setItemNo(rs.getString("ITEM_NO"));
				// orderList.setOrderAmount(rs.getString("ORDER_AMOUNT"));
				// orderList.setDemandDate(rs.getString("DEMAND_DATE"));
				// orderList.setMpsUse(rs.getString("MPS_USE"));

				orderLists.add(orderList);
			}

			return orderLists;
		} catch (Exception sqle) {

			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void insertContractDetail(ContractDetailBean contractDetailBean) throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();

		query.append(" INSERT INTO CONTRACT_DETAIL VALUES ( ?||'-'||lpad(CONTRACT_DETAIL_CODE_SEQ.NEXTVAL,5,'00000') , ? , ? , ? , ?, ?, ?) ");
		
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			 pstmt.setString(1, contractDetailBean.getContractCode());
			 pstmt.setString(2, contractDetailBean.getContractCode());
			 pstmt.setString(3, contractDetailBean.getClientCode());
			 pstmt.setString(4, contractDetailBean.getContractQuantity());
			 pstmt.setString(5, contractDetailBean.getDeliveryScheduleDate());
			 pstmt.setString(6, contractDetailBean.getItemCode());
			 pstmt.setString(7, contractDetailBean.getMpsStatus());

			pstmt.executeUpdate();
			

		} catch (SQLException e) {

			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateContractDetail(ContractDetailBean contractDetailBean) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append(" UPDATE CONTRACT_DETAIL SET MPS_STATUS='Y' ");
		query.append(" WHERE CONTRACT_DETAIL_CODE=? ");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			pstmt.setString(1, contractDetailBean.getContractDetailCode());

			pstmt.executeUpdate();

		} catch (Exception e) {

			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void deleteContractDetail(String code) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("DELETE FROM ORDER_LIST WHERE ORDER_LIST_NO = ?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
	
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public List<ContractDetailBean> selectShippingList(int sr, int er, String code) {

		List<ContractDetailBean> list = new ArrayList<ContractDetailBean>();
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ORDER_LIST_NO) rn, ");
			query.append("ORDER_LIST_NO, ITEM_NO, ITEM_NAME, ORDER_AMOUNT, DEMAND_DATE, STOCK_AMOUNT, ");
			query.append("c.CUSTOMER_NO FROM ORDER_LIST JOIN CONTRACT c USING(CONTRACT_NO) ");
			query.append(
					"JOIN ITEM USING(ITEM_NO) JOIN STOCK USING(ITEM_NO) WHERE c.CUSTOMER_NO=? AND SHIPPING_STATUS='N') ");
			query.append("WHERE rn BETWEEN ? AND ? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ContractDetailBean orderBean = new ContractDetailBean();
				// orderBean.setOrderListNo(rs.getString("ORDER_LIST_NO"));
				// orderBean.setItemNo(rs.getString("ITEM_NO"));
				// orderBean.setItemName(rs.getString("ITEM_NAME"));
				// orderBean.setOrderAmount(rs.getString("ORDER_AMOUNT"));
				// orderBean.setDemandDate(rs.getString("DEMAND_DATE"));
				// orderBean.setCustomerNo(rs.getString("CUSTOMER_NO"));
				// orderBean.setSotckAmount(rs.getString("STOCK_AMOUNT"));
				// orderBean.setStatus("");
				list.add(orderBean);
			}

			return list;
		} catch (Exception sqle) {
	
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateShipping(String orderListNo) {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ORDER_LIST SET SHIPPING_STATUS='Y' WHERE ORDER_LIST_NO=?");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, orderListNo);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	
}

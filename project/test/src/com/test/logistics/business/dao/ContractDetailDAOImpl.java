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
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.item.to.ItemBean;

public class ContractDetailDAOImpl implements ContractDetailDAO {
	protected final Log logger = LogFactory.getLog(getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	private static ContractDetailDAO instance;

	
	
	private ContractDetailDAOImpl() {
	}

	public static ContractDetailDAO getInstance() {
		if (instance == null)
			instance = new ContractDetailDAOImpl();

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
			query.append("SELECT COUNT(*) FROM CONTRACT_DETAIL");
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
			query.append("SELECT COUNT(*) FROM CONTRACT_DETAIL WHERE CONTRACT_CODE=?");
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
	
	

	public int selectShippingRowCount(String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectShippingRowCount-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("selectShippingRowCount-끝");
			}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectShippingRowCount-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public int selectOrderListRowCount(String mrpUse) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectOrderListRowCount(...)-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("selectOrderListRowCount(...)-끝");
			}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectOrderListRowCount(...)-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public List<ContractDetailBean> selectContractDetailLists(int sr, int er, String searchCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectContractDetailLists(...)-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("selectContractDetailLists(...)-끝");
			}
			return contractDetailList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectContractDetailLists(...)-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	
	public List<ContractDetailBean> selectOrderList(int sr, int er, String mpsUse) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectOrderList(...)-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("selectOrderList(...)-끝");
			}
			return orderLists;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectOrderList(...)-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void insertContractDetail(ContractDetailBean contractDetailBean) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("insertContractDetail(...)-시작");
		}
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
			
			if (logger.isDebugEnabled()) {
				logger.debug("insertContractDetail(...)-끝");
			}
		} catch (SQLException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("insertContractDetail(...)-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateContractDetail(ContractDetailBean contractDetailBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateOrderList(...)-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("updateOrderList(...)-끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("updateOrderList(...)-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void deleteContractDetail(String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteOrderList(...)-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("deleteOrderList(...)-끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("deleteOrderList(...)-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public List<ContractDetailBean> selectShippingList(int sr, int er, String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectShippingList(...)-시작");
		}
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
			if (logger.isDebugEnabled()) {
				logger.debug("selectShippingList(...)-끝");
			}
			return list;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectShippingList(...)-에러");
			}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateShipping(String orderListNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateShipping(...)-시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ORDER_LIST SET SHIPPING_STATUS='Y' WHERE ORDER_LIST_NO=?");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, orderListNo);
			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) {
				logger.debug("updateShipping(...)-끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("updateShipping(...)-에러");
			}
			e.printStackTrace();
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	
}

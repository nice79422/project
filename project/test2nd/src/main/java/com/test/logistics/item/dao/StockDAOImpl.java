package com.test.logistics.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.item.to.StockBean;

public class StockDAOImpl implements StockDAO {
	
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}
	


	@Override
	public int selectRowCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM STOCK");
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

	@Override
	public void insertStock(StockBean stock) throws DataAccessException {
		String[] data = null;
		String[] comp = null;
		String first = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			data =stock.getParentCode().split("-");
			comp =stock.getItemCode().split("-");
			if(data[1].equals("A0") && ( data[2].equals("01") || data[2].equals("02")) ){
				first = "SEOA";
			}else if(data[1].equals("A0") && data[2].equals("04")){
				first = "BUA";
			}else if( data[1].equals("B0") && ( data[2].equals("01") || data[2].equals("02") ) ){
				first = "SEOB";
			}else if( data[1].equals("B0") && ( data[2].equals("03") || data[2].equals("04") ) ){
				first = "SEOB";
			}else if( data[1].equals("B0") && ( data[2].equals("05") || data[2].equals("06") ) ){
				first = "BUB";
			}else if( data[1].equals("B0") && ( data[2].equals("07") || data[2].equals("08") ) ){
				first = "BUB";
			/*}else if(comp[2].equals("01")){
				first = "SEOC";
			}else if(comp[2].equals("02")){
				first = "BUC";*/
			}else{
				first = "SEOC";
			}
			
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO STOCK (STOCK_CODE,ITEM_CODE,WAREHOUSE_CODE ) VALUES ( ? || STOCK_SEQ.NEXTVAL, ? , ?) ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, first);
			pstmt.setString(2, stock.getItemCode());
			pstmt.setString(3, stock.getWarehouseCode());
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}

	}

	@Override
	public void deleteStock(StockBean stock) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("DELETE STOCK WHERE ITEM_NO=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, stock.getItemCode());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	@Override
	public List<StockBean> dateStockList(String start, String end) {
		List<StockBean> list = new ArrayList<StockBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * ");
			query.append(" FROM STOCK ");
			query.append(" WHERE OUTPUT_DATE BETWEEN ? AND ? ");
			query.append(" OR INPUT_DATE BETWEEN ? AND ?  ");
			query.append(" ORDER BY DECODE(ITEM_CODE,'A0',1 )  ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, start);
			pstmt.setString(2, end);
			pstmt.setString(3, start);
			pstmt.setString(4, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StockBean stock = new StockBean();
				stock.setStockCode(rs.getString("STOCK_CODE"));
				stock.setItemCode(rs.getString("ITEM_CODE"));
				stock.setInputAmount(rs.getString("INPUT_AMOUNT"));
				stock.setInputDate(rs.getString("INPUT_DATE"));
				stock.setInputExpecAmount(rs.getString("INPUT_EXPEC_AMOUNT"));
				stock.setInputExpecDate(rs.getString("INPUT_EXPEC_DATE"));
				stock.setOutputAmount(rs.getString("OUTPUT_AMOUNT"));
				stock.setOutputDate(rs.getString("OUTPUT_DATE"));
				stock.setOutputExpecAmount(rs.getString("OUTPUT_EXPEC_AMOUNT"));
				stock.setOutputExpecDate(rs.getString("OUTPUT_EXPEC_DATE"));
				stock.setStockAmount(rs.getString("STOCK_AMOUNT"));
				stock.setProduceStatus(rs.getString("PRODUCE_STATUS"));
				stock.setWarehouseCode(rs.getString("WAREHOUSE_CODE"));
				
				list.add(stock);
			}
			return list;
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	@Override
	public List<StockBean> itemStockList(String item) {
		List<StockBean> list = new ArrayList<StockBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * ");
			query.append(" FROM  STOCK ");
			query.append(" WHERE item_code = ? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, item);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				StockBean stock = new StockBean();
				
				stock.setStockCode(rs.getString("STOCK_CODE"));
				stock.setItemCode(rs.getString("ITEM_CODE"));
				stock.setInputAmount(rs.getString("INPUT_AMOUNT"));
				stock.setInputDate(rs.getString("INPUT_DATE"));
				stock.setInputExpecAmount(rs.getString("INPUT_EXPEC_AMOUNT"));
				stock.setInputExpecDate(rs.getString("INPUT_EXPEC_DATE"));
				stock.setOutputAmount(rs.getString("OUTPUT_AMOUNT"));
				stock.setOutputDate(rs.getString("OUTPUT_DATE"));
				stock.setOutputExpecAmount(rs.getString("OUTPUT_EXPEC_AMOUNT"));
				stock.setOutputExpecDate(rs.getString("OUTPUT_EXPEC_DATE"));
				stock.setStockAmount(rs.getString("STOCK_AMOUNT"));
				stock.setProduceStatus(rs.getString("PRODUCE_STATUS"));
				stock.setWarehouseCode(rs.getString("WAREHOUSE_CODE"));
				
				
				
				
				list.add(stock);
			}
			return list;
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	@Override
	public StockBean selectStockAmount(String code) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT STOCK_AMOUNT, NVL(OUTPUT_SCHEDULE_AMOUNT, 0) OUTPUT_SCHEDULE_AMOUNT ");
			query.append("FROM (SELECT STOCK_NO, ITEM_NO, STOCK_AMOUNT, OUTPUT_SCHEDULE_AMOUNT, ");
			query.append("MAX(STOCK_NO) over(PARTITION BY ITEM_NO) ms ");
			query.append("FROM STOCK) ");
			query.append("WHERE ITEM_NO=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			StockBean stockBean = null;
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stockBean = new StockBean();
				stockBean.setStockAmount(rs.getString("STOCK_AMOUNT"));
				// stockBean.setOutputScheduleAmount(rs.getString("OUTPUT_SCHEDULE_AMOUNT"));
			}
		
			return stockBean;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public void updateOutputScheduleStock(String supplyDate, String supplyAmount, String itemCode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
	
		query.append(" UPDATE STOCK SET OUTPUT_DATE=?, OUTPUT_AMOUNT=? ");
		query.append(" WHERE ITEM_CODE=? ");
	
		try {
			con = dataSourceTransactionManager.getConnection();
		
			pstmt = con.prepareStatement(query.toString());
	

			pstmt.setString(1, supplyDate);
			pstmt.setString(2, supplyAmount);
			pstmt.setString(3, itemCode);
		
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public void updateOutputStock(String outputDate, String outputAmount, String itemCode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE STOCK SET SHIPPING_AMOUNT=?, SHIPPING_DATE=? ");
		query.append("WHERE ITEM_CODE=?");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			pstmt.setString(1, outputAmount);
			pstmt.setString(2, outputDate);
			pstmt.setString(3, itemCode);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public void updateInputStock(String inDate, String inAmount, String itemNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE STOCK SET INPUT_AMOUNT=?, INOUT_DATE=? ");
		query.append("WHERE ITEM_NO=?");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			pstmt.setString(1, inAmount);
			pstmt.setString(2, inDate);
			pstmt.setString(3, itemNo);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public void updateStock(String stockAmount, String itemNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE STOCK SET STOCK_AMOUNT=?");
		query.append("WHERE ITEM_NO=?");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			pstmt.setString(1, stockAmount);
			pstmt.setString(2, itemNo);

			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	

}

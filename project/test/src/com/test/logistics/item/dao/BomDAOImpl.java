package com.test.logistics.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.item.to.BomBean;


public class BomDAOImpl implements BomDAO{
	protected final Log logger = LogFactory.getLog(getClass());
	private DataSourceTransactionManager dataSourceTransactionManager= DataSourceTransactionManager.getInstance();
	private static BomDAOImpl instance;


	public static BomDAOImpl getInstance() {
		if(instance == null)
			instance = new BomDAOImpl();
		return instance;
	}

	public List<BomBean> selectBom(String searchCode) {
		if (logger.isDebugEnabled()) { logger.debug("selectBom(...)-시작");}
		List<BomBean> bom = new ArrayList<BomBean>();
		String[] data = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			data = searchCode.split("&");

			if(data[1].equals("Y")){
			data[1] = "BT.ITEM_CODE=BT.PARENT_CODE";
			} else {
			data[1] = "BT.PARENT_CODE=BT.ITEM_CODE";
			}

			query.append(" SELECT LEVEL , BT.ITEM_CODE , BT.ITEM_NAME , BT.UNIT , BT.UNIT_PRICE , BT.LOSS_RATE ");
			query.append(" , BT.LEAD_TIME , BT.QUANTITY , BT.PARENT_CODE FROM ");
			query.append(" (SELECT i.ITEM_CODE ITEM_CODE , i.ITEM_NAME ITEM_NAME , i.UNIT UNIT ");
			query.append(" , i.UNIT_PRICE UNIT_PRICE , b.LOSS_RATE LOSS_RATE , b.LEAD_TIME LEAD_TIME ");
			query.append(" , b.QUANTITY QUANTITY  , b.PARENT_CODE PARENT_CODE FROM ITEM i , BOM b ");
			query.append(" WHERE i.ITEM_CODE=b.ITEM_CODE ) BT START WITH BT.ITEM_CODE LIKE ? CONNECT BY PRIOR "+data[1]  );
	
			con = dataSourceTransactionManager.getConnection();
		
			pstmt = con.prepareStatement(query.toString());
		
			pstmt.setString(1, data[0]);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BomBean bomBean = new BomBean();
				bomBean.setLevel(rs.getString("LEVEL"));
				bomBean.setItemCode(rs.getString("ITEM_CODE"));
				bomBean.setItemName(rs.getString("ITEM_NAME"));
				bomBean.setUnitPrice(rs.getString("UNIT_PRICE"));
				bomBean.setQuantity(rs.getString("QUANTITY"));
				bomBean.setLossRate(rs.getString("LOSS_RATE"));
				bomBean.setLeadTime(rs.getString("LEAD_TIME"));
				bomBean.setParentCode(rs.getString("PARENT_CODE"));
				
				bom.add(bomBean);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectBom(...)-끝");}
			return bom;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectBom(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	public List<BomBean> selectBomList() {
		if (logger.isDebugEnabled()) { logger.debug("selectBomList()-시작");}
		ArrayList<BomBean> bomLists = new ArrayList<BomBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM ITEM WHERE E_PRODUCT_STATUS like '%P'");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BomBean bom = new BomBean();
				bom.setItemCode(rs.getString("ITEM_NO"));
				bom.setItemName(rs.getString("ITEM_NAME"));

				bomLists.add(bom);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectBomList()-끝");}
			return bomLists;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectBomList()-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	  @Override
	    public void insertBom(BomBean bom) throws DataAccessException {
	    	if(logger.isDebugEnabled()){logger.debug(" insertBom() - 시작");}
	    	Connection con=null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	try{
	    		StringBuffer query=new StringBuffer();
	    		query.append(" INSERT INTO BOM VALUES ( 'BOM' ||'-'|| BOM_SEQ.NEXTVAL, ?, ?, ?, ?, ? ,'','') ");
	    		con = dataSourceTransactionManager.getConnection();
	    		pstmt = con.prepareStatement(query.toString());
	    		pstmt.setString(1, bom.getItemCode());
	    		pstmt.setString(2, bom.getParentCode());
	    		pstmt.setString(3, bom.getnQuantity() );
	    		pstmt.setString(4, bom.getLossRate() );
	    		pstmt.setString(5, bom.getLeadTime() );
	    		
	    		
	    		   rs = pstmt.executeQuery();
	    		   if(logger.isDebugEnabled()){logger.debug("insertBom() - 끝");}
	    	}catch(Exception error){
				if (logger.isFatalEnabled()) { logger.fatal("insertBom(...)-에러");}
	    		throw new DataAccessException(error.getMessage());
	    	}finally{
	    		dataSourceTransactionManager.close(pstmt, rs);
	    	}
	    }	
}

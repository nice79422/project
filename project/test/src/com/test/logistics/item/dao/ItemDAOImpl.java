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
import com.test.logistics.item.to.ItemBean;

public class ItemDAOImpl implements ItemDAO {
	 private static ItemDAOImpl instance;
	    private DataSourceTransactionManager dataSourceTransactionManager;
	    protected final Log logger;

	    private ItemDAOImpl(){
	    	dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	    	logger = LogFactory.getLog(getClass());
	    }

	    public static ItemDAOImpl getInstance(){
	    	if(instance==null) instance=new ItemDAOImpl();
	    	return instance;
	    }

	    public int selectRowCount(){
	    	if(logger.isDebugEnabled()){logger.debug("selectRowCount() - ����");}
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs=null;
	        try {
	            StringBuffer query = new StringBuffer();
	            query.append("SELECT COUNT(*) FROM ITEM");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            rs = pstmt.executeQuery();
	            rs.next();
	            if(logger.isDebugEnabled()){logger.debug("selectRowCount() - ��");}
	            return rs.getInt(1);
	        } catch(Exception sqle) {
				if (logger.isFatalEnabled()) { logger.fatal("selectRowCount(...)-����");}
	            throw new DataAccessException(sqle.getMessage());
	        } finally {
	        	dataSourceTransactionManager.close(pstmt, rs);
	        }
	    }


	    @Override
	    public List<ItemBean> selectItemList(String code) throws DataAccessException {
	    	if(logger.isDebugEnabled()){logger.debug("selectItemList() - ����");}
	    	List<ItemBean> itembean=new ArrayList<ItemBean>();
	    	Connection con=null;
	    	PreparedStatement pstmt =null;
	    	try{
	    		StringBuffer query= new StringBuffer();
	    		query.append("SELECT * FROM ITEM ");
	    		query.append("WHERE COM_PRODUCT_STATUS LIKE ? ");
	    		con = dataSourceTransactionManager.getConnection();
	            pstmt =con.prepareStatement(query.toString());
	            pstmt.setString(1, "%"+code+"%");
	            ResultSet rs= pstmt.executeQuery();
	            while(rs.next()){
	            	ItemBean item=new ItemBean();
	            	item.setItemCode(rs.getString("ITEM_CODE"));
	            	item.setItemName(rs.getString("ITEM_NAME"));
	            	item.setUnitPrice(rs.getString("UNIT_PRICE"));

	            	itembean.add(item);
	            }
	            if(logger.isDebugEnabled()){logger.debug("selectItemList() - ��");}
	            return itembean;
	    	}catch(Exception e){
				if (logger.isFatalEnabled()) { logger.fatal("selectItemList(...)-����");}
	    		throw new DataAccessException(e.getMessage());
	           }finally{
	        	   dataSourceTransactionManager.close(pstmt);
	                }
	    }

	    public List<ItemBean> selectItemList(int sr, int er, String searchWord)
	    		throws DataAccessException{

	    	if (logger.isDebugEnabled()) {
	    		logger.debug("selectEmpList() - ����");
	    	}
	    	List<ItemBean> itemBean = new ArrayList<ItemBean>();
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	try {
	    		
	    		String[] search =  searchWord.split("/");
	    		StringBuffer query = new StringBuffer();
	    		query.append(" SELECT * FROM ");
	    		query.append(
	    				" (SELECT ROW_NUMBER() OVER( ORDER BY DECODE(ITEM_CODE,'A0',1 ) ) RN, ITEM_CODE, ITEM_NAME, UNIT, UNIT_PRICE, COM_PRODUCT_STATUS,PURCHASING_PLACE_CODE   FROM ITEM ) ");
	    		query.append(" WHERE RN BETWEEN ? AND ? ");
	    		switch(search[0]){
	    		case "ITEM_CODE" : query.append(" AND ITEM_CODE LIKE ? "); break;
	    		case "ITEM_NAME" : query.append(" AND ITEM_NAME LIKE ? "); break;
	    		}
	    		con = dataSourceTransactionManager.getConnection();
	    		pstmt = con.prepareStatement(query.toString());
	    		pstmt.setInt(1, sr);
	    		pstmt.setInt(2, er);
	    		pstmt.setString(3, "%"+search[1]+"%");
	    		
	    		ResultSet rs = pstmt.executeQuery();
	    	
	    		while (rs.next()) {
	    			ItemBean item = new ItemBean();
	    			item.setItemCode(rs.getString("ITEM_CODE"));
	    			item.setItemName(rs.getString("ITEM_NAME"));
	    			item.setUnit(rs.getString("UNIT"));
	    			item.setUnitPrice(rs.getString("UNIT_PRICE"));
	    			item.setProductStatus(rs.getString("COM_PRODUCT_STATUS"));
	    			item.setPurchasingPlaceCode(rs.getString("PURCHASING_PLACE_CODE"));
	    			itemBean.add(item);
	    		}
	    		if (logger.isDebugEnabled()) {
	    			logger.debug("selectEmpList() - ��");
	    		}
	    	
	    		return itemBean;
	    	} catch (Exception e) {
	    		logger.fatal("selectEmpList()  - ����");
	    		throw new DataAccessException(e.getMessage());
	    	} finally {
	    		dataSourceTransactionManager.close(pstmt);
	    	}
	    }

	    public List<ItemBean> selectItemList(int sr, int er) throws DataAccessException{

	    	if (logger.isDebugEnabled()) {
	    		logger.debug("selectEmpList() - ����");
	    	}
	    	List<ItemBean> itemBean = new ArrayList<ItemBean>();
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	try {
	    		StringBuffer query = new StringBuffer();
	    		query.append(" SELECT * FROM ");
	    		query.append(" (SELECT ROW_NUMBER() OVER( ORDER BY ITEM_CODE ) RN, ITEM_CODE, ITEM_NAME, UNIT, UNIT_PRICE, COM_PRODUCT_STATUS,PURCHASING_PLACE_CODE  FROM ITEM) ");
	    		query.append(" WHERE RN BETWEEN ? AND ? ");
	    		con = dataSourceTransactionManager.getConnection();
	    		pstmt = con.prepareStatement(query.toString());
	    		pstmt.setInt(1, sr);
	    		pstmt.setInt(2, er);
	    		ResultSet rs = pstmt.executeQuery();
	    	
	    		while (rs.next()) {
	    			ItemBean item = new ItemBean();
	    			item.setItemCode(rs.getString("ITEM_CODE"));
	    			item.setItemName(rs.getString("ITEM_NAME"));
	    			item.setUnit(rs.getString("UNIT"));
	    			item.setUnitPrice(rs.getString("UNIT_PRICE"));
	    			item.setProductStatus(rs.getString("COM_PRODUCT_STATUS"));
	    			item.setPurchasingPlaceCode(rs.getString("PURCHASING_PLACE_CODE"));
	    			
	    			itemBean.add(item);
	    		}
	    		if (logger.isDebugEnabled()) {
	    			logger.debug("selectEmpList() - ��");
	    		}
	    
	    		return itemBean;
	    	} catch (Exception e) {
	    		logger.fatal("selectEmpList()  - ����");
	    		throw new DataAccessException(e.getMessage());
	    	} finally {
	    		dataSourceTransactionManager.close(pstmt);
	    	}
	    }
	    public List<ItemBean> selectItemList(int sr, int er, String itemCode, String itemName)
	    		throws DataAccessException{

	    	if (logger.isDebugEnabled()) {
	    		logger.debug("selectEmpList() - ����");
	    	}
	    	List<ItemBean> itemBean = new ArrayList<ItemBean>();
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	try {
	    		StringBuffer query = new StringBuffer();
	    		query.append(" SELECT * FROM ");
	    		query.append(
	    				" (SELECT ROW_NUMBER() OVER( ORDER BY ITEM_CODE ) RN, ITEM_CODE, ITEM_NAME, UNIT, UNIT_PRICE, COM_PRODUCT_STATUS,PURCHASING_PLACE_CODE  FROM ITEM ");
	    		query.append(" WHERE ITEM_CODE LIKE ? ");
	    		query.append(" AND ITEM_NAME LIKE ? ) ");
	    		query.append(" WHERE RN BETWEEN ? AND ?");
	    		con = dataSourceTransactionManager.getConnection();
	    		pstmt = con.prepareStatement(query.toString());
	    		pstmt.setString(1, "%"+itemCode+"%");
	    		pstmt.setString(2, "%"+itemName+"%");
	    		pstmt.setInt(3, sr);
	    		pstmt.setInt(4, er);
	    		ResultSet rs = pstmt.executeQuery();
	    	
	    		while (rs.next()) {
	    			ItemBean item = new ItemBean();
	    			item.setItemCode(rs.getString("ITEM_CODE"));
	    			item.setItemName(rs.getString("ITEM_NAME"));
	    			item.setUnit(rs.getString("UNIT"));
	    			item.setUnitPrice(rs.getString("UNIT_PRICE"));
	    			item.setProductStatus(rs.getString("COM_PRODUCT_STATUS"));
	    			item.setPurchasingPlaceCode(rs.getString("PURCHASING_PLACE_CODE"));
	    			itemBean.add(item);
	    		}
	    		if (logger.isDebugEnabled()) {
	    			logger.debug("selectEmpList() - ��");
	    		}
	    	
	    		return itemBean;
	    	} catch (Exception e) {
	    		logger.fatal("selectEmpList()  - ����");
	    		throw new DataAccessException(e.getMessage());
	    	} finally {
	    		dataSourceTransactionManager.close(pstmt);
	    	}
	    }

	    @Override
	    public void insertItem(ItemBean item) throws DataAccessException {
	    	String[] data = null;
	    	String code = null;
	    	if(logger.isDebugEnabled()){logger.debug(" insertItem() - ����");}
	    	Connection con=null;
	    	PreparedStatement pstmt = null;
	    	ResultSet rs = null;
	    	try{
	    		StringBuffer query=new StringBuffer();
	    		query.append(" INSERT INTO ITEM VALUES (?, ?, ?, ?, ?, ?) ");
	    		con = dataSourceTransactionManager.getConnection();
	    		pstmt = con.prepareStatement(query.toString());
	    		pstmt.setString(1, item.getItemCode());
	    		pstmt.setString(2, item.getItemName());
	    		pstmt.setString(3, item.getUnit());
	    		pstmt.setString(4, item.getUnitPrice());
	    		pstmt.setString(5, item.getProductStatus() );
	    		pstmt.setString(6, item.getPurchasingPlaceCode() );
	    		rs = pstmt.executeQuery();
	    		
	    		
	    		dataSourceTransactionManager.close(pstmt);
				con = dataSourceTransactionManager.getConnection();
				query.setLength(0);
				query.append(" INSERT INTO DETAIL_CODE (BASIC_CODE,DETAIL_CODE,DETAIL_CODE_NAME) VALUES ( 'LO-07',? , ?) ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.setString(1, item.getItemCode() );
				pstmt.setString(2, item.getItemName() );
				pstmt.executeQuery();
	    		
				
				
				data =item.getItemCode().split("-");
	    		if(data[1].equals("A0")){
	    			code = "LO-08";
	    		}else if(data[1].equals("B0")){
	    			code = "LO-09";
	    		}else{
	    			code = "LO-10";
	    		}
	    		
	    		dataSourceTransactionManager.close(pstmt);
				con = dataSourceTransactionManager.getConnection();
				query.setLength(0);
				query.append(" INSERT INTO DETAIL_CODE (BASIC_CODE,DETAIL_CODE,DETAIL_CODE_NAME) VALUES ( ? , ? , ?) ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.setString(1, code );
				pstmt.setString(2, item.getItemCode() );
				pstmt.setString(3, item.getItemName() );
				pstmt.executeQuery();
	    		  		
	    		
	    		
	    		if(logger.isDebugEnabled()){logger.debug("insertItem() - ��");}
	    	}catch(Exception error){
				if (logger.isFatalEnabled()) { logger.fatal("insertItem(...)-����");}
	    		throw new DataAccessException(error.getMessage());
	    	}finally{
	    		dataSourceTransactionManager.close(pstmt, rs);
	    	}
	    }

	    @Override
	    public void updateItem(ItemBean item){
	    	if(logger.isDebugEnabled()){logger.debug("updateItem() - ����");}
	    	Connection con = null;
	    	PreparedStatement pstmt = null;
	    	try{
	    		StringBuffer query=new StringBuffer();
	    		query.append("UPDATE ITEM SET ITEM_NAME=?, UNIT_PRICE=?, ");
	    		query.append("LEAD_TIME=?, LOSS_RATE=?, DIVISION_CODE_NAME=?, ");
	    		query.append("CATEGORY_CODE_NAME=?, PRODUCTION_LINE=?, SUPPLY=?, PLACEPURCH_NO=? ");
	    		query.append("WHERE ITEM_NO=?");
	    		con = dataSourceTransactionManager.getConnection();
	    		 pstmt = con.prepareStatement(query.toString());
	    		/* pstmt.setString(1, item.getItemName());
	    		 pstmt.setString(2, item.getUnitPrice());*/

	    		 pstmt.executeUpdate();
	    		 if(logger.isDebugEnabled()){logger.debug("updateItem() - ��");}
	    	}catch(Exception sqle){
				if (logger.isFatalEnabled()) { logger.fatal("updateItem(...)-����");}
	    		throw new DataAccessException(sqle.getMessage());
	    	}finally{
	    		dataSourceTransactionManager.close(pstmt);
	    	}
	    }

	    @Override
	    public void deleteItem(ItemBean item){
	    	if(logger.isDebugEnabled()){logger.debug("deleteItem() - ����");}
	    	Connection con=null;
	    	PreparedStatement pstmt = null;
	    	try{
	    		StringBuffer query=new StringBuffer();
	    		query.append("DELETE ITEM WHERE ITEM_NO=?");
	    		 con = dataSourceTransactionManager.getConnection();
	    		 pstmt = con.prepareStatement(query.toString());
	    		 pstmt.setString(1, item.getItemCode());
	    		 pstmt.executeUpdate();
	    		 if(logger.isDebugEnabled()){logger.debug("deleteItem() - ��");}
	    	}catch(Exception e){
				if (logger.isFatalEnabled()) { logger.fatal("deleteItem(...)-����");}
	    		throw new DataAccessException(e.getMessage());
	    	}finally{
	    		dataSourceTransactionManager.close(pstmt);
	    	}
	    }
}

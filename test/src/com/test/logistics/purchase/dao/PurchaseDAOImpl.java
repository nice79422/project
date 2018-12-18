package com.test.logistics.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.to.PurchaseBean;

public class PurchaseDAOImpl implements PurchaseDAO{
		private static PurchaseDAOImpl instance = new PurchaseDAOImpl();
	    private PurchaseDAOImpl(){}
	    public static PurchaseDAOImpl getInstance(){
	        if(instance==null) instance=new PurchaseDAOImpl();
	        return instance;
	    }
	    private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	    protected final Log logger = LogFactory.getLog(getClass());

	    public int selectRowCount(){
	    	if(logger.isDebugEnabled()){logger.debug("selectRowCount() - 시작");}
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs=null;
	        try {
	            StringBuffer query = new StringBuffer();
	            query.append("SELECT COUNT(*) FROM PURCHASE");	
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            rs = pstmt.executeQuery();
	            rs.next();
	            if(logger.isDebugEnabled()){logger.debug("selectRowCount() - 끝");}
	            return rs.getInt(1);
	        } catch(Exception sqle) {
				if (logger.isFatalEnabled()) { logger.fatal("selectRowCount()-에러");}
	            throw new DataAccessException(sqle.getMessage());
	        } finally {
	        	dataSourceTransactionManager.close(pstmt, rs);
	        }
	    }


		public void insertPurchase(MrpBean bean){
			if (logger.isDebugEnabled()) {logger.debug("insertPurchase() - start");}
			Connection con = null;
			PreparedStatement pstmt = null;
			StringBuffer query=new StringBuffer();
			query.append(" insert into purchase values(TO_CHAR(SYSDATE, 'YYYYMMDD')||'-'||LPAD(PURCHASE_CODE_SEQ.NEXTVAL, 5, '00000'), ? ,? , ? , ? , ?  ) ");
			try{
				con=dataSourceTransactionManager.getConnection();
				pstmt=con.prepareStatement(query.toString());
				
				pstmt.setString(1, bean.getMrpGatheringCode());
				pstmt.setString(2, bean.getItemBean().getPurchasingPlaceCode());
				pstmt.setString(3, bean.getItemCode());
				pstmt.setString(4, bean.getPurchaseDate());
				pstmt.setString(5, bean.getAmount());
				
				

				pstmt.executeUpdate();
				if (logger.isDebugEnabled()) {logger.debug("insertPurchase() - end");}
			}catch(Exception e){
				if (logger.isFatalEnabled()) {logger.fatal("DAO error !!!");}
				e.printStackTrace();
			}finally{
				dataSourceTransactionManager.close(pstmt);
			}
		}

	    @Override
	    public List<PurchaseBean> selectPurchaseList(int sr, int er) throws DataAccessException {
	    	System.out.println("sr:"+sr);
	    	System.out.println("er:"+er);
	    	if(logger.isDebugEnabled()){logger.debug("selectPurchaseList() - 시작");}
	        List<PurchaseBean> purchasebean=new ArrayList<PurchaseBean>();
	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {
	            StringBuffer query = new StringBuffer();
	            query.append(" SELECT * FROM ");
	            query.append(" (SELECT ROW_NUMBER() OVER(ORDER BY PURCHASE_CODE) rn, ");
	            query.append(" PURCHASE_CODE,MRP_GATHERING_CODE,PURCHASING_PLACE_CODE,ITEM_CODE,PURCHASE_DATE ,PURCHASE_AMOUNT FROM PURCHASE) ");
	            query.append(" WHERE rn BETWEEN ? AND ? ");
	            con = dataSourceTransactionManager.getConnection();
	             pstmt = con.prepareStatement(query.toString());
	             pstmt.setInt(1, sr);
	             pstmt.setInt(2, er);
	             ResultSet rs = pstmt.executeQuery();
	          
	             while(rs.next()){
	                 PurchaseBean purchase=new PurchaseBean();
	                 purchase.setPurchaseCode(rs.getString("PURCHASE_CODE"));
	                 purchase.setMrpGatheringCode(rs.getString("MRP_GATHERING_CODE"));
	                 purchase.setPurchasingPlaceCode(rs.getString("PURCHASING_PLACE_CODE"));
	                 purchase.setItemCode(rs.getString("ITEM_CODE"));
	                 purchase.setPurchaseDate(rs.getString("PURCHASE_DATE"));
	                 purchase.setPurchaseAmount(rs.getString("PURCHASE_AMOUNT"));
	                 purchasebean.add(purchase);
	             }
	             if(logger.isDebugEnabled()){logger.debug("selectPurchaseList() - 끝");}
	             return purchasebean;
	        }catch(Exception e){
				if (logger.isFatalEnabled()) { logger.fatal("selectPurchaseList()-에러");}
	            throw new DataAccessException(e.getMessage());
	        }finally{
	        	dataSourceTransactionManager.close(pstmt);
	        	}

	        }


	    @Override
	    public void updatePurchase(PurchaseBean purchaseBean) throws DataAccessException {
	      if(logger.isDebugEnabled()){logger.debug("updatePurchase() - 시작");}
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs=null;
	      try{
	          StringBuffer query=new StringBuffer();
	          query.append("UPDATE PURCHASE SET VENDOR_CODE=?, PURCHASE_PRICE=?");
	          query.append("WHERE PURCHASE_CODE=?");
	          con = dataSourceTransactionManager.getConnection();
	          pstmt = con.prepareStatement(query.toString());
	       

	          	pstmt.setString(1, purchaseBean.getClientCode());
	          	pstmt.setString(2, purchaseBean.getPurchaseFixDate());
	          	pstmt.setString(3, purchaseBean.getPurchaseCode());

	          pstmt.executeUpdate();
	          if(logger.isDebugEnabled()){logger.debug("updatePurchase() - 끝");}
	      }catch(Exception error){
				if (logger.isFatalEnabled()) { logger.fatal("updatePurchase()-에러");}
	          throw new DataAccessException(error.getMessage());
	      }finally{
	    	  dataSourceTransactionManager.close(pstmt, rs);
	      }
	    }

	}
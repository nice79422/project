package com.test.logistics.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.item.to.BomBean;


public class BomDAOImpl implements BomDAO{
   private DataSourceTransactionManager dataSourceTransactionManager;
   
   public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
      this.dataSourceTransactionManager = dataSourceTransactionManager;
   }


   public List<BomBean> selectBom(String searchCode) {
      System.out.println("BOM ?ค์?ด๊??? searchCode ??" +searchCode);
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

         query.append(" SELECT  (LPAD ('  ', (LEVEL)*5) || BT.ITEM_CODE) GRADE, BT.ITEM_CODE , BT.ITEM_NAME , BT.UNIT , BT.UNIT_PRICE , BT.LOSS_RATE ");
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
            bomBean.setLevel(rs.getString("GRADE"));
            bomBean.setItemCode(rs.getString("ITEM_CODE"));
            bomBean.setItemName(rs.getString("ITEM_NAME"));
            bomBean.setUnitPrice(rs.getString("UNIT_PRICE"));
            bomBean.setQuantity(rs.getString("QUANTITY"));
            bomBean.setLossRate(rs.getString("LOSS_RATE"));
            bomBean.setLeadTime(rs.getString("LEAD_TIME"));
            bomBean.setParentCode(rs.getString("PARENT_CODE"));
            
            bom.add(bomBean);
         }
         return bom;
      } catch (Exception sqle) {
         throw new DataAccessException(sqle.getMessage());
      } finally {
         dataSourceTransactionManager.close(pstmt,rs);
      }
   }

   public List<BomBean> selectBomList() {
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
         
         return bomLists;
      } catch (Exception sqle) {
         
         throw new DataAccessException(sqle.getMessage());
      } finally {
         dataSourceTransactionManager.close(pstmt,rs);
      }
   }
   
	@Override
	public void insertBom(BomBean bom) throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO BOM VALUES ( 'BOM' ||'-'|| BOM_SEQ.NEXTVAL, ?, ?, ?, ?, ? ,'','') ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, bom.getItemCode());
			pstmt.setString(2, bom.getParentCode());
			pstmt.setString(3, bom.getnQuantity());
			pstmt.setString(4, bom.getLossRate());
			pstmt.setString(5, bom.getLeadTime());

			rs = pstmt.executeQuery();

		} catch (Exception error) {

			throw new DataAccessException(error.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
}
       

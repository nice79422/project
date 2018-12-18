package com.test.logistics.product.dao;

import java.sql.CallableStatement;
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
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

import oracle.jdbc.OracleTypes;

public class MrpDAOImpl implements MrpDAO {
	protected final Log logger = LogFactory.getLog(getClass());
	private DataSourceTransactionManager dataSourceTransactionManager;
	private static MrpDAO instance ;
	private MrpDAOImpl() {
		 dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	}
	public static MrpDAO getInstance() {
		if(instance == null)
			instance = new MrpDAOImpl();
		return instance;
	}

	public List<MrpBean> selectMrpOpenList(String workplaceCode, String startDate, String endDate) {
		if (logger.isDebugEnabled()) { logger.debug("mrpOpenOut(...)-시작");}
		List<MrpBean> mrpOpenOutList = new ArrayList<MrpBean>();
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" {call P_MRP_OPEN(?,?,?,?,?,?)} ");
			
		
			con = dataSourceTransactionManager.getConnection();
			cs = con.prepareCall(query.toString());
			cs.setString(1,workplaceCode);
			cs.setString(2,startDate);
			cs.setString(3,endDate);
			cs.registerOutParameter(4,OracleTypes.NUMBER);
			cs.registerOutParameter(5,OracleTypes.VARCHAR);
			cs.registerOutParameter(6,OracleTypes.CURSOR);
			cs.executeUpdate();
			rs = (ResultSet) cs.getObject(6);
			while (rs.next()) {
				MrpBean mrpBean = new MrpBean();
				mrpBean.setLevel(rs.getString("LEV"));
				/*mrpBean.setMrpCode(rs.getString("MRP_CODE"));*/
				mrpBean.setItemCode(rs.getString("ITEM_CODE"));
				mrpBean.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mrpBean.setPurchaseDate(rs.getString("PLAN_DATE"));
				mrpBean.setBomBean(new BomBean());
				mrpBean.getBomBean().setParentCode(rs.getString("PARENT_CODE"));
				mrpBean.setWorkspaceCode(rs.getString("WORKPLACE_CODE"));
				mrpBean.setPurchaseQuantity(rs.getString("ORDER_QUANTITY"));
				mrpBean.setItemBean(new ItemBean());
				mrpBean.getItemBean().setItemName(rs.getString("ITEM_NAME"));
				mrpBean.setLossRate(rs.getString("LOSS_RATE"));
				mrpOpenOutList.add(mrpBean);
				
			}
			if (logger.isDebugEnabled()) { logger.debug("mrpOpenOut(...)-끝");}
			return mrpOpenOutList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("mrpOpenOut(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			
			dataSourceTransactionManager.close(cs);
		}
	}
	public List<MrpBean> selectMrpCodeList(MrpBean mrpCode) throws DataAccessException {
		if (logger.isDebugEnabled()) {logger.debug("selectMrpCodeList - start");}
		ArrayList<MrpBean> list=new ArrayList<MrpBean>();
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT mrp_code FROM mrp ");
			query.append(" WHERE mps_code= ? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, mrpCode.getMpsCode());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				MrpBean mrpBean=new MrpBean();
				mrpBean.setMrpCode(rs.getString("MRP_CODE"));
				list.add(mrpBean);
			
			}
			if (logger.isDebugEnabled()) {logger.debug("selectMrpCodeList - end");}
			return list;
			}
			catch(Exception sqle) {if (logger.isFatalEnabled()) {logger.fatal("DAO error");}throw new DataAccessException(sqle.getMessage());} 
			finally {dataSourceTransactionManager.close(pstmt);}
			}
	
	public int selectMrpTotalCount() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT COUNT(*) FROM (SELECT ITEM_CODE FROM MRP GROUP BY ITEM_CODE) ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
			int mrpTotalCount = rs.getInt(1);
			if (logger.isDebugEnabled()) {
				logger.debug("END");
			}
			return mrpTotalCount;
		} catch (Exception e) {
			logger.fatal(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	public int selectMrpTotalReviewCount() {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT COUNT(*) FROM MRP_GATHERING ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
			int mrpTotalReviewCount = rs.getInt(1);
			if (logger.isDebugEnabled()) {
				logger.debug("END");
			}
			return mrpTotalReviewCount;
		} catch (Exception e) {
			logger.fatal(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}
	
	@Override
	public int selectMrpListRowCount(String lineNo) {
		if (logger.isDebugEnabled()) { logger.debug("selectMrpListRowCount(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuffer query = new StringBuffer();
			query.append("SELECT count(*) from ");
			query.append("(SELECT LEVEL lv, b.ITEM_CODE, b.PARENT_CODE, b.QUANTITY, t.LOSS_RATE, t.LEAD_TIME , t.CONTRACT_DETAIL_CODE, ");
			query.append("t.PLAN_DATE, t.PLAN_QUANTITY, t.MRP_STATUS, t.UNIT_PRICE FROM  ");
			query.append("(SELECT m.CONTRACT_DETAIL_CODE, m.PLAN_DATE, m.PLAN_QUANTITY, ci.ITEM_CODE, i.UNIT_PRICE, i.LEAD_TIME, i.LOSS_RATE, ");
			query.append("ci.SUPPLY_DATE, m.MRP_STATUS FROM MPS m, CONTRACT_ITEM ci, ITEM i ");
			query.append("WHERE m.CONTRACT_DETAIL_CODE = ci.CONTRACT_DETAIL_CODE AND ci.ITEM_CODE = i.ITEM_CODE ");
			query.append("AND ci.MPS_STATUS = 'Y' AND m.MRP_STATUS = 'N' AND m.WORKPLACE_CODE=?) t, BOM b ");
			query.append("WHERE b.ITEM_CODE = t.ITEM_CODE(+) START WITH b.ITEM_CODE LIKE 'IT-FP%' ");
			query.append("CONNECT BY PRIOR b.ITEM_CODE = b.PARENT_CODE) ");

			con = dataSourceTransactionManager.getConnection();
		
			pstmt = con.prepareStatement(query.toString());
		
			pstmt.setString(1, lineNo);

			rs = pstmt.executeQuery();
		
			rs.next();
			if (logger.isDebugEnabled()) { logger.debug("selectMrpListRowCount(...)-끝");}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMrpListRowCount(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
  
	public int selectTotalMrpListRowCount(String supply) {
		if (logger.isDebugEnabled()) { logger.debug("selectTotalMrpListRowCount(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT count(*) FROM MRP_OPEN_TEMP ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
			int num=rs.getInt(1);
			
			if (logger.isDebugEnabled()) { logger.debug("selectTotalMrpListRowCount(...)-끝");}
			return num;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectTotalMrpListRowCount(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	public List<MrpBean> selectTotalMrp(int sr,int er,String supply){
		if (logger.isDebugEnabled()) { logger.debug("selectTotalMrp(...)-시작");}
		List<MrpBean> totalMrpList = new ArrayList<MrpBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT rownum, i.item_code , i.item_name , i.unit_price , i.unit_price * a.sum_quantity AS total_price  ");
			query.append(" , a.sum_quantity , a.plan_date FROM (SELECT item.ITEM_CODE , item.ITEM_NAME , SUM(ORDER_QUANTITY) sum_quantity ");
			query.append(" , max(plan_date) plan_date FROM MRP_open_temp mrp , item WHERE item.item_code= mrp.ITEM_CODE AND mrp.ORDER_QUANTITY<>'0' ");
			query.append(" GROUP BY ITEM.item_code, item.ITEM_NAME ) a , ITEM i WHERE a.item_code=i.item_code AND ROWNUM BETWEEN ? AND ? ");
			
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MrpBean mrpBean = new MrpBean();
				mrpBean.setItemCode(rs.getString("ITEM_CODE"));
				mrpBean.setItemBean(new ItemBean());
				mrpBean.getItemBean().setItemName(rs.getString("ITEM_NAME"));
				mrpBean.getItemBean().setUnitPrice(rs.getString("UNIT_PRICE"));
				mrpBean.setPurchaseQuantity(rs.getString("SUM_QUANTITY"));
				mrpBean.setTotalPrice(rs.getString("TOTAL_PRICE"));
				mrpBean.setPurchaseDate(rs.getString("PLAN_DATE"));
				/*mrpBean.setMpsCode(rs.getString("MPS_CODE"));*/
				
				

				totalMrpList.add(mrpBean);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectTotalMrp(...)-끝");}
			return totalMrpList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectTotalMrp(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	public void insertMrpGathering(MrpBean mrpGatheringBean) throws DataAccessException {
		if (logger.isDebugEnabled()) { logger.debug("insertMrpGathering(...)-시작");}
		Connection con = null;
		PreparedStatement pstmt=null;
		StringBuffer query = new StringBuffer();
		try {
			con = dataSourceTransactionManager.getConnection();

			query.append(" INSERT INTO MRP_GATHERING VALUES ( TO_CHAR(SYSDATE,'yyyymmdd')||'-'||LPAD(MRP_CODE_SEQ.NEXTVAL, 5,'00000'), ?, 'N','N', ? ,?) ");
			
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, mrpGatheringBean.getItemCode());
			pstmt.setString(2, mrpGatheringBean.getPurchaseQuantity());
			pstmt.setString(3, mrpGatheringBean.getPurchaseDate());
			

			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) { logger.debug("insertMrpGathering(...)-끝");}
		} catch (SQLException e) {
			if (logger.isFatalEnabled()) { logger.fatal("insertMrpGathering(...)-에러");}
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	

	public void updateMrp(MrpBean bean){
		if (logger.isDebugEnabled()) {logger.debug("updateMrp() - strat");}
		PreparedStatement pstmt=null;
		Connection con=null;
		StringBuffer query = new StringBuffer();
		query.append("UPDATE MRP_GATHERING SET PURCHASE_STATUS = 'Y' WHERE MRP_GATHERING_CODE=?");
		try {
			con=dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, bean.getMrpGatheringCode());
			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) {logger.debug("updateMrp() - end");}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {logger.fatal("updateMrp() error");}
			e.printStackTrace();
		}finally {
				dataSourceTransactionManager.close(pstmt);
		}
	}

	

	public int selectPurchaseOrderRowCount(String code){
		if (logger.isDebugEnabled()) {logger.debug("selectPurchaseOrderRowCount() - start");}
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT COUNT(*) FROM MRP m, ITEM i WHERE m.ITEM_CODE = i.ITEM_CODE ");
			query.append(" and i.PURCHASING_PLACE_CODE = ?  AND purchase_status = 'N' ");
			
		
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) {logger.debug("selectPurchaseOrderRowCount() - end");}

			return rs.getInt(1);
		} catch(Exception sqle) {
			if (logger.isFatalEnabled()) {logger.fatal("DAO error");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public List<MrpBean> selectPurchaseOrder(int sr, int er, String code){
		if (logger.isDebugEnabled()) {logger.debug("selectPurchaseOrder() - start");}
		ArrayList<MrpBean> list=new ArrayList<MrpBean>();
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM (SELECT row_number() over(ORDER BY MRP_GATHERING_CODE desc) rn, m.MRP_GATHERING_CODE ");
			query.append(" , m.AMOUNT , m.PURCHASE_DATE, i.ITEM_CODE, i.UNIT_PRICE, i.PURCHASING_PLACE_CODE, i.ITEM_NAME ");
			query.append(" FROM MRP_gathering m, ITEM i WHERE purchase_status = 'N' AND m.ITEM_CODE = i.ITEM_CODE ");
			query.append(" AND i.PURCHASING_PLACE_CODE= ? ) WHERE rn BETWEEN ? AND ? ");
		


			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				MrpBean mrpBean=new MrpBean();
				mrpBean.setMrpGatheringCode(rs.getString("MRP_GATHERING_CODE"));
				mrpBean.setItemCode(rs.getString("ITEM_CODE"));
				mrpBean.setItemName(rs.getString("ITEM_NAME"));
				mrpBean.setPurchaseDate(rs.getString("PURCHASE_DATE"));
				mrpBean.setAmount(rs.getString("AMOUNT"));
				mrpBean.setItemBean(new ItemBean());
				mrpBean.getItemBean().setUnitPrice(rs.getString("UNIT_PRICE"));
				mrpBean.getItemBean().setPurchasingPlaceCode(rs.getString("PURCHASING_PLACE_CODE"));
				

				list.add(mrpBean);
			}
			if (logger.isDebugEnabled()) {logger.debug("selectPurchaseOrder() - end");}
			return list;
		} catch(Exception sqle) {
			if (logger.isFatalEnabled()) {logger.fatal("DAO error");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	public List<MrpTotalBean> selectMrpTotalReviewList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MrpTotalBean> mrpTotalReviewList = new ArrayList<>();

		try {
			StringBuffer query = new StringBuffer();
			query.append("  SELECT A.*,B.ITEM_NAME,B.UNIT ");
			query.append(" FROM (SELECT MRP_GATHERING.*,ROWNUM NUM FROM MRP_GATHERING) A, ITEM B ");
			query.append(" WHERE A.ITEM_CODE = B.ITEM_CODE AND NUM BETWEEN ? AND ? ");
			query.append(" ORDER BY A.PURCHASE_DATE DESC ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MrpTotalBean mrpTotalBean = new MrpTotalBean();
				mrpTotalBean.setMrpGatheringCode(rs.getString("MRP_GATHERING_CODE"));
				mrpTotalBean.setItemCode(rs.getString("ITEM_CODE"));
				mrpTotalBean.setItemName(rs.getString("ITEM_NAME"));
				mrpTotalBean.setUnit(rs.getString("UNIT"));
				mrpTotalBean.setPurchaseDate(rs.getString("PURCHASE_DATE"));
				mrpTotalBean.setAmount(rs.getString("AMOUNT"));
				/*mrpTotalBean.setClientCode(rs.getString("CLIENT_CODE"));*/
				mrpTotalBean.setPurchaseStatus(rs.getString("PURCHASE_STATUS"));
				mrpTotalReviewList.add(mrpTotalBean);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("END");
			}

			return mrpTotalReviewList;

		} catch (Exception e) {
			logger.fatal(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}

		
	}
	@Override
	public List<MrpBean> dateMrp(String startDate, String endDate) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) { logger.debug("dateMrp(...)-시작");}
		List<MrpBean> dateMrpList = new ArrayList<MrpBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM MRP WHERE purchase_date BETWEEN ? AND ? ");
						
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MrpBean mrpBean = new MrpBean();
				mrpBean.setMrpCode(rs.getString("MRP_CODE"));
				mrpBean.setMpsCode(rs.getString("MPS_CODE"));
				mrpBean.setLevel(rs.getString("LEV"));
				mrpBean.setItemCode(rs.getString("ITEM_CODE"));
				mrpBean.setItemName(rs.getString("ITEM_NAME"));
				mrpBean.setAmount(rs.getString("PURCHASE_AMOUNT"));
/*				mrpBean.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));*/
				mrpBean.setPurchaseDate(rs.getString("PURCHASE_DATE"));
				/*mrpBean.setBomBean(new BomBean());
				mrpBean.getBomBean().setParentCode(rs.getString("PARENT_CODE"));
				mrpBean.setWorkspaceCode(rs.getString("WORKPLACE_CODE"));
				mrpBean.setPurchaseQuantity(rs.getString("ORDER_QUANTITY"));*/
				mrpBean.setPurchaseStatus(rs.getString("PURCHASE_STATUS"));
				/*mrpBean.setItemBean(new ItemBean());
				mrpBean.getItemBean().setItemName(rs.getString("ITEM_NAME"));*/
				dateMrpList.add(mrpBean);
				
			}
			if (logger.isDebugEnabled()) { logger.debug("mrpOpenOut(...)-끝");}
			return dateMrpList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("mrpOpenOut(...)-에러");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			
			dataSourceTransactionManager.close(pstmt);
		}
}
}

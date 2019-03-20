package com.test.logistics.product.dao;

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
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;
import com.test.logistics.product.to.MpsBean;

public class MpsDAOImpl implements MpsDAO{
	protected final Log logger;
	private DataSourceTransactionManager dataSourceTransactionManager ;
	private static MpsDAO instance ;
	private MpsDAOImpl() {
		logger = LogFactory.getLog(getClass());
		dataSourceTransactionManager = DataSourceTransactionManager.getInstance();
	}
	public static MpsDAO getInstance() {
		if(instance ==null)
			instance= new MpsDAOImpl();
		return instance;
	}

	public int selectProductRowCount(String code){
		if (logger.isDebugEnabled()) {logger.debug("selectProductRowCount(...)-����");}
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM MPS WHERE LINE_NO=? AND COMPLETION_OX='N'");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) {logger.debug("selectProductRowCount(...)-��");}
			return rs.getInt(1);
		} catch(Exception sqle) {
			if (logger.isFatalEnabled()) {logger.fatal("selectProductRowCount(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public int selectMpsListRowCount(String mpsStatus) {
		if (logger.isDebugEnabled()) { logger.debug("selectMpsListRowCount(...)-����");}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			StringBuffer query = new StringBuffer();
			query.append(" SELECT COUNT(*) FROM CONTRACT_DETAIL WHERE MPS_STATUS = ? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1,mpsStatus);
			rs = pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) { logger.debug("selectMpsListRowCount(...)-��");}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMpsListRowCount(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	public int selectMpsListRowCount(String mpsStatus, String mrpStatus, String lineNo) {
		if (logger.isDebugEnabled()) { logger.debug("selectMpsListRowCount(...)-����");}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuffer query = new StringBuffer();
			query.append("  SELECT COUNT(*) FROM MPS m, CONTRACT_DETAIL ci ");
			query.append("  WHERE m.CONTRACT_DETAIL_CODE = ci.CONTRACT_DETAIL_CODE ");
			query.append("  AND m.MRP_STATUS=? ");
			query.append("  AND ci.MPS_STATUS=? ");
			query.append("  AND M.WORKPLACE_CODE=? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1,mrpStatus);
			pstmt.setString(2,mpsStatus);
			pstmt.setString(3,lineNo);
			rs = pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) { logger.debug("selectMpsListRowCount(...)-��");}
			return rs.getInt(1);
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMpsListRowCount(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	public List<MpsBean> selectProductList(int sr, int er, String code){
		if (logger.isDebugEnabled()) {logger.debug("selectProductList(...)-����");}
		List<MpsBean> list=new ArrayList<MpsBean>();
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ORDER_LIST_NO) rn, ");
			query.append("ORDER_LIST_NO, ITEM_NO, ITEM_NAME, PLANNED_DATE, PLANNED_AMOUNT ");
			query.append("FROM MPS JOIN ORDER_LIST USING(ORDER_LIST_NO) JOIN ITEM USING(ITEM_NO) ");
			query.append("WHERE LINE_NO=? AND COMPLETION_OX='N') ");
			query.append("WHERE rn BETWEEN ? AND ? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				MpsBean mpsBean=new MpsBean();

				list.add(mpsBean);
			}
			if (logger.isDebugEnabled()) {logger.debug("selectProductList(...)-��");}
			return list;
		} catch(Exception sqle) {
			if (logger.isFatalEnabled()) {logger.fatal("selectProductList(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public List<MpsBean> selectMpsList(int sr, int er, String mpsStatus) {
		if (logger.isDebugEnabled()) { logger.debug("selectMpsList(...)-����");}
		List<MpsBean> mpsList = new ArrayList<MpsBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
	
			/*			SELECT t.CONTRACT_DETAIL_CODE  --���ֻ󼼹�ȣ
		     ,t.iitem AS item_code     --ǰ���ڵ�
		     ,t.LOSS_RATE              --�սǷ�
		     ,t.LEAD_TIME              --���۱Ⱓ  
		     ,t.DELIVERY_SCHEDULE_DATE --��޸�����
		     ,t.CONTRACT_QUANTITY      --�ֹ���
		     ,t.STOCK_AMOUNT           --���
		     ,t.CONTRACT_QUANTITY AS PLAN_QUANTITY  --�ֹ��� 
		     ,t.OUTPUT_EXPEC_AMOUNT    -- ���������
		     ,t.STOCK_CODE --����ڵ�
		     ,t.DELIVERY_SCHEDULE_DATE AS PLAN_DATE --��޸�����
		     ,t.MPS_STATUS  -- MPS����
		  FROM 
		       (SELECT ROW_NUMBER() OVER(ORDER BY it.CONTRACT_DETAIL_CODE) rn
		            , it.*
		            ,s.STOCK_AMOUNT --���
		            ,s.OUTPUT_EXPEC_AMOUNT -- ��������� 
		            ,s.STOCK_CODE -- ����ڵ�
		            ,s.ITEM_CODE  --ǰ���ڵ�
		         FROM 
		              (SELECT ci.*
		                   , i.ITEM_CODE AS iitem  --�������ڵ�
		                   , i.LEAD_TIME           --���۱Ⱓ
		                   , i.LOSS_RATE           --�սǷ�
		                FROM 
		                     (SELECT CONTRACT_DETAIL.* 
		                       FROM CONTRACT_DETAIL 
		                      WHERE MPS_STATUS = 'N' 
		                     ) ci 
		                   , 
		                     (SELECT ITEM_CODE
		                          ,LOSS_RATE
		                          ,LEAD_TIME 
		                       FROM bom
		                     ) i 
		               WHERE ci.ITEM_CODE=i.ITEM_CODE
		              ) it
		            , STOCK s 
		        WHERE it.iitem = s.item_code
		       ) t 
		 WHERE t.rn BETWEEN 1 AND 20  
		 */	
			query.append(" SELECT t.CONTRACT_DETAIL_CODE,t.iitem AS item_code,t.LOSS_RATE,t.LEAD_TIME,t.DELIVERY_SCHEDULE_DATE ,t.CONTRACT_QUANTITY ");
			query.append(" ,t.STOCK_AMOUNT ,t.CONTRACT_QUANTITY AS PLAN_QUANTITY ,t.OUTPUT_EXPEC_AMOUNT ,t.STOCK_CODE,t.DELIVERY_SCHEDULE_DATE AS PLAN_DATE ");
			query.append(" ,t.MPS_STATUS FROM (SELECT ROW_NUMBER() OVER(ORDER BY it.CONTRACT_DETAIL_CODE) rn, it.*,s.STOCK_AMOUNT ,s.OUTPUT_EXPEC_AMOUNT ");
			query.append(" ,s.STOCK_CODE,s.ITEM_CODE FROM (SELECT ci.*, i.ITEM_CODE AS iitem , i.LEAD_TIME, i.LOSS_RATE FROM	 ");
			query.append(" (SELECT CONTRACT_DETAIL.* FROM CONTRACT_DETAIL WHERE MPS_STATUS = ? ) ci , (SELECT ITEM_CODE,LOSS_RATE,LEAD_TIME ");
			query.append(" FROM bom) i WHERE ci.ITEM_CODE=i.ITEM_CODE) it, STOCK s WHERE it.iitem = s.item_code) t WHERE t.rn BETWEEN ? AND ? ");
			
			
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, mpsStatus);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MpsBean mpsBean = new MpsBean();
				mpsBean.setPlanQuantity(rs.getString("PLAN_QUANTITY"));
				mpsBean.setPlanDate(rs.getString("PLAN_DATE"));
				mpsBean.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.setItemCode(rs.getString("ITEM_CODE"));
				
				
				mpsBean.setItemBean(new ItemBean());
				mpsBean.getItemBean().setItemCode(rs.getString("ITEM_CODE"));
				
				mpsBean.getItemBean().setStockBean(new StockBean());
				mpsBean.getItemBean().getStockBean().setStockAmount(rs.getString("STOCK_AMOUNT"));
				mpsBean.getItemBean().getStockBean().setStockCode(rs.getString("STOCK_CODE"));
				mpsBean.getItemBean().getStockBean().setOutputExpecAmount(rs.getString("OUTPUT_EXPEC_AMOUNT"));
				
				mpsBean.getItemBean().setBomBean(new BomBean());
				mpsBean.getItemBean().getBomBean().setLeadTime(rs.getString("LEAD_TIME"));
				mpsBean.getItemBean().getBomBean().setLossRate(rs.getString("LOSS_RATE"));
				mpsBean.getItemBean().setContractDetailBean(new ContractDetailBean());
				mpsBean.getItemBean().getContractDetailBean().setSupplyDate(rs.getString("DELIVERY_SCHEDULE_DATE"));
				mpsBean.getItemBean().getContractDetailBean().setMpsStatus(rs.getString("MPS_STATUS"));
				mpsBean.getItemBean().getContractDetailBean().setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.getItemBean().getContractDetailBean().setSupplyAmount(rs.getString("CONTRACT_QUANTITY"));
				
				mpsList.add(mpsBean);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectMpsList(...)-��");}
			return mpsList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMpsList(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	public List<MpsBean> selectMpsList(int sr, int er) {
		if (logger.isDebugEnabled()) { logger.debug("-----selectMpsList(...)-����");}
		ArrayList<MpsBean> mpsList = new ArrayList<MpsBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT t.* FROM (SELECT ROWNUM RN, ss.ITEM_CODE, ss.STOCK_CODE, ss.STOCK_AMOUNT, ss.OUTPUT_EXPEC_AMOUNT ");
			query.append(" , b.LEAD_TIME, b.LOSS_RATE , ci.DELIVERY_SCHEDULE_DATE, ci.MPS_STATUS, ci.CONTRACT_DETAIL_CODE ");
			query.append(" , ci.CONTRACT_QUANTITY, m.PLAN_DATE, m.MRP_STATUS , m.WORKPLACE_CODE, m.PLAN_AMOUNT ");
			query.append(" FROM CONTRACT_DETAIL ci, ITEM i, BOM b, MPS m, (SELECT s.STOCK_CODE, s.STOCK_AMOUNT ");
			query.append(" , s.OUTPUT_EXPEC_AMOUNT ,t1.* FROM (SELECT ITEM_CODE, MAX(INPUT_DATE) INPUT_DATE ");
			query.append(" , MAX(OUTPUT_DATE) OUTPUT_DATE FROM STOCK GROUP BY ITEM_CODE) t1, STOCK s ");
			query.append(" WHERE t1.ITEM_CODE = s.ITEM_CODE AND t1.INPUT_DATE = s.INPUT_DATE AND t1.OUTPUT_DATE = s.OUTPUT_DATE) ss ");
			query.append(" WHERE ci.ITEM_CODE = i.ITEM_CODE AND ci.ITEM_CODE = b.ITEM_CODE AND ci.ITEM_CODE = ss.ITEM_CODE ");
			query.append(" AND ci.CONTRACT_DETAIL_CODE = m.CONTRACT_DETAIL_CODE ");
			query.append(" ORDER BY ci.ITEM_CODE, ci.DELIVERY_SCHEDULE_DATE) t ORDER BY CONTRACT_DETAIL_CODE DESC  ");
		

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MpsBean mpsBean = new MpsBean();
				mpsBean.setPlanQuantity(rs.getString("PLAN_AMOUNT"));
				mpsBean.setPlanDate(rs.getString("PLAN_DATE"));
				mpsBean.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.setItemCode(rs.getString("ITEM_CODE"));
				mpsBean.setMrpStatus(rs.getString("MRP_STATUS"));
				mpsBean.setItemBean(new ItemBean());
				mpsBean.getItemBean().setItemCode(rs.getString("ITEM_CODE"));
				mpsBean.setWorkplaceCode(rs.getString("WORKPLACE_CODE"));
				mpsBean.getItemBean().setStockBean(new StockBean());
				mpsBean.getItemBean().getStockBean().setStockAmount(rs.getString("STOCK_AMOUNT"));
				mpsBean.getItemBean().getStockBean().setStockCode(rs.getString("STOCK_CODE"));
				mpsBean.getItemBean().getStockBean().setOutputExpecAmount(rs.getString("OUTPUT_EXPEC_AMOUNT"));
				
				mpsBean.getItemBean().setBomBean(new BomBean());
				mpsBean.getItemBean().getBomBean().setLeadTime(rs.getString("LEAD_TIME"));
				mpsBean.getItemBean().getBomBean().setLossRate(rs.getString("LOSS_RATE"));
				mpsBean.getItemBean().setContractDetailBean(new ContractDetailBean());
				mpsBean.getItemBean().getContractDetailBean().setSupplyDate(rs.getString("DELIVERY_SCHEDULE_DATE"));
				mpsBean.getItemBean().getContractDetailBean().setMpsStatus(rs.getString("MPS_STATUS"));
				mpsBean.getItemBean().getContractDetailBean().setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.getItemBean().getContractDetailBean().setSupplyAmount(rs.getString("CONTRACT_QUANTITY"));


				mpsList.add(mpsBean);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectMpsList(...)-��");}
			return mpsList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMpsList(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	public List<MpsBean> selectMpsList(int sr, int er, String mpsStatus, String mrpStatus) {
		if (logger.isDebugEnabled()) { logger.debug("-----selectMpsList(...)-����");}
		ArrayList<MpsBean> mpsList = new ArrayList<MpsBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT t.* FROM (SELECT ROWNUM RN, ss.ITEM_CODE, ss.STOCK_CODE, ss.STOCK_AMOUNT, ss.OUTPUT_EXPEC_AMOUNT ");
			query.append(" , b.LEAD_TIME, b.LOSS_RATE , ci.DELIVERY_SCHEDULE_DATE, ci.MPS_STATUS, ci.CONTRACT_DETAIL_CODE ");
			query.append(" , ci.CONTRACT_QUANTITY, m.PLAN_DATE, m.MRP_STATUS , m.WORKPLACE_CODE, m.PLAN_AMOUNT ");
			query.append(" FROM CONTRACT_DETAIL ci, ITEM i, BOM b, MPS m, (SELECT s.STOCK_CODE, s.STOCK_AMOUNT ");
			query.append(" , s.OUTPUT_EXPEC_AMOUNT ,t1.* FROM (SELECT ITEM_CODE, MAX(INPUT_DATE) INPUT_DATE ");
			query.append(" , MAX(OUTPUT_DATE) OUTPUT_DATE FROM STOCK GROUP BY ITEM_CODE) t1, STOCK s ");
			query.append(" WHERE t1.ITEM_CODE = s.ITEM_CODE AND t1.INPUT_DATE = s.INPUT_DATE AND t1.OUTPUT_DATE = s.OUTPUT_DATE) ss ");
			query.append(" WHERE ci.ITEM_CODE = i.ITEM_CODE AND ci.ITEM_CODE = b.ITEM_CODE AND ci.ITEM_CODE = ss.ITEM_CODE ");
			query.append(" AND ci.CONTRACT_DETAIL_CODE = m.CONTRACT_DETAIL_CODE(+) ");
			query.append(" ORDER BY ci.ITEM_CODE, ci.DELIVERY_SCHEDULE_DATE) t ");
			
			
			con = dataSourceTransactionManager.getConnection();
			
			pstmt = con.prepareStatement(query.toString());
			
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MpsBean mpsBean = new MpsBean();
				mpsBean.setPlanQuantity(rs.getString("PLAN_AMOUNT"));
				mpsBean.setPlanDate(rs.getString("PLAN_DATE"));
				mpsBean.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.setItemCode(rs.getString("ITEM_CODE"));
				mpsBean.setMrpStatus(rs.getString("MRP_STATUS"));
				mpsBean.setItemBean(new ItemBean());
				mpsBean.getItemBean().setItemCode(rs.getString("ITEM_CODE"));
				mpsBean.setWorkplaceCode(rs.getString("WORKPLACE_CODE"));
				mpsBean.getItemBean().setStockBean(new StockBean());
				mpsBean.getItemBean().getStockBean().setStockAmount(rs.getString("STOCK_AMOUNT"));
				mpsBean.getItemBean().getStockBean().setStockCode(rs.getString("STOCK_CODE"));
				mpsBean.getItemBean().getStockBean().setOutputExpecAmount(rs.getString("OUTPUT_EXPEC_AMOUNT"));
				
				mpsBean.getItemBean().setBomBean(new BomBean());
				mpsBean.getItemBean().getBomBean().setLeadTime(rs.getString("LEAD_TIME"));
				mpsBean.getItemBean().getBomBean().setLossRate(rs.getString("LOSS_RATE"));
				mpsBean.getItemBean().setContractDetailBean(new ContractDetailBean());
				mpsBean.getItemBean().getContractDetailBean().setSupplyDate(rs.getString("DELIVERY_SCHEDULE_DATE"));
				mpsBean.getItemBean().getContractDetailBean().setMpsStatus(rs.getString("MPS_STATUS"));
				mpsBean.getItemBean().getContractDetailBean().setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.getItemBean().getContractDetailBean().setSupplyAmount(rs.getString("CONTRACT_QUANTITY"));
				
				
				
				mpsList.add(mpsBean);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectMpsList(...)-��");}
			return mpsList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMpsList(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
	
	
	
	public List<MpsBean> selectMpsList(int sr, int er, String mpsStatus, String mrpStatus,String lineNo,String sDate, String eDate) {
		if (logger.isDebugEnabled()) { logger.debug("-----selectMpsList(...)-����");}
		ArrayList<MpsBean> mpsList = new ArrayList<MpsBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
/*			SELECT t.* 
			  FROM 
			       (SELECT ROWNUM RN
			            , ss.ITEM_CODE --��ǰ��
			            , ss.STOCK_CODE -- ����ڵ�
			            , ss.STOCK_AMOUNT -- ���
			            , ss.OUTPUT_EXPEC_AMOUNT -- �ֹ����� 
			            , b.LEAD_TIME -- ����½ð�
			            , b.LOSS_RATE  -- �սǷ�
			            , ci.DELIVERY_SCHEDULE_DATE -- ��۳���
			            , ci.MPS_STATUS -- �ֻ����ȹ����
			            , ci.CONTRACT_DETAIL_CODE  -- ���ֻ󼼹�ȣ
			            , ci.CONTRACT_QUANTITY -- ���ֹ�ǰ����
			            , m.PLAN_DATE -- �����ȹ����
			            , m.MRP_STATUS  -- ������ ����
			            , m.WORKPLACE_CODE -- ������ȣ
			            , m.PLAN_AMOUNT -- ��ȹ����
			            , m.MPS_CODE -- �ֻ����ȣ
			         FROM CONTRACT_DETAIL ci
			            , ITEM i
			            , BOM b
			            , MPS m
			            , 
			              (SELECT s.STOCK_CODE
			                   , s.STOCK_AMOUNT
			                   , s.OUTPUT_EXPEC_AMOUNT 
			                   ,t1.* 
			                FROM 
			                     (SELECT ITEM_CODE
			                          , MAX(INPUT_DATE) INPUT_DATE 
			                          , MAX(OUTPUT_DATE) OUTPUT_DATE 
			                       FROM STOCK 
			                      GROUP BY ITEM_CODE
			                     ) t1
			                   , STOCK s 
			               WHERE t1.ITEM_CODE = s.ITEM_CODE 
			                     AND t1.INPUT_DATE = s.INPUT_DATE 
			                     AND t1.OUTPUT_DATE = s.OUTPUT_DATE
			              ) ss 
			        WHERE ci.ITEM_CODE = i.ITEM_CODE 
			              AND ci.ITEM_CODE = b.ITEM_CODE 
			              AND ci.ITEM_CODE = ss.ITEM_CODE 
			              AND ci.CONTRACT_DETAIL_CODE = m.CONTRACT_DETAIL_CODE(+) 
			              AND ci.MPS_STATUS = 'Y' 
			              AND m.MRP_STATUS = 'N' 
			              AND m.WORKPLACE_CODE= 'WP-SEO' 
			     ORDER BY ci.ITEM_CODE
			            , ci.DELIVERY_SCHEDULE_DATE
			       ) t 
			 WHERE t.plan_date BETWEEN '2018-10-01' AND '2018-10-31' 
			       AND rn BETWEEN 1 AND 20 */
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT t.* FROM (SELECT ROWNUM RN, ss.ITEM_CODE, ss.STOCK_CODE, ss.STOCK_AMOUNT, ss.OUTPUT_EXPEC_AMOUNT ");
			query.append(" , b.LEAD_TIME, b.LOSS_RATE , ci.DELIVERY_SCHEDULE_DATE, ci.MPS_STATUS, ci.CONTRACT_DETAIL_CODE ");
			query.append(" , ci.CONTRACT_QUANTITY, m.PLAN_DATE, m.MRP_STATUS , m.WORKPLACE_CODE, m.PLAN_AMOUNT, m.MPS_CODE ");
			query.append(" FROM CONTRACT_DETAIL ci, ITEM i, BOM b, MPS m, (SELECT s.STOCK_CODE, s.STOCK_AMOUNT, s.OUTPUT_EXPEC_AMOUNT ,t1.* ");
			query.append(" FROM (SELECT ITEM_CODE, MAX(INPUT_DATE) INPUT_DATE , MAX(OUTPUT_DATE) OUTPUT_DATE FROM STOCK GROUP BY ITEM_CODE) t1, STOCK s ");
			query.append(" WHERE t1.ITEM_CODE = s.ITEM_CODE AND t1.INPUT_DATE = s.INPUT_DATE AND t1.OUTPUT_DATE = s.OUTPUT_DATE) ss	 ");
			query.append(" WHERE ci.ITEM_CODE = i.ITEM_CODE AND ci.ITEM_CODE = b.ITEM_CODE AND ci.ITEM_CODE = ss.ITEM_CODE ");
			query.append(" AND ci.CONTRACT_DETAIL_CODE = m.CONTRACT_DETAIL_CODE(+) AND ci.MPS_STATUS = ? AND m.MRP_STATUS = ? AND m.WORKPLACE_CODE= ? ");
			query.append(" ORDER BY ci.ITEM_CODE, ci.DELIVERY_SCHEDULE_DATE) t ");
			query.append(" WHERE t.plan_date BETWEEN ? AND ? AND rn BETWEEN ? AND ? ");
		

			con = dataSourceTransactionManager.getConnection();
		
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, mpsStatus);
			pstmt.setString(2, mrpStatus);
			pstmt.setString(3, lineNo);
			pstmt.setString(4, sDate);
			pstmt.setString(5, eDate);
			pstmt.setInt(6, sr);
			pstmt.setInt(7, er);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MpsBean mpsBean = new MpsBean();
				mpsBean.setPlanQuantity(rs.getString("PLAN_AMOUNT"));
				mpsBean.setPlanDate(rs.getString("PLAN_DATE"));
				mpsBean.setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.setItemCode(rs.getString("ITEM_CODE"));
				mpsBean.setMrpStatus(rs.getString("MRP_STATUS"));
				mpsBean.setItemBean(new ItemBean());
				mpsBean.getItemBean().setItemCode(rs.getString("ITEM_CODE"));
				mpsBean.setWorkplaceCode(rs.getString("WORKPLACE_CODE"));
				mpsBean.getItemBean().setStockBean(new StockBean());
				mpsBean.getItemBean().getStockBean().setStockAmount(rs.getString("STOCK_AMOUNT"));
				mpsBean.getItemBean().getStockBean().setStockCode(rs.getString("STOCK_CODE"));
				mpsBean.getItemBean().getStockBean().setOutputExpecAmount(rs.getString("OUTPUT_EXPEC_AMOUNT"));
				
				mpsBean.getItemBean().setBomBean(new BomBean());
				mpsBean.getItemBean().getBomBean().setLeadTime(rs.getString("LEAD_TIME"));
				mpsBean.getItemBean().getBomBean().setLossRate(rs.getString("LOSS_RATE"));
				mpsBean.getItemBean().setContractDetailBean(new ContractDetailBean());
				mpsBean.getItemBean().getContractDetailBean().setSupplyDate(rs.getString("DELIVERY_SCHEDULE_DATE"));
				mpsBean.getItemBean().getContractDetailBean().setMpsStatus(rs.getString("MPS_STATUS"));
				mpsBean.getItemBean().getContractDetailBean().setContractDetailCode(rs.getString("CONTRACT_DETAIL_CODE"));
				mpsBean.getItemBean().getContractDetailBean().setSupplyAmount(rs.getString("CONTRACT_QUANTITY"));
				mpsBean.setMpsCode(rs.getString("MPS_CODE"));


				
				mpsList.add(mpsBean);
			}
			if (logger.isDebugEnabled()) { logger.debug("selectMpsList(...)-��");}
			return mpsList;
		} catch (Exception sqle) {
			if (logger.isFatalEnabled()) { logger.fatal("selectMpsList(...)-����");}
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}

	public void insertMps(MpsBean mpsBean) throws DataAccessException {
		if (logger.isDebugEnabled()) { logger.debug("insertMps(...)-����");}
		Connection con = null;
		PreparedStatement pstmt=null;
		StringBuffer query = new StringBuffer();

	
		query.append(" INSERT INTO MPS VALUES ( seq_mps.NEXTVAL, ?,  ?, ?, ? , 'N',?  ) ");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
		
			String str="WP-" + mpsBean.getItemBean().getStockBean().getStockCode().substring(0,3);
			pstmt.setString(1, mpsBean.getContractDetailCode());
			pstmt.setString(2, mpsBean.getPlanQuantity());
			pstmt.setString(3, mpsBean.getPlanDate());
			pstmt.setString(4, str);
			pstmt.setString(5, mpsBean.getItemCode());
	
			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) { logger.debug("insertMps(...)-��");}
		} catch (SQLException e) {
			if (logger.isFatalEnabled()) { logger.fatal("insertMps(...)-����");}
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	public void updateMps(MpsBean mpsBean) {
		if (logger.isDebugEnabled()) { logger.debug("updateMps(...)-����");}
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer query = new StringBuffer();
		query.append(" UPDATE MPS SET MRP_STATUS='Y' ");
		query.append(" WHERE CONTRACT_DETAIL_CODE=? ");
		try {
			con = dataSourceTransactionManager.getConnection();
			pstmt=con.prepareStatement(query.toString());
			pstmt.setString(1, mpsBean.getContractDetailCode());
			pstmt.executeUpdate();
			if (logger.isDebugEnabled()) { logger.debug("updateMps(...)-��");}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) { logger.fatal("updateMps(...)-����");}
			throw new DataAccessException(e.getMessage());
		}finally {
			dataSourceTransactionManager.close(pstmt,rs);
		}
	}
}

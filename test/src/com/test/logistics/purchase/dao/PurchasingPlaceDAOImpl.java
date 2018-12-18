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
import com.test.logistics.purchase.to.PurchasingPlaceBean;

public class PurchasingPlaceDAOImpl implements PurchasingPlaceDAO {
	private static PurchasingPlaceDAO instance;

	private PurchasingPlaceDAOImpl() {
	}

	public static PurchasingPlaceDAO getInstance() {
		if (instance == null)
			instance = new PurchasingPlaceDAOImpl();
		return instance;
	}

	protected final Log logger = LogFactory.getLog(getClass());
	private DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	public int selectRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("selectRowCount() - 시작");
		}
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM PURCHASING_PLACE");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			rs.next();
			if (logger.isDebugEnabled()) {
				logger.debug("selectRowCount() - 끝");
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

	@Override
	public List<PurchasingPlaceBean> selectPurchasingPlaceList(int sr, int er) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectPurchasingPlaceList() - 시작");
		}
		ArrayList<PurchasingPlaceBean> purchasingPlaceBean = new ArrayList<PurchasingPlaceBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();


			query.append(" SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY v.PURCHASING_PLACE_CODE) RN, v.PURCHASING_PLACE_CODE ");
			query.append(" , v.PURCHASING_PLACE_NAME , v.BUSINESS_NUMBER, v.BUSINESS_CONDITIONS, v.REPRESENT_NAME ");
			query.append(" , v.POST_CODE, v.ADDRESS, v.DETAIL_ADDRESS , v.TEL, v.FAX FROM PURCHASING_PLACE v)  ");
			query.append(" WHERE RN BETWEEN ? AND ? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			ResultSet rs = pstmt.executeQuery();
			
		

			while (rs.next()) {
				PurchasingPlaceBean purchasingPlace = new PurchasingPlaceBean();
				purchasingPlace.setPurchasingPlaceCode(rs.getString("PURCHASING_PLACE_CODE"));
				purchasingPlace.setPurchasingPlaceName(rs.getString("PURCHASING_PLACE_NAME"));
				purchasingPlace.setBusinessNumber(rs.getString("BUSINESS_NUMBER"));
				purchasingPlace.setBusinessConditions(rs.getString("BUSINESS_CONDITIONS"));
				purchasingPlace.setRepresentName(rs.getString("REPRESENT_NAME"));
				purchasingPlace.setPostNumber(rs.getString("POST_CODE"));
				purchasingPlace.setAddr(rs.getString("ADDRESS"));
				purchasingPlace.setDetailAddr(rs.getString("DETAIL_ADDRESS"));
				purchasingPlace.setTel(rs.getString("TEL"));
				purchasingPlace.setFax(rs.getString("FAX"));
				purchasingPlaceBean.add(purchasingPlace);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("selectPurchasingPlaceList() - 끝");
			}
			return purchasingPlaceBean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectPurchasingPlaceList()-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);

		}
	}

	@Override
	public void insertPurchasingPlace(PurchasingPlaceBean purchasingPlace) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("insertPurchasingPlace() - 시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO PURCHASING_PLACE VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, purchasingPlace.getPurchasingPlaceCode());
			pstmt.setString(2, purchasingPlace.getPurchasingPlaceName());
			pstmt.setString(3, purchasingPlace.getBusinessNumber());
			pstmt.setString(4, purchasingPlace.getBusinessConditions());
			pstmt.setString(5, purchasingPlace.getRepresentName());
			pstmt.setString(6, purchasingPlace.getPostNumber());
			pstmt.setString(7, purchasingPlace.getAddr());
			pstmt.setString(8, purchasingPlace.getDetailAddr());
			pstmt.setString(9, purchasingPlace.getTel());
			pstmt.setString(10, purchasingPlace.getFax());
			pstmt.executeUpdate();
			
			dataSourceTransactionManager.close(pstmt);
			con = dataSourceTransactionManager.getConnection();
			query.setLength(0);
			query.append(" INSERT INTO DETAIL_CODE (BASIC_CODE,DETAIL_CODE,DETAIL_CODE_NAME) VALUES ( 'LO-06',? , ?) ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, purchasingPlace.getPurchasingPlaceCode() );
			pstmt.setString(2, purchasingPlace.getPurchasingPlaceName() );
			pstmt.executeUpdate();
			
			
			
			if (logger.isDebugEnabled()) {
				logger.debug("insertPurchasingPlace() - 끝");
			}
		} catch (Exception error) {
			if (logger.isFatalEnabled()) {
				logger.fatal("insertPurchasingPlace()-에러");
			}
			throw new DataAccessException(error.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public String selectPurchasingPlace(String name, String regionNo) throws DataAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("selectCustomer() - 시작");
		}
		Connection con = null;
		PreparedStatement pstmt = null;
		String customerNo = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT CUSTOMER_NO FROM CUSTOMER WHERE NAME = ? AND REGION_NO = ?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, regionNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customerNo = rs.getString("CUSTOMER_NO");
			}

			if (logger.isDebugEnabled()) {
				logger.debug("selectCustomer() - 끝");
			}
			return customerNo;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("selectCustomer()-에러");
			}
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);

		}
	}
}

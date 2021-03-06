package com.test.logistics.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.business.to.ClientBean;

public class ClientDAOImpl implements ClientDAO {
	
	private DataSourceTransactionManager dataSourceTransactionManager;

	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	public int selectRowCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM CLIENT");
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
	public List<ClientBean> selectClientList(int sr, int er) throws DataAccessException {
		
		List<ClientBean> clientBean = new ArrayList<ClientBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();

			query.append(" SELECT * FROM ");
			query.append(" (SELECT ROW_NUMBER() OVER(ORDER BY CLIENT_CODE) RN, v.CLIENT_CODE, v.CLIENT_NAME, v.BUSINESS_NUMBER, ");
			query.append(" 	v.BUSINESS_CONDITIONS, v.REPRESENT_NAME, v.POST_CODE,  v.ADDRESS, v.DETAIL_ADDRESS,  v.TEL,  v.FAX  FROM CLIENT v) ");
			query.append("  WHERE RN BETWEEN ? AND ? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			ResultSet rs = pstmt.executeQuery();
			

			while (rs.next()) {
				ClientBean client = new ClientBean();
				client.setClientCode(rs.getString("CLIENT_CODE"));
				client.setClientName(rs.getString("CLIENT_NAME"));
				client.setBusinessNumber(rs.getString("BUSINESS_NUMBER"));
				client.setBusinessConditions(rs.getString("BUSINESS_CONDITIONS"));
				client.setRepresentName(rs.getString("REPRESENT_NAME"));
				client.setPostNumber(rs.getString("POST_CODE"));
				client.setAddr(rs.getString("ADDRESS"));
				client.setDetailAddr(rs.getString("DETAIL_ADDRESS"));
				client.setTel(rs.getString("TEL"));
				client.setFax(rs.getString("FAX"));
				clientBean.add(client);
			}
			
			return clientBean;
		} catch (Exception e) {
			
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);

		}
	}

	@Override
	public void insertClient(ClientBean client) throws DataAccessException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" INSERT INTO CLIENT VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, client.getClientCode());
			pstmt.setString(2, client.getClientName());
			pstmt.setString(3, client.getBusinessNumber());
			pstmt.setString(4, client.getBusinessConditions());
			pstmt.setString(5, client.getRepresentName());
			pstmt.setString(6, client.getPostNumber());
			pstmt.setString(7, client.getAddr());
			pstmt.setString(8, client.getDetailAddr());
			pstmt.setString(9, client.getTel());
			pstmt.setString(10, client.getFax());
			pstmt.executeUpdate();
			
			
			dataSourceTransactionManager.close(pstmt);
			con = dataSourceTransactionManager.getConnection();
			query.setLength(0);
			query.append(" INSERT INTO DETAIL_CODE (BASIC_CODE,DETAIL_CODE,DETAIL_CODE_NAME) VALUES ( 'LO-02',? , ?) ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, client.getClientCode() );
			pstmt.setString(2, client.getClientName() );
			pstmt.executeUpdate();
			
			
			
		} catch (Exception error) {
			
			throw new DataAccessException(error.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public String selectClient(String name, String regionNo) throws DataAccessException {
		
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

			
			return customerNo;
		} catch (Exception e) {
			
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);

		}
	}
}

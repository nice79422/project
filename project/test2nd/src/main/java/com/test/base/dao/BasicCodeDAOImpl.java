package com.test.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.base.to.BasicCodeBean;
import com.test.common.exception.DataAccessException;
import com.test.common.transaction.DataSourceTransactionManager;

public class BasicCodeDAOImpl implements BasicCodeDAO {
	
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	public final void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

	
/*	private static BasicCodeDAO BasicCodeDAOImpl;


	public static void setBasicCodeDAOImpl(BasicCodeDAO basicCodeDAOImpl) {
		BasicCodeDAOImpl = basicCodeDAOImpl;
	}*/


	public int selectRowCount() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT COUNT(*) FROM DETAIL_CODE");
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
	
	
	public List<BasicCodeBean> selectBasicCodeList(int sr, int er, String code) throws DataAccessException {
		
		List<BasicCodeBean> codebeanList = new ArrayList<BasicCodeBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM  ");
			query.append(" ( SELECT ROW_NUMBER() OVER(ORDER BY BASIC_CODE) RN, BASIC_CODE, BASIC_CODE_NAME FROM CODE ) ");
			query.append(" WHERE RN BETWEEN ? AND ? ");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasicCodeBean codeBean = new BasicCodeBean();
				codeBean.setBasicCode(rs.getString("BASIC_CODE"));
				codeBean.setBasicCodeName(rs.getString("BASIC_CODE_NAME"));
				codebeanList.add(codeBean);
			}
		
			return codebeanList;
		} catch (Exception sqle) {
			
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	public List<BasicCodeBean> selectDetailCodeList(int sr, int er, String code) throws DataAccessException {
	
		List<BasicCodeBean> codebeanList = new ArrayList<BasicCodeBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT  * FROM  ");
			query.append(" (SELECT ROW_NUMBER() OVER(ORDER BY DETAIL_CODE) RN, DETAIL_CODE, DETAIL_CODE_NAME, BASIC_CODE ");
			query.append(" FROM DETAIL_CODE WHERE BASIC_CODE= ?) ");
			query.append(" WHERE RN BETWEEN ? AND ? ");

			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, code);
			pstmt.setInt(2, sr);
			pstmt.setInt(3, er);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BasicCodeBean codeBean = new BasicCodeBean();
				codeBean.setDetailCode(rs.getString("DETAIL_CODE"));
				codeBean.setDetailCodeName(rs.getString("DETAIL_CODE_NAME"));
				codeBean.setBasicCode(rs.getString("BASIC_CODE"));
				codebeanList.add(codeBean);
			}
		
			return codebeanList;
		} catch (Exception sqle) {
		
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void insertDetailCode(String dCodeNo, String dCodeName, String CodeNo) throws DataAccessException {
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("INSERT INTO DETAIL_CODE VALUES(?, ?, 'N', ?)");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dCodeNo);
			pstmt.setString(2, dCodeName);
			pstmt.setString(3, CodeNo);
			pstmt.executeUpdate();
		
		} catch (Exception error) {
		
			error.printStackTrace();
		} finally {
			dataSourceTransactionManager.close(pstmt, rs);
		}
	}

	@Override
	public void updateDetailCode(String dCodeNo, String dCodeName, String CodeNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("UPDATE DETAIL_CODE SET DETAIL_CODE_NAME=? ");
			query.append("WHERE DETAIL_CODE_NO=? AND CODE_NO=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dCodeNo);
			pstmt.setString(2, dCodeName);
			pstmt.setString(3, CodeNo);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	@Override
	public void deleteDetailCode(String dCodeNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("DELETE DETAIL_CODE WHERE DETAIL_CODE_NO=?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, dCodeNo);
			pstmt.executeUpdate();
		
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
}

package com.test.common.transaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.test.common.exception.*;


public class DataSourceTransactionManager {
	private DataSource dataSource;
	private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	static DataSourceTransactionManager instance;
	public static DataSourceTransactionManager getInstance(){
		if(instance==null) instance=new DataSourceTransactionManager();
		return instance;
	}

	public Connection getConnection() {
		Connection connection = (Connection) threadLocal.get();
	
		try {
			if (connection == null) {
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
		return connection;
	}

	public void closeConnection() {
		Connection conn = (Connection) threadLocal.get();
		threadLocal.set(null);
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void beginTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void rollbackTransaction() {
		try {
			getConnection().rollback();
			closeConnection();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void commitTransaction() {
		try {
			getConnection().commit();
			closeConnection();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}
	public void close(CallableStatement cstmt, ResultSet rs) {
		try {
			if (cstmt != null)
				cstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}
	public void close(CallableStatement cstmt) {
		try {
			if (cstmt != null)
				cstmt.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}
	public void close(Connection conn) {
		closeConnection();
	}

	public void close(Connection conn, PreparedStatement ps) {
		try {
			closeConnection();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			closeConnection();
			if (ps != null)
				ps.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	public void close(Connection conn, Statement st, ResultSet rs) {
		try {
			closeConnection();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}
}
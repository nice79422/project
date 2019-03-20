package com.test.hr.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.test.common.dao.DataAccessException;

import com.test.common.transaction.DataSourceTransactionManager;
import com.test.hr.emp.to.EmpBean;

public class EmpDAOImpl implements EmpDAO {

	private DataSourceTransactionManager dataSourceTransactionManager;

	public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}
	

    public int selectRowCount(){
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            StringBuffer query = new StringBuffer();
            query.append("SELECT COUNT(*) FROM EMP");
            con = dataSourceTransactionManager.getConnection();
            pstmt = con.prepareStatement(query.toString());
            rs = pstmt.executeQuery();
            rs.next();
            
            return rs.getInt(1);
        } catch(Exception sqle) {
    		
            throw new DataAccessException(sqle.getMessage());
        } finally {
        	dataSourceTransactionManager.close(pstmt, rs);
        }
    }



    @Override
    public void insertEmp(EmpBean emp) throws DataAccessException {
 	 
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs=null;
      try{
    	  
    	  StringBuffer query=new StringBuffer();
          query.append(" INSERT INTO EMP VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
          con = dataSourceTransactionManager.getConnection();
          pstmt = con.prepareStatement(query.toString());
          pstmt.setString(1, emp.getEmpCode());
          pstmt.setString(2, emp.getEmpName());
          pstmt.setString(3, emp.getJobTitle());
          pstmt.setString(4, emp.getHireDate());
          pstmt.setString(5, emp.getEmploymentTerm());
          pstmt.setString(6, emp.getSocialNumber());
          pstmt.setString(7, emp.getTel());
          pstmt.setString(8, emp.getEmail());
          pstmt.setString(9, emp.getPostCode());
          pstmt.setString(10, emp.getAddr());
          pstmt.setString(11, emp.getDetailAddr());
          pstmt.setString(12, emp.getPassword());
          pstmt.setString(13, emp.getImage());
          pstmt.setString(14, emp.getDeptCode());
          pstmt.executeUpdate();
          
            dataSourceTransactionManager.close(pstmt);
			con = dataSourceTransactionManager.getConnection();
			query.setLength(0);
			query.append(" INSERT INTO DETAIL_CODE (BASIC_CODE,DETAIL_CODE,DETAIL_CODE_NAME) VALUES ( 'HS-04',? , ?) ");
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, emp.getEmpCode() );
			pstmt.setString(2, emp.getEmpName() );
			pstmt.executeUpdate();
          
     
      }catch(Exception error){
  	
          throw new DataAccessException(error.getMessage());
      }finally{
     	 dataSourceTransactionManager.close(pstmt, rs);
      }
         }

	@Override
	public List<EmpBean> selectEmpList(int sr, int er) throws DataAccessException {
		
		ArrayList<EmpBean> empbean = new ArrayList<EmpBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM ");
			query.append(
					" (SELECT ROW_NUMBER() OVER(ORDER BY EMP_CODE) RN, EMP_CODE, JOB_TITLE, EMP_NAME, HIRE_DATE, DEPT_CODE FROM EMP) ");
			query.append(" WHERE RN BETWEEN ? AND ?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, sr);
			pstmt.setInt(2, er);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpBean emp = new EmpBean();
				emp.setEmpCode(rs.getString("EMP_CODE"));
				emp.setJobTitle(rs.getString("JOB_TITLE"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setHireDate(rs.getString("HIRE_DATE"));
				emp.setDeptCode(rs.getString("DEPT_CODE"));
				empbean.add(emp);
			}
		
			return empbean;
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	

	@Override
	public List<EmpBean> selectEmpList(int sr, int er,  String empCode, String empName) throws DataAccessException {
		
		ArrayList<EmpBean> empbean = new ArrayList<EmpBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT * FROM ");
			query.append(
					"(SELECT ROW_NUMBER() OVER(ORDER BY EMP_CODE) RN, EMP_CODE, JOB_TITLE, EMP_NAME, HIRE_DATE, DEPT_CODE FROM EMP ");
			query.append(" WHERE EMP_CODE LIKE ? ");
    		query.append(" AND EMP_NAME LIKE ? ) ");
			query.append("WHERE RN BETWEEN ? AND ?");
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, "%"+empCode+"%");
    		pstmt.setString(2, "%"+empName+"%");
    		pstmt.setInt(3, sr);
    		pstmt.setInt(4, er);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpBean emp = new EmpBean();
				emp.setEmpCode(rs.getString("EMP_CODE"));
				emp.setJobTitle(rs.getString("JOB_TITLE"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setHireDate(rs.getString("HIRE_DATE"));
				emp.setDeptCode(rs.getString("DEPT_CODE"));
				empbean.add(emp);
			}
		
			return empbean;
		} catch (Exception e) {
		
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	@Override
	public List<EmpBean> selectEmpList(int sr, int er,  String searchWord) throws DataAccessException {
		
		ArrayList<EmpBean> empbean = new ArrayList<EmpBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
    		String[] search =  searchWord.split("/");
			
			StringBuffer query = new StringBuffer();
			query.append(" SELECT * FROM ");
			query.append(
					" (SELECT ROW_NUMBER() OVER(ORDER BY EMP_CODE) RN, EMP_CODE, JOB_TITLE, EMP_NAME, HIRE_DATE, DEPT_CODE FROM EMP ) ");
			query.append(" WHERE RN BETWEEN ? AND ?");
			switch(search[0]){
    		case "ITEM_CODE" : query.append(" AND EMP_CODE LIKE ? "); break;
    		case "ITEM_NAME" : query.append(" AND EMP_NAME LIKE ? "); break;
    		}
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
    		pstmt.setInt(1, sr);
    		pstmt.setInt(2, er);
    		pstmt.setString(3, "%"+search[1]+"%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				EmpBean emp = new EmpBean();
				emp.setEmpCode(rs.getString("EMP_CODE"));
				emp.setJobTitle(rs.getString("JOB_TITLE"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setHireDate(rs.getString("HIRE_DATE"));
				emp.setDeptCode(rs.getString("DEPT_CODE"));
				empbean.add(emp);
			}
			
			return empbean;
		} catch (Exception e) {
			
			throw new DataAccessException(e.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}
	
	public int selectDbcount() throws DataAccessException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("select count(*) from member");
			
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());

			rs = pstmt.executeQuery();
			rs.next();

			return rs.getInt(1);
		} catch (Exception sqle) {
			throw new DataAccessException(sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (Exception e) {
			}
		}
	}

	@Override
	public EmpBean selectEmp(String empCode) throws DataAccessException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuffer query = new StringBuffer();
			query.append(" select * from emp where EMP_CODE=? ");

			con = dataSourceTransactionManager.getConnection();
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, empCode);
			rs = pstmt.executeQuery();

			EmpBean emp = null;

			if (rs.next()) {
				emp = new EmpBean();
				emp.setEmpCode(rs.getString("EMP_CODE"));
				emp.setEmpName(rs.getString("EMP_NAME"));
				emp.setJobTitle(rs.getString("JOB_TITLE"));
				emp.setHireDate(rs.getString("HIRE_DATE"));
				emp.setEmploymentTerm(rs.getString("EMPLOYMENT_TERM"));
				emp.setSocialNumber(rs.getString("SOCIAL_NUMBER"));
				emp.setTel(rs.getString("TEL"));
				emp.setEmail(rs.getString("EMAIL"));
				emp.setPostCode(rs.getString("POST_CODE"));
				emp.setAddr(rs.getString("ADDRESS"));
				emp.setDetailAddr(rs.getString("DETAIL_ADDRESS"));
				emp.setPassword(rs.getString("PASSWORD"));
				emp.setImage(rs.getString("IMAGE"));
				emp.setDeptCode(rs.getString("DEPT_CODE"));
			}
			
			
			return emp;
		} catch (Exception sqle) {
			
			throw new DataAccessException(sqle.getMessage());
		} finally {
			dataSourceTransactionManager.close(pstmt);
		}
	}

	   @Override
	    public void updateEmp(EmpBean emp){
	    	
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        try{
	            StringBuffer query=new StringBuffer();
	            query.append(" UPDATE EMP SET EMP_CODE=?, EMP_NAME=?, JOB_TITLE=?, HIRE_DATE=?, EMPLOYMENT_TERM=?, SOCIAL_NUMBER=? ");
	            query.append(" , TEL=?, EMAIL=?, POST_CODE=?, ADDRESS=?, DETAIL_ADDRESS=? ");
	            query.append(" , PASSWORD=?, IMAGE=?, DEPT_CODE=? WHERE EMP_CODE=? ");
	            con = dataSourceTransactionManager.getConnection();
	            pstmt = con.prepareStatement(query.toString());
	            
	            pstmt.setString(1, emp.getEmpCode());
	            pstmt.setString(2, emp.getEmpName());
	            pstmt.setString(3, emp.getJobTitle());
	            pstmt.setString(4, emp.getHireDate());
	            pstmt.setString(5, emp.getEmploymentTerm());
	            pstmt.setString(6, emp.getSocialNumber());
	            pstmt.setString(7, emp.getTel());
	            pstmt.setString(8, emp.getEmail());
	            pstmt.setString(9, emp.getPostCode());
	            pstmt.setString(10, emp.getAddr());
	            pstmt.setString(11, emp.getDetailAddr());
	            pstmt.setString(12, emp.getPassword());
	            pstmt.setString(13, emp.getImage());
	            pstmt.setString(14, emp.getDeptCode());
	            pstmt.setString(15, emp.getEmpCode());
	            pstmt.executeUpdate();
	            System.out.println("????彪??标车");
	            
	            dataSourceTransactionManager.close(pstmt);
				con = dataSourceTransactionManager.getConnection();
				query.setLength(0);
				query.append(" UPDATE DETAIL_CODE SET BASIC_CODE='HS-04', DETAIL_CODE_NAME=? WHERE DETAIL_CODE=? ");
				pstmt = con.prepareStatement(query.toString());
				pstmt.setString(1, emp.getEmpName() );
				pstmt.setString(2, emp.getEmpCode() );
				pstmt.executeUpdate();
				 System.out.println("旮半掣旖???????彪??标车");
	           
	        }catch(Exception sqle){
	     		
	            throw new DataAccessException(sqle.getMessage());
	        }finally{
	        	dataSourceTransactionManager.close(pstmt);
	        }
	    }


	public int existedEmp(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			StringBuffer selectQuery = new StringBuffer();
			selectQuery.append("select count(*) from member ");
			selectQuery.append("where id=? ");

		
			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(selectQuery.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				cnt = rs.getInt(1);

			return cnt;
		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				pstmt.close();
				con.close();
				pstmt = null;
				con = null;
			} catch (Exception e) {
			}
		}


	}



    @Override
    public void deleteEmpList(String empCode) throws DataAccessException {
   	   
   	   Connection con = null;
  		   PreparedStatement pstmt = null;
  		   try{
  			StringBuffer query = new StringBuffer();
  			query.append("delete emp where emp_code=?");
  			con = dataSourceTransactionManager.getConnection();
			pstmt = con.prepareStatement(query.toString());
				pstmt.setString(1, empCode);
				pstmt.executeUpdate();
				 System.out.println("????????标车");	
				  dataSourceTransactionManager.close(pstmt);
					con = dataSourceTransactionManager.getConnection();
					query.setLength(0);
					query.append("delete DETAIL_CODE WHERE detail_code=? ");
					pstmt = con.prepareStatement(query.toString());
					pstmt.setString(1, empCode);
					pstmt.executeUpdate();
					 System.out.println("旮半掣旖???????????标车");	
		
  		   }catch(Exception sqle){
        
  			   throw new DataAccessException(sqle.getMessage());
  		   }finally{
  			dataSourceTransactionManager.close(pstmt);
  		   }
      }
}

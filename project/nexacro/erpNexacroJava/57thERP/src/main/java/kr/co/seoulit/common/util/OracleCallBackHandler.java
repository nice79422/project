package kr.co.seoulit.common.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import oracle.sql.ArrayDescriptor;
import oracle.jdbc.driver.OracleConnection;
import oracle.sql.ARRAY;

public class OracleCallBackHandler implements TypeHandler<Object>{
//아이바티스 인 액션등의 타입핸들러 파트를 참조. 타입핸들러/타입핸들러 콜백 인터페이스를 상속하여 구현할수 있다.
//sql맵에서 주소를 등록하여 사용하거나 전체 시스템이 사용가능하도록 공통config에 등록하는것도 가능. 후자의 경우엔 태그별 인식순서에 의한 문법오류에 주의하여 설정이 필요
	//마이바티스 프로그래밍 354p를 참조하여 마이바티스용으로 수정
	Connection conn;

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		//커넥션 획득
		if (ps.getConnection().isWrapperFor(OracleConnection.class)) {
		    this.conn = ps.getConnection().unwrap(OracleConnection.class);
		}


		ArrayDescriptor desc = ArrayDescriptor.createDescriptor("MPS_NO_ARRAY", conn);
//패러미터로 받은 배열
		parameter = new ARRAY(desc, conn, (String[])parameter);
//오라클 배열 타입으로 변환
		ps.setArray(i, (oracle.sql.ARRAY)parameter);

			System.out.println((oracle.sql.ARRAY)parameter);
			System.out.println(i);
	}


/*	public void setParameter(PreparedStatement ps, int i, Object parameter, String jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		 * 아이바티스때 사용하던 메서드. String jdbcType이 JdbcType jdbcType으로 변하면서 사용하지 않게되었다.

	}*/

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String value = "";

		try {

		if(StringUtils.isNotEmpty(rs.getString(columnName))){

		value = new String(rs.getString(columnName).getBytes("8859_1") ,"KSC5601");

		}

		} catch (Exception e) {

		// TODO Auto-generated catch block

		e.printStackTrace();

		}

		//System.out.println("columnName :: " + value);

		return value;

	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
//메서드 오버로딩에 주의. CallableStatement와 ResultSet 2종류를 지원.
	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return cs.getString(columnIndex);
	}


}

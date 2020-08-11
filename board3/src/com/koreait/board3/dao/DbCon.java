package com.koreait.board3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCon {

	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String id = "korea01";
	private static final String pw = "1234";

	public static Connection getCon() throws Exception {  //예외처리를 받는사람에게 미룬다!

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, id, pw);
		System.out.println("연결 성공!");
		
		return con;

	}
	
	//select문이 아니면 ResultSet가 필요없기때문에 이걸로 돌아감.
	public static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);

	}
	
//	Connection : db연결담당
//	PreparedStatement : 쿼리문 실행담당
//	ResultSet : select문의 결과가 담길 곳 (select문을 쓸때는 100% 쓴다)
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {  
		
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {}			
		}
	}
}


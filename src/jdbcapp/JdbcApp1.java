package jdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcApp1 {

	public static void main(String[] args) {
		//1. Driver Class Loading
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Class Loading 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class Loading 실패");
			e.printStackTrace();
		}
		//DB 접속정보
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		//사용자ID
		String user = "hr";
		//패스워드
		String pass = "hr";
		//SQL문
		String sql = "select * from employees";
		
		Connection  con = null;
		Statement  stmt = null;
		ResultSet rs = null;
		
		try {
			//Connection 생성
			con = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println(con.getClass().getName());
			//Statement 생성
			stmt = con.createStatement();
			//SQL 실행
			rs = stmt.executeQuery(sql);
			//SQL문 결과 처리
			while(rs.next()) {
				System.out.println(rs.getString("employee_id") + " " + 
			                                  rs.getString("first_name") + " " +
			                                  rs.getString("hire_date"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			//자원반납
			try {
				if(rs != null) rs.close();
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				System.out.println("자원반납 오류발생 :" + e.getMessage());
				e.printStackTrace();
			}

		}//finally
		
		

	}
	

}

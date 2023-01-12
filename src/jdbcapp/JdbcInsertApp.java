package jdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * JDBC는 Auto Commit을 지원한다.
 * Auto Commit을 사용하지 않으려면
 * 1. Auto Commit mode를 해제한다.
 *       con.setAutoCommit(false);
 * 2. executeUpdate() 메서드 다음에
 *       con.commit();
 * 3. 갱신시 오류가 발생한 경우는 catch 절에 
 *      con.rollback();             
 */
public class JdbcInsertApp {
	//DB 접속정보
	static String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	//사용자ID
	static String user = "hr";
	//패스워드
	static String pass = "hr";
	
	public static void main(String[] args) {
		if(args.length != 3 ) {
			System.out.println("아규먼트가 없습니다. !!!");
			System.exit(0);
		}
		String name = args[0];
		int age = Integer.parseInt(args[1]);
		String password = args[2];
		
		//1. Driver class loading
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Ok");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2. Connection 생성
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DriverManager.getConnection(dbUrl, user, pass);
			//Auto Commit 해제
			con.setAutoCommit(false);
			
			System.out.println(con);
			stmt = con.createStatement();
			
			StringBuilder sb = new StringBuilder();
			sb.append("update users set ")
			   .append("name='"+name+"', ")
			   .append("age="+age+", ")
			   .append("password='"+password+"' ")
			   .append("where userid = 'scott'");
			//String sql = "insert into users values ('scott4','스캇2',20,'abcd',sysdate)";
			String sql = sb.toString();
			System.out.println("UPDATE SQL 문 :" + sql);
			int recCnt = stmt.executeUpdate(sql);
			//Commit 실행
			con.commit();
			
			System.out.println("Insert 된 레코드 건수 : " + recCnt);
			
		} catch (SQLException e) {
			//Rollback 실행
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("Rollback 오류 " + e1.getMessage());
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//finally
		
	}

}

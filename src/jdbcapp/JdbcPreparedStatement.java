package jdbcapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcPreparedStatement {
	//DB 접속정보
	static String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
	//사용자ID
	static String user = "hr";
	//패스워드
	static String pass = "hr";
	
	//Connetion
	Connection con = null;
	
	public JdbcPreparedStatement() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Class Loading 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class Loading 실패");
			e.printStackTrace();
		}
	}
	
	public Connection  getConnection(String url, String user, String pass) {
		try {
			con = DriverManager.getConnection(url,user,pass);
			System.out.println(con);
		} catch (SQLException e) {
			System.out.println("Connection 생성 오류 " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
//	public int deleteUsers() throws SQLException {
//		
//	}
	
	public int updateUsers() throws SQLException {
		String sql = "update users set age = ? where userid = ?";
		int updCnt = 0;
		PreparedStatement pstmt = null;
		
		//1. Connection 생성
		con = getConnection(dbUrl, user, pass);
		//2.Statment 생성
		pstmt = con.prepareStatement(sql);
		//3. 위치표시자에 값을 SET
		pstmt.setInt(1, 25);
		pstmt.setString(2, "css3");
		//4. SQL문 실행
		updCnt = pstmt.executeUpdate();
		
		return updCnt;		
	}
	
	public  int  insertUsers () {
		String[] useridArr = {"css3","javascript","php" };
		String[] nameArr = {"씨에스","자바스크립","피에치피" };
		String[] passArr = {"c123","j123","p123" };
		
		int recCnt = 0;
		PreparedStatement  pstmt = null;
		String sql = "insert into users (userid,name,password,regdate) values (?,?,?,sysdate)";
		
		//1.Connection 생성해라
		con  = getConnection(dbUrl, user, pass);
		//2. PreparedStatement 생성해라
		try {
			pstmt = con.prepareStatement(sql);
			
			for(int i=0;i<useridArr.length;i++) {
			   //3.위치표시자(?)에 값을 SET 해라
				pstmt.setString(1, useridArr[i]);  //userid
				pstmt.setString(2, nameArr[i]);  //name
				pstmt.setString(3, passArr[i]);  //password
			    //4. Insert SQL 문 실행해라	
				recCnt = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return recCnt;
	}
	
	
	
	
	
	public static void main(String[] args) {
		JdbcPreparedStatement  test = new JdbcPreparedStatement();
		//int cnt = test.insertUsers();
		//System.out.println("Insert 된 Row 갯수 : " + cnt + " 건");
		
		try {
			int updCnt = test.updateUsers();
			System.out.println("Update된 갯수 : " + updCnt + "건");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

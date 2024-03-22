package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.Userscore;

public class Dao {
	private final String JDBC_URL="jdbc:mysql://bubble0304.mysql.database.azure.com/bubble";
	private final String DB_USER="fushimi";
	private final String DB_PASS="Wakana515";
	
//	private final String JDBC_URL="jdbc:mysql://localhost:3306/bubble";
//	private final String DB_USER="root";
//	private final String DB_PASS="";

	public Map<String,Integer> put(Userscore userscore) {
		System.out.println("putメソッド呼んだ");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		Map<String,Integer> scoreMap=new HashMap<String,Integer>();
		boolean newUser=true;
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			conn.setAutoCommit(true);

			String sql = "select * from score where name =?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,userscore.getUsername());
			
			//stmt.executeUpdate();
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				newUser=false;
				int dbScore=rs.getInt("score");
				if(userscore.getScore()>dbScore) {
					String sql1 = "update score set score=? where name=?;";
					PreparedStatement stmt1 = conn.prepareStatement(sql1);
					stmt1.setInt(1,userscore.getScore());
					stmt1.setString(2,userscore.getUsername());
					stmt1.executeUpdate();
				}
			}
			
			if(newUser) {
				System.out.println("newuser");
				String sql3 = "insert into score values(?,?);";
				PreparedStatement stmt3= conn.prepareStatement(sql3);
				stmt3.setString(1,userscore.getUsername());
				stmt3.setInt(2,userscore.getScore());
				stmt3.executeUpdate();
			}
			
			String sql2 = "select * from score;";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			ResultSet rs2=stmt2.executeQuery();
			int columnCount=rs2.getMetaData().getColumnCount();	
	        
			while(rs2.next()) {
		        scoreMap.put(rs2.getString(1),rs2.getInt(2));
//		        System.out.println(rs2.getString(1));
//		        System.out.println(rs2.getString(2));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("score.javaのエラー");
			System.out.println(e.getMessage());
			System.out.println("SQL State: " + e.getSQLState());
			System.out.println("Vendor Error Code: " + e.getErrorCode());
		}
		return scoreMap;
	}
}

package cn.edu.zucc.petsys.tool;

import java.sql.Connection;

public class JDBCManager {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/petsystem?&zeroDateTimeBehavior=convertToNull&characterEncoding=utf8";
	private static final String dbUser="root";
	private static final String dbPwd="";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
}
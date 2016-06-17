package cn.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper1 {
	public static Connection conn = null;
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url ="jdbc:mysql://23.234.26.184:3306/a0612153032?user=a0612153032&password=1cc891e0&useUnicode=true&characterEncoding=utf-8"
;
	public static Connection getConn(){
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}

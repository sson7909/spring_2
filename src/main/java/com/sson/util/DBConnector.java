package com.sson.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public  class DBConnector {

	public static Connection getDBConnect() throws Exception{
		
		String user = "user02";
		String password = "user02";
		String url = "ojdbc:oracle:thin:@192.168.20.11:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url, user, password);
		
		return con;
	}
	
	public static void disConnect(Connection con,PreparedStatement st) throws Exception{
		
		con.close();
		st.close();
	}
	
	public static void disConnect(Connection con,PreparedStatement st,ResultSet rs)throws Exception{
		con.close();
		st.close();
		rs.close();
		
	}
}

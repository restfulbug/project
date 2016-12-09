package com.spark.network.junit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import org.junit.Test;

public class TestProcedure {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql:///network";
	private String username = "root";
	private String password = "123456";
	
	@Test
	public void test() throws SQLException {
	
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql = "{call getMaxMark (?,?)}";
			callableStatement = connection.prepareCall(sql);
			callableStatement.registerOutParameter(2, Types.INTEGER);
			callableStatement.setInt(1, 1);
			callableStatement.execute();
			Long long1 = callableStatement.getLong(2);
			System.out.println(long1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally{
		
			connection.close();
		
		}
	
	}

}

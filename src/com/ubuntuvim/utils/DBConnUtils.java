package com.ubuntuvim.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据链接
 * @author ubuntuvim
 * @email 1527254027@qq.com
 * @datatime 2015-7-23 下午10:03:32
 */
public class DBConnUtils {
	
	private static Connection conn = null;
	private static Properties props = null;

	static {
		props = new Properties();
		try {
			//  加载配置文件
			props.load(DBConnUtils.class.getResourceAsStream("/dbconfig.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			//  读取配置文件的值并加载 MySQL 驱动
			Class.forName(props.getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据的 connection 连接对象
	 * @return connection
	 */
	public static Connection getConnection(){
		try {
			// 根据 URL、用户名、密码链接数据库
			conn = DriverManager.getConnection(
					props.getProperty("url"), 
					props.getProperty("username"), 
					props.getProperty("password"));
			conn.setAutoCommit(false);  // 设置为 false 之后需要 conn.commit();才会把SQL 的执行结果提交到数据库
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	
	/**
	 * 关闭数据库连接
	 */
	public static void closeConn(){
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

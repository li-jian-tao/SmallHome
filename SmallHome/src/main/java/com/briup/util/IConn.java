package com.briup.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class IConn implements Conn{
	
	private static Log log = new ILog();

	String driver = "";
	String url = "";
	String user = "";
	String password = "";
	public void init(Properties properties) throws Exception {
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
	}
	
	public Connection conn() throws Exception {
		//1.注册驱动
		Class.forName(driver);
		//2.建立连接
		Connection conn = DriverManager.getConnection(url, user, password);
		log.info("成功连接会话");
		return conn;
	}
}

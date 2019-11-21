package com.briup.SmallHome;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.client.IClient;
import com.briup.client.IGather;
import com.briup.login.ILogin;
import com.briup.util.IConfiguration;
import com.briup.util.IConn;

/**
 *	客户端
 * @author li
 *
 */
public class AppClient 
{
	public static void main(String[] args) {
		String xmlFile = "src/main/java/config.xml";
		try {
			Properties pro = new Properties();
			pro.load(new FileReader("src/main/java/t.properties"));
			IConfiguration con = new IConfiguration(xmlFile);
			IConn iconn = (IConn) con.getConn();
			iconn.init(pro);
			Connection conn = iconn.conn();
			ILogin login = new ILogin();
			//login.reginer(conn);
			login.login(conn);
			IGather igather = (IGather) con.getGather();
			igather.init(pro);
			Collection<Environment> coll = igather.gather();
			IClient iclient = (IClient) con.getClient();
			iclient.init(pro);
			iclient.send(coll);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

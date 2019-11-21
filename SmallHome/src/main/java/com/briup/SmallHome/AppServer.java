package com.briup.SmallHome;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.server.IDBStore;
import com.briup.server.IServer;
import com.briup.util.IConfiguration;
import com.briup.util.IConn;

/**
 * 服务器端
 * @author li
 *
 */
public class AppServer {

	public static void main(String[] args) {
		String xmlFile = "src/main/java/config.xml";
		try {
			Properties pro = new Properties();
			pro.load(new FileReader("src/main/java/t.properties"));
			IConfiguration con = new IConfiguration(xmlFile);
			IServer iserver = (IServer) con.getServer();
			iserver.init(pro);
			Collection<Environment> coll = iserver.reciver();
			IConn conn = (IConn) con.getConn();
			conn.init(pro);
			Connection connection = conn.conn();
			IDBStore iDBStore = (IDBStore) con.getDbStore();
			iDBStore.init(pro);
			iDBStore.saveDb(coll,connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

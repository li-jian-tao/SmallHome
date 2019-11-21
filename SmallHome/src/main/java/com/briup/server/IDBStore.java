package com.briup.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.server.DBStore;
import com.briup.util.ILog;
import com.briup.util.Log;

public class IDBStore implements DBStore{

	private static Log log = new ILog();
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
//	String user = "briup";
//	String password = "briup";
	int count;
	public void init(Properties properties) throws Exception {
		count = Integer.parseInt(properties.getProperty("batchSize"));
	}
	
	public void saveDb(Collection<Environment> coll,Connection conn) throws Exception {
		//计数批处理条数
		int i = 0;
		//前一天日期
		int preday = 0;
		int day = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		//3.建立会话
		PreparedStatement ps = null;
		for (Environment en : coll) {
			i++;
			//当前时间
			Timestamp gather_time = en.getGather_time();
			long time = gather_time.getTime();
			String startString = formatter.format(gather_time);
			day = Integer.parseInt(startString.substring(8,10));
			if(day!=preday) {
				if(ps!=null) {
					ps.executeBatch();
					ps.close();
				}
				String sql = "insert into E_DETAIL_"+day+"("
						+ "name,srcid,dstid,sersoraddress,count,cmd,status,data,gather_date) "
						+ "values(?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				preday = day;
			}
			ps.setString(1,en.getName());
			ps.setString(2,en.getSrcId());
			ps.setString(3,en.getDstId());
			ps.setString(4,en.getSensorAddress());
			ps.setInt(5,en.getCounter());
			ps.setString(6,en.getCmd());
			ps.setInt(7,en.getStatus());
			ps.setDouble(8,en.getData());
			ps.setDate(9,new Date(time));
			ps.addBatch();
			if(i%count==0) {
				ps.executeBatch();
			}
		}
		ps.executeBatch();
		log.info("数据入库完成");
		ps.close();
		conn.close();
	}
	
}

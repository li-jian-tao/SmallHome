package com.briup.client;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.util.ILog;
import com.briup.util.Log;

public class IClient implements Client{

	private static Log log = new ILog();
	String ip;
	int port;
	
	public void init(Properties properties) throws Exception {
		ip = properties.getProperty("ip");
		port = Integer.parseInt(properties.getProperty("port"));
	}

	public void send(Collection<Environment> coll) throws Exception {
		System.out.println("客户端启动");
		log.info("客户端启动");
		OutputStream out = null;
		ObjectOutputStream oos = null;
		Socket socket = new Socket(ip,port);
		System.out.println(socket.getInetAddress());
		out = socket.getOutputStream();
		oos = new ObjectOutputStream(out);
		oos.writeObject(coll);
		oos.flush();
		System.out.println("采集数据"+coll.size());
		log.info("采集数据数:"+coll.size());
		oos.close();
		out.close();
		socket.close();
	}

}

package com.briup.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.server.Server;
import com.briup.util.ILog;
import com.briup.util.Log;

public class IServer implements Server{

	private static Log log = new ILog();
	int port;
	int i = 0;
	Collection<Environment> coll;
	ServerSocket server;
	Socket socket = null;
	InputStream is = null;
	ObjectInputStream ois = null;
	
	public void init(Properties properties) throws Exception {
		port = Integer.parseInt(properties.getProperty("port"));
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Environment> reciver() throws Exception {
		System.out.println("服务器启动");
		log.info("服务器启动");
		server = new ServerSocket(port);
		socket = server.accept();
		is = socket.getInputStream();
		ois = new ObjectInputStream(is);
		coll = (Collection<Environment>) ois.readObject();
		shutdown();
//		for (Environment en : coll) {
//			i++;
//			System.out.println(i+" "+en);
//		}
		return coll;
	}

	public void shutdown() {
		if (coll==null) {
			try {
				is.close();
				ois.close();
				server.close();
				socket.close();
				System.out.println("服务器关闭");
				log.info("服务器关闭");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("服务器未关闭");
				log.info("服务器未关闭");
			}
		}
	}

	public static void main(String[] args) {
		try {
			new IServer().reciver();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

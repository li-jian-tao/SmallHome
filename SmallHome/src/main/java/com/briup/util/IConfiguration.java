package com.briup.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.server.DBStore;
import com.briup.server.Server;

public class IConfiguration implements Configuration{

	static String xmlFile = "src/main/java/config.xml";
	Map<String,WossModuleInit> map = new HashMap<String,WossModuleInit>();
	public IConfiguration() {	
	}
	
	public IConfiguration(String path) {
		
		Properties pro = new Properties();
		//获取工仓实例
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//获取对象
		DocumentBuilder builder;
		try {
			File file = new File("src/main/java/t.properties");
			builder = factory.newDocumentBuilder();
			//获得Document
			Document parse = builder.parse(xmlFile);
			Element root = parse.getDocumentElement();
			NodeList nodes = root.getChildNodes();
			for (int i=0;i<nodes.getLength();i++) {
				Node node = nodes.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE) {//获取非文本元素的名字
					System.out.println("元素节点名字: "+node.getNodeName());
					NamedNodeMap attributes = node.getAttributes();
					Node item = attributes.item(0);
					System.out.println(item.getNodeValue());
					Object instance = Class.forName(item.getNodeValue()).newInstance();//获取类加载对象
					map.put(node.getNodeName(), (WossModuleInit) instance);
				}
				NodeList nodeList = node.getChildNodes();
				for(int k=0;k<nodeList.getLength();k++) {
					Node item = nodeList.item(k);
					//如果这个节点是元素节点的话
					if(item.getNodeType()==Node.ELEMENT_NODE) {
						//拿出元素节点的名字和它的文本值
						System.out.println(item.getNodeName()+" = "+item.getTextContent());
						pro.setProperty(item.getNodeName(), item.getTextContent());
					}
				}			
			}
			FileOutputStream fos = new FileOutputStream(file);
			pro.store(fos, null);
			fos.flush();
			fos.close();
			for (String ma : map.keySet()) {
				System.out.println(ma);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Log getLogger() throws Exception {
		return (Log) map.get("Log");
	}

	public Server getServer() throws Exception {
		return (Server) map.get("Server");
	}

	public Client getClient() throws Exception {
		return (Client) map.get("Client");
	}

	public DBStore getDbStore() throws Exception {
		return (DBStore) map.get("DBStore");
	}

	public Gather getGather() throws Exception {
		return (Gather) map.get("Gather");
	}

	public Conn getConn() throws Exception {
		return (Conn) map.get("Conn");
	}
	

}

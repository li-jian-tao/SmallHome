package com.briup.util;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.server.DBStore;
import com.briup.server.Server;



/**
 * Simple to Introduction
 * @ProjectName:  物联网环境监测系统
 * @Package: com.briup.environment.util
 * @InterfaceName:  Configuration
 * @Description:  Configuration接口提供了配置模块的规范。 配置模块通过某种配置方式将Logger、Login、<br/>
 * 							 Gather、Client、Server、DBStore等模块的实现类进行实例化， 并且将其所需要配置信息予以<br/>
 * 							传递。通过配置模块可以获得各个模块的实例。
 * @CreateDate:   2018-1-25 14:28:30
 * @author briup
 * @Version: 1.0
 */
public interface Configuration {
	/**
	 * 获取日志模块的实例
	 * @return 日志对象
	 * @throws Exception
	 */
	public Log getLogger()throws Exception;
	/**
	 * 获取服务器端的实例
	 * @return 服务器对象
	 * @throws Exception
	 */
	public Server getServer()throws Exception;
	/**
	 * 获取客户端的实例
	 * @return  客户端对象
	 * @throws Exception
	 */
	public Client getClient()throws Exception;
	/**
	 * 获取入库连接的实例
	 * @return  连接对象
	 * @throws Exception
	 */
	public Conn getConn()throws Exception;
	/**
	 * 获取入库模块的实例
	 * @return  入库对象
	 * @throws Exception
	 */
	public DBStore getDbStore()throws Exception;
	/**
	 * 获取采集模块的实例
	 * @return  采集对象
	 * @throws Exception
	 */
	public Gather getGather()throws Exception;
	
}

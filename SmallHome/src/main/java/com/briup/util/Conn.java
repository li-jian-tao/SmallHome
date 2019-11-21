package com.briup.util;

import java.sql.Connection;

public interface Conn extends WossModuleInit{
	/**
	 * Simple to Introduction
	 * @ProjectName:  物联网环境监测系统
	 * @Package: com.briup.environment.util
	 * @InterfaceName:  Conn
	 * @Description:  Conn接口规定了链接数据库方法.<br>
	 * @CreateDate:   2018-1-25 14:28:30
	 * @author briup
	 * @Version: 1.0
	 */
	public Connection conn() throws Exception;
}

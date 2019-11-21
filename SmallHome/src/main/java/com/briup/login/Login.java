package com.briup.login;

import java.sql.Connection;

/**
 * Simple to Introduction
 * @ProjectName:  物联网环境监测系统
 * @Package: com.briup.environment.login
 * @InterfaceName:  Login
 * @Description:  Login接口规定了管理员的登录注册.当Gather执行gather()方法时<br>。
 * @CreateDate:   2018-1-25 14:28:30
 * @author briup
 * @Version: 1.0
 */
public interface Login {

	public void login(Connection conn) throws Exception;
	public void reginer(Connection conn) throws Exception;
}

package com.briup.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ILogin implements Login{

	public void login(Connection conn) throws Exception {
		System.out.println("输入用户名");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		System.out.println("输入密码");
		String p = sc.next();
		String sql = "select * from U where username = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);
		System.out.println("登陆信息");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String pwd = rs.getString("pwd");
			String gender = rs.getString("gender");
			String info = rs.getString("info");
			if(pwd.equals(p)) {
				System.out.println(id+" "+username+" "+pwd+" "+gender+" "+info);
				System.out.println("登陆成功");
			}else {
				System.out.println("登陆失败");
			}
		}
		ps.close();
		conn.close();
	}

	public void reginer(Connection conn) throws Exception {
		System.out.println("添加信息");
		String sql = "insert into U values(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		ps.setInt(1, id);
		String username = sc.next();
		ps.setString(2, username);
		String pwd = sc.next();
		ps.setString(3, pwd);
		String gender = sc.next();
		ps.setString(4, gender);
		String info = sc.next();
		ps.setString(5, info);
		ps.executeUpdate();
	}

}

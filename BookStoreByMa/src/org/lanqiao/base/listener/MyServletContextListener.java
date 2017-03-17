package org.lanqiao.base.listener;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.lanqiao.admin.util.MyUtils;

public class MyServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		// 通过类加载器获取配置文件的输入流
		InputStream is = MyServletContextListener.class.getClassLoader().getResourceAsStream("config.properties");
		// 通过properties对象将配置文件的信息读出来放到properties对象中
		try {
			MyUtils.config.load(is);
			//获取项目的相对路径
			
			String driver = MyUtils.config.getProperty("driver");
			String url = MyUtils.config.getProperty("url");
			String user = MyUtils.config.getProperty("user");
			String pwd = MyUtils.config.getProperty("pwd");
			String max = MyUtils.config.getProperty("max");
			String min = MyUtils.config.getProperty("min");
			String init = MyUtils.config.getProperty("init");
			String incrementby = MyUtils.config.getProperty("incrementby");
			// 配置数据源
			MyUtils.ds.setDriverClass(driver);
			MyUtils.ds.setJdbcUrl(url);
			MyUtils.ds.setUser(user);
			MyUtils.ds.setPassword(pwd);
			MyUtils.ds.setMaxPoolSize(Integer.parseInt(max));
			MyUtils.ds.setMinPoolSize(Integer.parseInt(min));
			MyUtils.ds.setInitialPoolSize(Integer.parseInt(init));
			MyUtils.ds.setAcquireIncrement(Integer.parseInt(incrementby));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

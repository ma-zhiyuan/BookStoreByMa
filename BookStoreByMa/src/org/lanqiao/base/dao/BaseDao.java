package org.lanqiao.base.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.lanqiao.admin.util.MyUtils;

@SuppressWarnings("unchecked")
public class BaseDao<T> {
	
	/**
	 * 通用的获取一个对像的方法
	 * @param conn
	 * @param sql
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T get(String sql,int id) throws SQLException{
		Connection conn = MyUtils.conns.get();
		BeanHandler<T> rsh = new BeanHandler<T>(MyUtils.getGenericClass(this.getClass()));
		T t = MyUtils.qr.query(conn, sql, rsh, id);
		return t;
	}
	
	/**
	 * 通用的获取一波的方法
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public List<T> getAll(String sql,Object ... args) throws SQLException{
		Connection conn = MyUtils.conns.get();
		List<T> ts = new ArrayList<T>();
		BeanListHandler<T> rsh = new BeanListHandler<T>(MyUtils.getGenericClass(this.getClass()));
		ts = MyUtils.qr.query(conn, sql, rsh, args);
		return ts;
	}
	
	/**
	 * 获取一个值的方
	 * @param conn
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException 
	 */
	public Object getE(String sql,Object ... args ) throws SQLException{
		Connection conn = MyUtils.conns.get();
		Object obj = null;
		ScalarHandler<Object> rsh = new ScalarHandler<Object>();
		obj = MyUtils.qr.query(conn, sql, rsh, args);
		return obj;
	}
	
}

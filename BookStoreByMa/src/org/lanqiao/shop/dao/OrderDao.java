package org.lanqiao.shop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;
import org.lanqiao.shop.entity.Order;

public class OrderDao extends BaseDao<Order> implements Dao<Order>{

	@Override
	public Order getT(int id) {
		return null;
	}

	@Override
	public List<Order> getAll(Object... args) {
		Connection conn = MyUtils.conns.get();
		List<Order> orders;
		try {
			String sql = "select id,time,total,issend,iscommented from orders";
			orders = this.getAll(sql);
			return orders;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(Order t) {
		Connection conn = MyUtils.conns.get();
		try {
			String sql = "update orders set user_id = ?,time = ?,total = ?,issend = ?,address_id=?,iscommented=? where id = ?";
			int i = MyUtils.qr.update(conn, sql, t.getUser().getId(),new java.sql.Date(t.getTime().getTime()),t.getTotal(),t.getIsSend(),t.getAddress().getId(),t.getIsCommented(),t.getId());
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int delete(Order t) {
		return 0;
	}

	@Override
	public int delete(int id) {
		return 0;
	}

	@Override
	public int add(Order order) {
		Connection conn = MyUtils.conns.get();
		int id = this.getId();
		order.setId(id);
		int i;
		try {
			String sql="insert into orders values (?,?,?,?,?,?,?)";
			i = MyUtils.qr.update(conn, sql, order.getId(),order.getUser().getId(),new java.sql.Date(order.getTime().getTime()),order.getTotal(),order.getIsSend(),order.getAddress().getId(),Order.ISCOMMENTED_FALSE);
			if(i>0){
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	public int getId(){
		int b;
		try {
			String sql = "select Max(id) from orders";
			Object e = this.getE(sql);
			b = (Integer)e+1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

	/**
	 * 根据用户ID获取所有的订单
	 * @param id
	 * @return
	 */
	public List<Order> getAllByUserId(int id) {
		List<Order> orders = null;
		try {
			String sql = "select id,time,total,issend,iscommented from orders where user_id = ?";
			orders = this.getAll(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return orders;
	}

	public void send(int id) {
		try {
			Connection conn = MyUtils.conns.get();
			String sql = "update orders set issend = 1 where id = ?";
			MyUtils.qr.update(conn, sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

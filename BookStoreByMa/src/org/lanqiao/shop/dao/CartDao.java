package org.lanqiao.shop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;
import org.lanqiao.shop.entity.Cart;

public class CartDao extends BaseDao<Cart> implements Dao<Cart>{


	@Override
	public int update(Cart t) {
		Connection conn = MyUtils.conns.get();
		try {
			String sql = "update carts set total=? where id=?";
			return MyUtils.qr.update(conn, sql, t.getTotal(),t.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	@Override
	public int add(Cart t) {
		Connection conn = MyUtils.conns.get();
		try {
			//获取id
			String getId = "select Max(id) from carts";
			Object e = this.getE(getId);
			int id = (Integer)e +1;
			t.setId(id);
			//插入数据
			String sql = "insert into carts values (?,?,?)";
			int i = MyUtils.qr.update(conn, sql,t.getId(),t.getUser().getId(),t.getTotal());
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Cart getByUserId(int userId) {
		Cart cart=null;
		try {
			String sql = "select id,total from carts where user_id = ?";
			cart = this.get(sql, userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return cart;
	}


	@Override
	public Cart getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Cart> getAll(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int delete(Cart t) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

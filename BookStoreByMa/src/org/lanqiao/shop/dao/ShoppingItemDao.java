package org.lanqiao.shop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.lanqiao.admin.dao.BookDao;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;
import org.lanqiao.shop.entity.ShoppingItem;

public class ShoppingItemDao extends BaseDao<ShoppingItem> implements Dao<ShoppingItem> {

	/**
	 * 通过购物车来获取
	 */
	@Override
	public List<ShoppingItem> getAll(Object... args) {
		String sql = "select id,count,itemprice from shoppingitems where cart_id  =?";
		List<ShoppingItem> items = new ArrayList<ShoppingItem>();
		try {
			items = this.getAll(sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return items;
	}

	@Override
	public int update(ShoppingItem t) {
		Connection conn = MyUtils.conns.get();
		try {
			String sql = "update shoppingitems set book_id=?,count=?,itemprice=?" + " where id=?";
			int i = MyUtils.qr.update(conn, sql, t.getBook().getId(), t.getCount(), t.getItemPrice(), t.getId());
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过购物项获取该购物项目的书的id
	 * 
	 * @param conn
	 * @param item
	 * @return
	 */
	public int getBookId(ShoppingItem item) {
		try {
			String sql = "select book_id from shoppingitems where id = ?";
			Object objId = this.getE(sql, item.getId());
			int i = ((Integer) objId);
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ShoppingItem getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(ShoppingItem t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(ShoppingItem t) {
		int i = 0;
		try {
			Connection conn = MyUtils.conns.get();
			int id = this.getId();
			t.setId(id);
			String sql = "insert into shoppingitems values (?,?,?,?,null,?)";
			i = MyUtils.qr.update(conn, sql, t.getId(), t.getBook().getId(), t.getCount(), t.getItemPrice(),
					t.getCart().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return i;
	}

	public int getId() {
		int id = 0;
		try {
			String getId = "select Max(id) from shoppingitems";
			Object e = this.getE(getId);
			id = (Integer)e+1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return id;
	}

	public boolean checkStock(ShoppingItem si) {
		Book book = new BookDao().getT(si.getBook().getId());
		if(book.getStock()<si.getCount()){
			return false;
		}
		return true;
	}

	public void cartToOrder(ShoppingItem si,int orderId) {
		try {
			Connection conn = MyUtils.conns.get();
			String sql="update shoppingitems set cart_id = null,order_id = ? where id = ?";
			int i = MyUtils.qr.update(conn, sql, orderId,si.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<ShoppingItem> getAllByOrderId(int orderId) {
		List<ShoppingItem> items = null;
		try {
			String sql = "select id,count,itemprice from shoppingitems where order_id = ?";
			items = this.getAll(sql, orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return items;
	}

}

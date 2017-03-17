package org.lanqiao.admin.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.bean.BookCretiera;
import org.lanqiao.base.bean.Page;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;

public class BookDao extends BaseDao<Book> implements Dao<Book> {

	/**
	 * 使用ID获取一本书
	 */
	@Override
	public Book getT(int id) {
		Connection conn = MyUtils.conns.get();
		String sql = "select id,name,price,publishcorp,stock,sold,isonsale,details,surface,type,onsaletime from books where id=?";
		try {
			return this.get(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取所有的书
	 */
	public List<Book> getAll(Object... args) {
		try {
			Connection conn = MyUtils.conns.get();
			List<Book> books = new ArrayList<Book>();
			String sql = "select * from books";
				return this.getAll(sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(Book t) {
		Connection conn = MyUtils.conns.get();
		int i = 0;
		String sql = "update books set name=?,price=?," + "publishcorp=?, stock=? , sold=?,"
				+ "isonsale=?,details=?,surface=?," + "type=?,onsaletime=? where id=?";
		try {
			i = MyUtils.qr.update(conn, sql, t.getName(), t.getPrice(), t.getPublishCorp(), t.getStock(), t.getSold(),
					t.getIsOnSale(), t.getDetails(), t.getSurface(), t.getType(),
					new java.sql.Date(t.getOnSaleTime().getTime()), t.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int delete(Book t) {
		return delete(t.getId());
	}

	/**
	 * 删除一本书
	 */
	@Override
	public int delete(int id) {
		Connection conn = MyUtils.conns.get();
		int i = 0;
		String sql = "delete from books where id=?";
		try {
			i = MyUtils.qr.update(conn, sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 增加一本书
	 */
	@Override
	public int add(Book t) {
		Connection conn = MyUtils.conns.get();
		int key = 0;
		// 先通过序列获取id
		String sql = "select max(id) from books";
		String sql2 = "insert into books values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ScalarHandler<Integer> rsh = new ScalarHandler<Integer>();
			Integer l = MyUtils.qr.query(conn, sql, rsh);
			// 主键
			key = l+1;
			t.setId(key);
			// 插入一条数据
			int result = MyUtils.qr.update(conn, sql2, t.getId(), t.getName(), t.getPrice(), t.getPublishCorp(),
					t.getStock(), t.getSold(), t.getIsOnSale(), t.getDetails(), t.getSurface(), t.getType(),
					new java.sql.Date(t.getOnSaleTime().getTime()));
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Page<Book> getPage(BookCretiera bc) {
		Page<Book> page = null;
		String sql ;
		String sql2;
		try {
			double min = bc.getMinPrice();
			double max = bc.getMaxPrice();
			String key = bc.getKeyword();
			int orderbyInt = bc.getOrderBy();
			String orderby = "id asc";
			int pageNo = bc.getPageNo();
			int pageSize = bc.getPageSize();
			switch(orderbyInt){
				case 1:
					orderby = "price asc";
					break;
				case 2:
					orderby = "price desc";
					break;
				case 3:
					orderby = "sold asc";
					break;
				case 4:
					orderby = "sold desc";
					break;
				default:
					orderby = "id desc";
			}
			//查询语句
			sql = "SELECT * FROM (SELECT * FROM books "
					+ "WHERE price"
					+ " BETWEEN ? AND ? "
					+ "and isOnsale=1 "
					+ "and (name like '%"+key+"%' or publishCorp like '%"+key+"%' or details like '%"+key+"%' or type like '%"+key+"%')"
					+ " order by "+orderby+" )books LIMIT ?,?";
			//查询有多少条数据
			sql2="select count(id) from books where price between ? and ? and (name like '%"+key+"%' or publishCorp like '%"+key+"%' or details like '%"+key+"%' or type like '%"+key+"%')";
			Object e = this.getE(sql2, min,max);
			int count = ((Long)e).intValue();
			bc.setCount(count);
			//计算每页起始和结束
			int from = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			//获取当前页的所有书的集合
			List<Book> books = this.getAll(sql,min,max,from,end);
			//封装成一个Page对象
			Page<Book> bookPage  = new Page<Book>(books,bc);
			return bookPage;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return page;
	}

}

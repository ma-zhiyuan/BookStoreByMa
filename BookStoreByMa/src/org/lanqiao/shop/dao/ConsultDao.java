package org.lanqiao.shop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.service.BookService;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;
import org.lanqiao.shop.entity.Consult;

public class ConsultDao extends BaseDao<Consult> implements Dao<Consult>{

	@Override
	public List<Consult> getAll(Object... args) {
		try {
			String sql="select id,time,content,isreplied from consults where book_id=?";
			List<Consult> consults = this.getAll(sql, args);
			return consults;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Consult> getById(int bookId) {
		return this.getAll(bookId);
	}

	public int getUserId(Consult c) {
		String sql = "select user_id from consults where id=?";
		try {
			Object obj = this.getE(sql, c.getId());
			return (Integer)obj;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取回复的id
	 * @param conn
	 * @param c
	 * @return
	 */
	public int getReplyId(Consult c) {
		try {
			String sql="select reply_id from consults where id=?";
			Object obj = this.getE(sql, c.getId());
			if(obj!=null){
				return ((Integer)obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return 0;
	}

	@Override
	public Consult getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Consult t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Consult t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Consult t) {
		Connection conn = MyUtils.conns.get();
		int id = this.getId();
		t.setId(id);
		try {
			String sql = "insert into consults values (?,?,?,?,?,?,null)";
			int i = MyUtils.qr.update(conn, sql, t.getId(),new java.sql.Date(t.getTime().getTime()),t.getContent(),t.getBook().getId(),t.getIsReplied(),t.getUser().getId());
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public int getId() {
		int b;
		try {
			String sql="select Max(id) from Consults";
			Object e = this.getE(sql);
			b = (Integer) e+1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

	public List<Consult> getUnreplied() {
		try {
			String sql = "select id,time,content,isreplied from consults where isreplied = 0";
			List<Consult> consults = this.getAll(sql);
			return consults;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Book getBook(int consultId) {
		int bookId = this.getBookId(consultId);
		return new BookService().getById(bookId, false);
	}

	public int getBookId(int consultId) {
		int b;
		try {
			String sql ="select book_id from consults where id = ?";
			Object e = this.getE(sql, consultId);
			b = (Integer) e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

	public int getUnrepliedCount() {
		try {
			String sql = "select count(id) from consults where isreplied = 0";
			Object e = this.getE(sql);
			int b =Integer.valueOf(((Long)e).toString());
			return b;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void replied(String consultId,int replyId) {
		Connection conn = MyUtils.conns.get();
		try {
			String sql = "update consults set isreplied = 1,reply_id = ? where id = ?";
			MyUtils.qr.update(conn, sql, replyId,consultId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

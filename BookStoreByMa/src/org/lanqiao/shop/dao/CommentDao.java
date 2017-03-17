package org.lanqiao.shop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;
import org.lanqiao.shop.entity.Comment;

public class CommentDao extends BaseDao<Comment> implements Dao<Comment>  {

	@Override
	public List<Comment> getAll(Object... args) {
		List<Comment> comments = null;
		try {
			String sql = "select id,time,content,stars from comments where book_id = ?";
			comments = this.getAll(sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return comments;
	}
	
	/**
	 * 通过评论获取评论的用户
	 * @param conn
	 * @param comment
	 * @return
	 */
	public int getUserId(Comment comment){
		String sql = "select user_id from comments where id=?";
		try {
			Object e = this.getE(sql, comment.getId());
			return ((Integer)e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

	@Override
	public Comment getT(int id) {
		return null;
	}

	@Override
	public int update(Comment t) {
		return 0;
	}

	@Override
	public int delete(Comment t) {
		return 0;
	}

	@Override
	public int delete(int id) {
		return 0;
	}

	@Override
	public int add(Comment t) {
		Connection conn = MyUtils.conns.get();
		try {
			String sql = "insert into comments values (?,?,?,?,?,?)";
			int id = this.getId();
			t.setId(id);
			int i = MyUtils.qr.update(conn, sql, t.getId(),new java.sql.Date(t.getTime().getTime()),t.getContent(),t.getBook().getId(),t.getStars(),t.getUser().getId());
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public int getId(){
		int b;
		try {
			String sql="select max(id) from comments";
			Object e = this.getE(sql);
			b = (Integer) e+1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

}

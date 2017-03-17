package org.lanqiao.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.admin.dao.UserDao;
import org.lanqiao.admin.entity.User;
import org.lanqiao.shop.dao.CommentDao;
import org.lanqiao.shop.entity.Comment;

public class CommentService {

	/**
	 * 根据book id获取这本书所有的评论
	 * 
	 * @param bookId
	 * @return
	 */
	public List<Comment> getAll( int bookId) {
		CommentDao cdao = new CommentDao();
		List<Comment> comments = new ArrayList<Comment>();
		comments = cdao.getAll(bookId);
		// 为评论赋User属性
		for (Comment c : comments) {
			this.setUser(c);
		}
		return comments;
	}

	/**
	 * 为评论赋User对象
	 * 
	 * @param conn
	 * @param c
	 */
	public void setUser(Comment c) {
		CommentDao cdao = new CommentDao();
		// 通过评论获取用户的id
		int userId = cdao.getUserId(c);
		// 获取用户
		UserDao udao = new UserDao();
		User user = udao.getT(userId);
		c.setUser(user);
	}

	public void add(Comment comment) {
		new CommentDao().add(comment);
	}
}

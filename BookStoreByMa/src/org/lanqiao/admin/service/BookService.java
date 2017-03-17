package org.lanqiao.admin.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.lanqiao.admin.dao.BookDao;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.bean.BookCretiera;
import org.lanqiao.base.bean.Page;
import org.lanqiao.shop.entity.Comment;
import org.lanqiao.shop.entity.Consult;
import org.lanqiao.shop.service.CommentService;
import org.lanqiao.shop.service.ConsultService;

public class BookService {

	private List<Book> all;

	/*
	 * 获取一波书
	 */
	public List<Book> getAll(Object... args) {
		return new BookDao().getAll(args);
	}

	public int add(Book book) {
		return new BookDao().add(book);
	}

	/**
	 * 
	 * @param id
	 * @param getCommentsAndConsult
	 *            是否获取咨询和评论
	 * @return
	 */
	public Book getById(int id, boolean getCommentsAndConsult) {
		// 获取连接

		// 执行事务的各种方法
		BookDao bdao = new BookDao();
		Book book = bdao.getT(id);
		if (getCommentsAndConsult) {
			CommentService cs = new CommentService();
			List<Comment> comments = cs.getAll(id);
			book.setComments(comments);

			ConsultService conSer = new ConsultService();
			List<Consult> consults = conSer.getAll(id);
			book.setConsults(consults);
		}
		return book;
	}

	/**
	 * 下架
	 * 
	 * @param id
	 * @return
	 */
	public int down(int id) {
		Book book = this.getById(id, false);
		book.setIsOnSale(0);
		int i = this.update(book);
		return i;
	}

	public int update(Book book) {
		BookDao dao = new BookDao();
		int i = dao.update(book);
		return i;
	}

	public int up(int id) {
		Book book = this.getById(id, false);
		book.setIsOnSale(1);
		int i = this.update(book);
		return i;
	}

	public Page<Book> getAll(BookCretiera bc) {
		Page<Book> books = null;
		BookDao dao = new BookDao();
		books = dao.getPage(bc);
		return books;
	}

	public Page<Book> getWeiXinBook(String key) {
		BookCretiera bc = new BookCretiera();
		bc.setPageSize(Integer.parseInt(MyUtils.config.getProperty("weixinMax")));
		bc.setMinPrice(0);
		bc.setMaxPrice(9999);
		bc.setPageNo(1);
		bc.setOrderBy(4);
		bc.setKeyword(key);
		return this.getAll(bc);
	}

}

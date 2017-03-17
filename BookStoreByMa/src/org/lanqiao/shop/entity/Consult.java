package org.lanqiao.shop.entity;

import java.util.Date;

import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.entity.Reply;
import org.lanqiao.admin.entity.User;

public class Consult {
	private int id;
	private Date time;
	private String content;
	private Book book;
	private int isReplied;
	private User user;
	private Reply reply;
	
	public static final int ISREPLIED_FALSE=0;
	public static final int ISREPLIED_TRUE=1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getIsReplied() {
		return isReplied;
	}

	public void setIsReplied(int isReplied) {
		this.isReplied = isReplied;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public Consult(int id, Date time, String content, Book book, int isReplied, User user, Reply reply) {
		super();
		this.id = id;
		this.time = time;
		this.content = content;
		this.book = book;
		this.isReplied = isReplied;
		this.user = user;
		this.reply = reply;
	}

	public Consult(Date time, String content, Book book, int isReplied, User user, Reply reply) {
		super();
		this.time = time;
		this.content = content;
		this.book = book;
		this.isReplied = isReplied;
		this.user = user;
		this.reply = reply;
	}

	public Consult() {
		super();
	}

}

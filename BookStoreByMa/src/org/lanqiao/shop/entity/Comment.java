package org.lanqiao.shop.entity;

import java.util.Date;

import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.entity.User;

public class Comment {
	private int id;
	private Date time;
	private String content;
	private Book book;
	private int stars;
	private User user;

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

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Comment(int id, Date time, String content, Book book, int stars, User user) {
		super();
		this.id = id;
		this.time = time;
		this.content = content;
		this.book = book;
		this.stars = stars;
		this.user = user;
	}

	public Comment(Date time, String content, Book book, int stars, User user) {
		super();
		this.time = time;
		this.content = content;
		this.book = book;
		this.stars = stars;
		this.user = user;
	}

	public Comment() {
		super();
	}

}

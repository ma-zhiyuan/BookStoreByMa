package org.lanqiao.shop.entity;

import org.lanqiao.admin.entity.Book;

public class ShoppingItem {
	private int id;
	private Book book;
	private int count;
	private double itemPrice;
	private Order order;
	private Cart cart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public ShoppingItem(int id, Book book, int count, double itemPrice, Order order, Cart cart) {
		super();
		this.id = id;
		this.book = book;
		this.count = count;
		this.itemPrice = itemPrice;
		this.order = order;
		this.cart = cart;
	}

	public ShoppingItem(Book book, int count, double itemPrice, Order order, Cart cart) {
		super();
		this.book = book;
		this.count = count;
		this.itemPrice = itemPrice;
		this.order = order;
		this.cart = cart;
	}

	public ShoppingItem(int id, Book book, int count, double itemPrice, Cart cart) {
		super();
		this.id = id;
		this.book = book;
		this.count = count;
		this.itemPrice = itemPrice;
		this.cart = cart;
	}

	public ShoppingItem(int id, Book book, int count, double itemPrice, Order order) {
		super();
		this.id = id;
		this.book = book;
		this.count = count;
		this.itemPrice = itemPrice;
		this.order = order;
	}

	public ShoppingItem(int id, Book book, int count, double itemPrice) {
		super();
		this.id = id;
		this.book = book;
		this.count = count;
		this.itemPrice = itemPrice;
	}

	public ShoppingItem() {
		super();
	}

}

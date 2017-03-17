package org.lanqiao.shop.entity;

import java.util.List;

import org.lanqiao.admin.entity.User;

public class Cart {
	private int id;
	private User user;
	private double total;
	private List<ShoppingItem> items;

	public List<ShoppingItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingItem> items) {
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Cart(int id, User user, double total) {
		super();
		this.id = id;
		this.user = user;
		this.total = total;
	}

	public Cart() {
		super();
	}

	public Cart(User user, double total) {
		super();
		this.user = user;
		this.total = total;
	}

}

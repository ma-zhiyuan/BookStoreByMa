package org.lanqiao.shop.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanqiao.admin.entity.User;

public class Order {
	private int id;
	private User user;
	private Date time;
	private double total;
	private int isSend;
	private Address address;
	private int isCommented;
	private List<ShoppingItem> items = new ArrayList<ShoppingItem>();

	public static final int ISSEND_FALSE = 0;
	public static final int ISSEND_TRUE = 1;
	
	public static final int ISCOMMENTED_FALSE = 0;
	public static final int ISCOMMENTED_TRUE = 1;
	
	public int getIsCommented() {
		return isCommented;
	}

	public void setIsCommented(int isCommented) {
		this.isCommented = isCommented;
	}

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Order(int id, User user, Date time, double total, int isSend, Address address) {
		super();
		this.id = id;
		this.user = user;
		this.time = time;
		this.total = total;
		this.isSend = isSend;
		this.address = address;
	}

	public Order(User user, Date time, double total, int isSend, Address address) {
		super();
		this.user = user;
		this.time = time;
		this.total = total;
		this.isSend = isSend;
		this.address = address;
	}

	public Order() {
		super();
	}

}

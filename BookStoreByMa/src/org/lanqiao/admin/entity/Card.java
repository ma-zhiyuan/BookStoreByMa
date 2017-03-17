package org.lanqiao.admin.entity;

import java.util.Date;

public class Card {
	private String id;
	private double balance;
	private Date time;
	private int isUsed;
	private User user;
	
	public static final int ISUSED_TRUE = 1;
	public static final int ISUSED_FALSE = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Card(String id, double balance, Date time, int isUsed, User user) {
		super();
		this.id = id;
		this.balance = balance;
		this.time = time;
		this.isUsed = isUsed;
		this.user = user;
	}

	public Card() {
		super();
	}

}

package org.lanqiao.shop.entity;

import org.lanqiao.admin.entity.User;

public class Address {
	private int id;
	private User user;
	private String addr;
	private String consignee;
	private String tel;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Address(int id, User user, String addr, String consignee, String tel) {
		super();
		this.id = id;
		this.user = user;
		this.addr = addr;
		this.consignee = consignee;
		this.tel = tel;
	}

	public Address(User user, String addr, String consignee, String tel) {
		super();
		this.user = user;
		this.addr = addr;
		this.consignee = consignee;
		this.tel = tel;
	}

	public Address() {
		super();
	}

}

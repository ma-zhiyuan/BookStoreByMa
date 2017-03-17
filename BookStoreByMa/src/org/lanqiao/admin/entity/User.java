package org.lanqiao.admin.entity;

import java.util.ArrayList;
import java.util.List;

import org.lanqiao.shop.entity.Address;
import org.lanqiao.shop.entity.Cart;
import org.lanqiao.shop.entity.Order;

public class User {
	private int id;
	private String name;
	private String password;
	private double balance;
	private String profile;
	private int gender;
	private int age;
	private Cart cart;
	private String email;
	private List<Order> orders = new ArrayList<Order>();

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	private List<Address> addrs = new ArrayList<Address>();

	public List<Address> getAddrs() {
		return addrs;
	}

	public void setAddrs(List<Address> addrs) {
		this.addrs = addrs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public User(int id, String name, String password, double balance, String profile, int gender, int age, Cart cart) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.profile = profile;
		this.gender = gender;
		this.age = age;
		this.cart = cart;
	}

	public User() {
		super();
	}

	public User(String name, String password, double balance, String profile, int gender, int age, Cart cart) {
		super();
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.profile = profile;
		this.gender = gender;
		this.age = age;
		this.cart = cart;
	}

}

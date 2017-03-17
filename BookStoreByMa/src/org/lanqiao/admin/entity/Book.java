package org.lanqiao.admin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.lanqiao.shop.entity.Comment;
import org.lanqiao.shop.entity.Consult;

public class Book {
	private int id;
	private String name;
	private double price;
	private String publishCorp;
	private int stock;
	private int sold;
	private int isOnSale;
	private String details;
	private String surface;
	private String type;
	private Date onSaleTime;
	
	//常量来标记特殊的情况
	public static final int ISONSALE_TRUE = 1;
	
	public static final int ISONSALE_FALSE = 0;
	
	//排序

	// 评论
	private List<Comment> comments = new ArrayList<Comment>();

	// 咨询
	private List<Consult> consults = new ArrayList<Consult>();

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublishCorp() {
		return publishCorp;
	}

	public void setPublishCorp(String publishCorp) {
		this.publishCorp = publishCorp;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(int isOnSale) {
		this.isOnSale = isOnSale;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOnSaleTime() {
		return onSaleTime;
	}

	public void setOnSaleTime(Date onSaleTime) {
		this.onSaleTime = onSaleTime;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Consult> getConsults() {
		return consults;
	}

	public void setConsults(List<Consult> consults) {
		this.consults = consults;
	}

	public Book(int id, String name, double price, String publishCorp, int stock, int sold, int isOnSale,
			String details, String surface, String type, Date onSaleTime) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.publishCorp = publishCorp;
		this.stock = stock;
		this.sold = sold;
		this.isOnSale = isOnSale;
		this.details = details;
		this.surface = surface;
		this.type = type;
		this.onSaleTime = onSaleTime;
	}

	public Book() {
		super();
	}

	public Book(String name, double price, String publishCorp, int stock, int sold, int isOnSale, String details,
			String surface, String type, Date onSaleTime) {
		super();
		this.name = name;
		this.price = price;
		this.publishCorp = publishCorp;
		this.stock = stock;
		this.sold = sold;
		this.isOnSale = isOnSale;
		this.details = details;
		this.surface = surface;
		this.type = type;
		this.onSaleTime = onSaleTime;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", publishCorp=" + publishCorp + ", stock="
				+ stock + ", sold=" + sold + ", isOnSale=" + isOnSale + ", details=" + details + ", surface=" + surface
				+ ", type=" + type + ", onSaleTime=" + onSaleTime + "]";
	}

}

package org.lanqiao.shop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.lanqiao.admin.dao.UserDao;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.service.BookService;
import org.lanqiao.admin.service.UserService;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.shop.dao.OrderDao;
import org.lanqiao.shop.dao.ShoppingItemDao;
import org.lanqiao.shop.entity.Address;
import org.lanqiao.shop.entity.Cart;
import org.lanqiao.shop.entity.Order;
import org.lanqiao.shop.entity.ShoppingItem;

public class OrderService {

	public String add(User user, int bookId, int count) {
		BookService bs = new BookService();
		Book book = bs.getById(bookId, false);
		// 先看看有没有这个购物项目
		List<ShoppingItem> items = user.getCart().getItems();
		//遍历当前购物车看是否已经有了这本书，如果有，只需要加上相应的数量就可以了。
		for (int i = 0; i < items.size(); i++) {
			ShoppingItem si = items.get(i);
			int itemId = si.getBook().getId();
			if (itemId == bookId) {
				// 如果有，就加一个
				ShoppingItemService sis = new ShoppingItemService();
				// 更新数据库并获取得更新后的购物项目
				if(book.getStock()<(count+si.getCount())){
					return "库存不足！";
				}
				si.setCount(si.getCount() + count);
				si.setItemPrice(si.getItemPrice() + si.getBook().getPrice() * count);
				int result = sis.update(si);
				if (result > 0) {
					Cart cart = user.getCart();
					cart.setTotal(cart.getTotal() + si.getBook().getPrice() * count);
					CartService cs = new CartService();
					result = cs.update(cart);
				}
				return "增加成功！";
			}
		}
		// 如果没有，就创建一个
		//如果库存不足
		if(book.getStock()<count){
			return "库存不足！";
		}
		ShoppingItem si = new ShoppingItem(book, count, book.getPrice()*count, null, user.getCart());
		ShoppingItemDao sid = new ShoppingItemDao();
		//程序中的数据也需更新。
		int i = sid.add(si);
		if(i>0){
			user.getCart().getItems().add(si);
			user.getCart().setTotal(user.getCart().getTotal()+book.getPrice()*count);
			CartService cs = new CartService();
			cs.update(user.getCart());
			return "增加成功！";
		}else{
			return "增加失败！";
		}
	}

	public String confirm_order(User u, Address add) {
		//更新用户的余额
		if(u.getBalance()<u.getCart().getTotal()){
			return "余额不足！";
		}
		//查看库存，（需要将事务的隔离级别设为最高，略）
		boolean enoughStock = this.enoughStock(u.getCart());
		if(!enoughStock){
			return "库存不足";
		}
		
		Order order = new Order(u, new Date(), u.getCart().getTotal(), Order.ISSEND_FALSE, add);
		//去数据库增加车个订单
		OrderDao dao = new OrderDao();
		int orderId = dao.add(order);
		//更新购物项，让他们从购物车移移，指到订单
		this.cartToOrder(u.getCart().getItems(),orderId);
		//更新用户余额
		u.setBalance(u.getBalance()-u.getCart().getTotal());
		UserDao udao = new UserDao();
		udao.update(u);
		//图书库存和销量
		this.updateStockAndSold(u.getCart());
		//从程序中登录的用户的购物车中移除所有购物项。
		u.getCart().setItems(null);
		u.getCart().setTotal(0);
		new CartService().update(u.getCart());
		return "提交成功！";
	}
	
	public void cartToOrder(List<ShoppingItem> items,int orderId) {
		ShoppingItemDao dao = new ShoppingItemDao();
		for(ShoppingItem si: items){
			dao.cartToOrder(si,orderId);
		}
	}

	public boolean enoughStock(Cart c){
		ShoppingItemDao dao = new ShoppingItemDao();
		List<ShoppingItem> items = c.getItems();
		for(ShoppingItem si:items){
			boolean isEnough = dao.checkStock(si);
			//如果其中一个是false，就返回false
			if(!isEnough){
				return isEnough;
			}
		}
		return true;
	}
	
	public void updateStockAndSold(Cart c){
		BookService bs = new BookService();
		List<ShoppingItem> items = c.getItems();
		for(ShoppingItem si:items){
			Book book = si.getBook();
			int count = si.getCount();
			book.setStock(book.getStock()-count);
			book.setSold(book.getSold()+count);
			bs.update(book);
		}
	}

	/**
	 * 获取用户所的有订单
	 * @param user
	 * @return
	 */
	public List<Order> getAll(User user) {
		OrderDao dao = new OrderDao();
		//获取所有的订单
		List<Order> orders = dao.getAllByUserId(user.getId());
		//为所有的订单获取地址信息
		this.setAddress(orders);
		//获取所有的交易项
		this.setItems(orders);
		return orders;
	}

	/**
	 * 为每个订单获取交易项
	 * @param orders
	 */
	public void setItems(List<Order> orders) {
		ShoppingItemService sid = new ShoppingItemService();
		for(Order o:orders){
			sid.setItems(o);
		}
	}

	/**
	 * 为所有的订单获取地址
	 * @param orders
	 */
	public void setAddress(List<Order> orders) {
		AddressService as = new AddressService();
		for(Order o: orders){
			Address addr = as.getByOrderId(o);
			o.setAddress(addr);
		}
	}

	public void update(Order order) {
		new OrderDao().update(order);
	}

	public List<Order> getAll() {
		List<Order> orders = new OrderDao().getAll();
		this.setAddress(orders);
		this.setItems(orders);
		this.setUser(orders);
		return orders;
	}

	public void setUser(List<Order> orders) {
		for(Order o:orders){
			UserService us  =new UserService();
			User u = us.getByOrderId(o.getId());
			o.setUser(u);
		}
	}

	public void send(int id) {
		new OrderDao().send(id);
	}
}

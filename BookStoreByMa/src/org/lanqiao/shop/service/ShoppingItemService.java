package org.lanqiao.shop.service;

import java.util.List;

import org.lanqiao.admin.dao.BookDao;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.shop.dao.ShoppingItemDao;
import org.lanqiao.shop.entity.Order;
import org.lanqiao.shop.entity.ShoppingItem;

public class ShoppingItemService {

	public List<ShoppingItem> getAll(int cartId) {
		ShoppingItemDao sid = new ShoppingItemDao();
		// 不带书对象的这个属性的
		List<ShoppingItem> items = sid.getAll(cartId);
		for (ShoppingItem item : items) {
			setBook(item);
		}
		return items;
	}

	public void setBook(ShoppingItem item) {
		// 获取这个item所对应的快的id
		ShoppingItemDao sid = new ShoppingItemDao();
		int bookId = sid.getBookId(item);
		// 通过book的id获取book对象
		BookDao bdao = new BookDao();
		Book book = bdao.getT(bookId);
		item.setBook(book);
	}

	public int update(ShoppingItem si) {
		return new ShoppingItemDao().update(si);
	}

	/**
	 * 为订单获取交易项
	 * @param o
	 */
	public void setItems(Order o) {
		ShoppingItemDao dao = new ShoppingItemDao();
		List<ShoppingItem> items = dao.getAllByOrderId(o.getId());
		for(ShoppingItem si:items){
			this.setBook(si);
		}
		o.setItems(items);
	}

}

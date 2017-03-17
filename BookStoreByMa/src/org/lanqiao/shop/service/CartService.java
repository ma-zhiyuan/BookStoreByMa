package org.lanqiao.shop.service;

import java.util.List;

import org.lanqiao.admin.dao.UserDao;
import org.lanqiao.admin.entity.User;
import org.lanqiao.shop.dao.CartDao;
import org.lanqiao.shop.entity.Cart;
import org.lanqiao.shop.entity.ShoppingItem;

public class CartService {

	public Cart getByUserId( int userId) {
		CartDao dao = new CartDao();
		Cart cart = dao.getByUserId(userId);
		setShoppingItems(cart);
		return cart;
	}

	public void setShoppingItems( Cart cart) {
		ShoppingItemService sis = new ShoppingItemService();
		List<ShoppingItem> items = sis.getAll(cart.getId());
		cart.setItems(items);
	}

	/**
	 * 给用户增加购物车
	 * @param conn
	 * @param id 用户的id
	 * @return
	 */
	public int addCart(User user) {
		Cart cart = new Cart(user, 0);
		CartDao dao = new CartDao();
		//创建一个购物车
		int i = dao.add(cart);
		//更新user 的cart_id列
		if(i>0){
			user.setCart(cart);
			UserDao userDao = new UserDao();
			userDao.update(user);
		}
		return i;
	}

	public int update( Cart cart) {
		return new CartDao().update(cart);
	}
}

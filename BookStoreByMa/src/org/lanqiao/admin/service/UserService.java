package org.lanqiao.admin.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.lanqiao.admin.dao.UserDao;
import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.shop.entity.Cart;
import org.lanqiao.shop.service.CartService;


public class UserService {

	/**
	 * 使用用户名和密码获取用户
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public User get(String name, String pwd) {
		UserDao dao = new UserDao();
		User u = dao.getT(name, pwd);
		if(u!=null){
			this.setCart(u);
		}
		return u;
	}

	public void setCart(User u) {
		CartService cs = new CartService();
		Cart cart = cs.getByUserId(u.getId());
		u.setCart(cart);
	}

	/**
	 * 验证用户名是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean verifyName(String name) {
		String n = new UserDao().getName(name);
		if (n == null) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unused")
	public int regist(String name, String pwd) {
		// 先增用户
		User u = new User(name, pwd, 0, null, 1, 0, null);
		UserDao dao = new UserDao();
		int result = dao.add(u);
		// 增加购物车
		CartService cs = new CartService();
		int i = cs.addCart(u);
		return result;
	}

	public String update(User user) {
		int i = new UserDao().update(user);
		if(i>0){
			return "更新成功。";
		}
		return "更新失败";
	}

	public User getByOrderId(int orderId) {
		return new UserDao().getByOrderId(orderId);
	}

	public int toBind(String name, String pwd, String openid) {
		//成功0，用户名不存在1，密码不正确2.账号已绑定过了3。
		boolean b = this.verifyName(name);
		//用户名不存在
		if(!b){
			return 1;
		}
		User u = this.get(name, pwd);
		//密码不正确
		if(u==null){
			return 2;
		}
		//查看当前的微信号是否绑定其它账号
		boolean b2 = this.verifyOpenId(openid);
		if(b2){
			return 3;
		}
		//输入的用户有是否被其它微信号绑定
		boolean b3 = this.isBind(u.getId());
		if(b3){
			return 4;
		}
		int i = this.bind(u.getId(),openid);
		if(i==1){
			return 0;
		}
		return 99;
	}

	public boolean isBind(int userId) {
		Integer id = new UserDao().getBindId(userId);
		if(id==null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 绑定用户的微信号
	 * @param id
	 * @param openid
	 * @return
	 */
	public int bind(int id, String openid) {
		return new UserDao().bind(id,openid);
	}

	/**
	 * 验证是否存在这样一个openid
	 * @param openid
	 * @return
	 */
	public boolean verifyOpenId(String openid) {
		String id = new UserDao().getOpenId(openid);
		if(id==null){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * @param openId
	 * @return
	 */
	public User getByOpenId(String openId) {
		User u =  new UserDao().getByOpenId(openId);
		if(u!=null){
			this.setCart(u);
		}
		return u;
	}
	
	public int registByEmail(String email, String pwd) {
		Connection conn;
		try {
			conn = MyUtils.ds.getConnection();
			//增加用户
			User u = new User(email, pwd, 0, null, 1, 0, null);
			u.setEmail(email);
			UserDao ud = new UserDao();
			int result = ud.add(u);
			//增加购物车
			CartService cs = new CartService();
			int i = cs.addCart(u);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

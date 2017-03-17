package org.lanqiao.shop.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.admin.entity.User;
import org.lanqiao.shop.entity.Address;
import org.lanqiao.shop.entity.Comment;
import org.lanqiao.shop.entity.Order;
import org.lanqiao.shop.entity.ShoppingItem;
import org.lanqiao.shop.service.AddressService;
import org.lanqiao.shop.service.CommentService;
import org.lanqiao.shop.service.OrderService;

public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3750951517670959846L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				try {
					String uri = request.getRequestURI();
					String methodName = uri.substring(uri.lastIndexOf("/") + 1);
					Method m = OrderServlet.class.getDeclaredMethod(methodName, HttpServletRequest.class,
							HttpServletResponse.class);
					m.invoke(this, request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
	}

	/**
	 * 下单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取图书的id
		String id = request.getParameter("id");
		int bookId = Integer.parseInt(id);
		// 从session中获取User对象
		Object userObj = request.getSession().getAttribute("user");
		User user = (User) userObj;
		OrderService os = new OrderService();
		String result = os.add(user, bookId, 1);
		request.setAttribute("msg", result);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	
	/**
	 * 去结算
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void to_confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.需要显示这个用户的所有的收货地址，让他选一个。
		User u = (User)request.getSession().getAttribute("user");
		//2.需要从Session中获取用户，然后获取用户所有的收货地址，放到session中
		AddressService as = new AddressService();
		List<Address> addrs = as.getAll(u);
		u.setAddrs(addrs);
		//3,返回确认订单页面
		response.sendRedirect(request.getContextPath()+"/loginjsp/to_confirm.jsp");
	}
	
	/**
	 * 获取所有订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		OrderService os = new OrderService();
		List<Order> orders = os.getAll(user);
		user.setOrders(orders);
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/loginjsp/showAll.jsp").forward(request, response);
	}
	/**
	 * 去评价
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void to_comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		User u = (User) request.getSession().getAttribute("user");
		List<Order> orders = u.getOrders();
		Order order = null;
		for(Order o:orders){
			if(o.getId()==Integer.parseInt(orderid)){
				order=o;
			}
		}
		if(order.getIsSend()==Order.ISSEND_FALSE){
			request.setAttribute("msg", "该订单还未发货。");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
		order.setUser(u);
		if(order.getIsCommented()==Order.ISCOMMENTED_TRUE){
			request.setAttribute("msg", "该订单已评价过了。");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
		request.getSession().setAttribute("order", order);
		request.getRequestDispatcher("/loginjsp/comment.jsp").forward(request, response);
	}
	
	/**
	 * 评价
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u =(User) request.getSession().getAttribute("user");
		//保存所有的星级，键是交易项，值是星级
		Map<Integer,Integer> stars = new HashMap<Integer, Integer>();
		//保存所有的评价，键是交易项id，值是评价内容
		Map<Integer,String> contents = new HashMap<Integer, String>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			if(name.startsWith("star")){
				String idStr = name.substring(name.indexOf("-")+1);
				int id = Integer.parseInt(idStr);
				String star = request.getParameter(name);
				int s = Integer.parseInt(star);
				stars.put(id, s);
			}else if(name.startsWith("content")){
				String idStr = name.substring(name.indexOf("-")+1);
				int id = Integer.parseInt(idStr);
				String content = request.getParameter(name);
				contents.put(id, content);
			}
		}
		//获取正在评价的订单
		Order order = (Order) request.getSession().getAttribute("order");
		Set<Integer> keys = stars.keySet();
		List<ShoppingItem> items = order.getItems();
		CommentService cs = new CommentService();
		for(ShoppingItem si:items){
			Comment comment = new Comment(new Date(), contents.get(si.getId()), si.getBook(), stars.get(si.getId()), u);
			cs.add(comment);
		}
		order.setIsCommented(Order.ISCOMMENTED_TRUE);
		OrderService os = new OrderService();
		os.update(order);
		response.sendRedirect(request.getContextPath()+"/success.jsp");
	}
	
	/**
	 * 确认订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void confirm_order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User)request.getSession().getAttribute("user");
		//获取请求参数，先获取，addr这个radio的值，判断是选择的现有的地址还是一个新地址
		String addrStr = request.getParameter("addr");
		//如果是一个新地址，则去数据加增加一条新的地址记录，
		Address add = null;
		if(addrStr.equals("other")){
			String consignee = request.getParameter("newconsignee");
			String tel = request.getParameter("newtel");
			String addr = request.getParameter("newaddr");
			add = new Address(u, addr, consignee, tel);
			AddressService as = new AddressService();
			as.add(add);
		//如果是现有的地址，则从session中获取该地址对象
		}else{
			int id = Integer.parseInt(addrStr);
			List<Address> addrs= u.getAddrs();
			for(Address a:addrs){
				if(a.getId()==id){
					add=a;
				}
			}
		}
		OrderService os = new OrderService();
		//确认订单
		String result = os.confirm_order(u,add);
		request.setAttribute("msg", result);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}

}

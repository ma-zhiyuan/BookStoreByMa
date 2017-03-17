package org.lanqiao.admin.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.admin.entity.Admin;
import org.lanqiao.admin.entity.Card;
import org.lanqiao.admin.service.AdminService;
import org.lanqiao.admin.service.CardService;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.shop.entity.Order;
import org.lanqiao.shop.service.OrderService;

public class AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 481772726921774255L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			String methodName = uri.substring(uri.lastIndexOf("/") + 1);
			Method m = AdminServlet.class.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void addCards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/ms-download");
		response.setHeader("Content-disposition", "attachment;filename=cards.txt");
		String number = request.getParameter("number");
		String balance = request.getParameter("balance");
		CardService cs = new CardService();
		List<Card> cards = cs.add(Integer.parseInt(number),Double.parseDouble(balance));
		ServletOutputStream os = response.getOutputStream();
		for(Card c:cards){
			String str = "会员卡号："+c.getId()+"\n";
			os.write(str.getBytes());
		}
		os.close();
	}
	
	/**
	 * 后台登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		pwd = MyUtils.md5(pwd);
		Admin admin = new AdminService().getByNameAndPwd(name,pwd);
		request.getSession().setAttribute("admin", admin);
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	
	/**
	 * 后台查看所有订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void allOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService os = new OrderService();
		List<Order> orders = os.getAll();
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/admin/allorders.jsp").forward(request, response);
	}
	
	/**
	 * 发货
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		int id = Integer.parseInt(orderid);
		OrderService os = new OrderService();
		os.send(id);
		response.sendRedirect(request.getContextPath()+"/admin/success.jsp");
	}

}

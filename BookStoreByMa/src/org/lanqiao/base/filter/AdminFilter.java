package org.lanqiao.base.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.shop.service.ConsultService;

public class AdminFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object admin = request.getSession().getAttribute("admin");
		if(admin==null){
			request.setAttribute("msg", "您还没有登录&nbsp;&nbsp;<a href=\"admin.jsp\">去登录</a>");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
		ConsultService cs = new ConsultService();
		int count  = cs.getUnrepliedConsultsCount();
		request.setAttribute("unrepliedcount", count);
		chain.doFilter(request, response);
	}

}

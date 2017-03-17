package org.lanqiao.base.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFile extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object user = request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("msg", "您还没有登录");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}
}

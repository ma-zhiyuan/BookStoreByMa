package org.lanqiao.weixin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5592958801002345598L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		System.out.println("code:"+code);
		System.out.println("id:"+id);
		System.out.println("state:"+state);
	}

}

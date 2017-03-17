package org.lanqiao.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.weixin.service.WxService;
import org.lanqiao.weixin.utils.WxUtils;

public class CoreServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8915889559605864605L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("准备接入");
		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		if(WxUtils.verify(signature, timestamp, nonce)){
			out.write(echostr);
			System.out.println("接入成功");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServletInputStream is = request.getInputStream();
		//获取消息的Map
		Map<String, String> msgMap = WxUtils.parseMsg(is);
		Connection conn=null;
		String respMsg = null;
		try {
			conn = MyUtils.ds.getConnection();
			conn.setAutoCommit(false);
			MyUtils.conns.set(conn);
			//调用业务方法
			respMsg = WxService.getResponseMsg(msgMap);
			System.out.println(respMsg);
			MyUtils.conns.remove();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			MyUtils.conns.remove();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		out.write(respMsg);
		out.flush();
		out.close();
	}

}

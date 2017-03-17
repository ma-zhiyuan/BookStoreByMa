package org.lanqiao.base.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.admin.util.MyUtils;

public class TransactionFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1.先获取一个连接
		Connection conn = null;
		try {
			conn = MyUtils.ds.getConnection();
			conn.setAutoCommit(false);
			//2.存到一个地方
//			request.setAttribute("conn", conn);
			//存入第三方的一仓库
			//通过ThreadLocal可以将一个数据与当前线程绑定。
			MyUtils.conns.set(conn);
			
			//doFilter
			chain.doFilter(request, response);

			//解除绑定
			MyUtils.conns.remove();
			//如果不出异常就提交
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				//出异常就回滚
				System.out.println("出异常回滚");
				MyUtils.conns.remove();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			response.sendRedirect(request.getContextPath()+"/fail.jsp");
		}
	}

}

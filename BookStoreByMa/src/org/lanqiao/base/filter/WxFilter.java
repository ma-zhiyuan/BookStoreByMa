package org.lanqiao.base.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.service.UserService;
import org.lanqiao.weixin.utils.WxUtils;

import net.sf.json.JSONObject;

public class WxFilter extends HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object u = request.getSession().getAttribute("user");
		if(u==null){
			String code = request.getParameter("code");
			if(code!=null){
				//调用方法能过code获取jsonObject
				JSONObject jsonObject = WxUtils.getAccessTokenOpenId(code);
				String openid = (String) jsonObject.get("openid");
				User user = new UserService().getByOpenId(openid);
				if(user!=null){
					request.getSession().setAttribute("user", user);
				}
				chain.doFilter(request, response);
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

}

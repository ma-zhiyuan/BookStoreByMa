package org.lanqiao.shop.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.service.CardService;
import org.lanqiao.admin.service.UserService;
import org.lanqiao.admin.util.MyUtils;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			String methodName = uri.substring(uri.lastIndexOf("/") + 1);
			Method m = UserServlet.class.getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		pwd = MyUtils.md5(pwd);
		UserService us = new UserService();
		User u = us.get(name, pwd);
		if (u != null) {
			request.getSession().setAttribute("user", u);
			response.sendRedirect(request.getContextPath() + "/success.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/fail.jsp");
		}

	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath() + "/success.jsp");

	}
	
	/**
	 * 用户充值
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void recharge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		String cardid = request.getParameter("cardid");
		CardService cs = new CardService();
		String result = cs.recharge(cardid,u);
		request.setAttribute("msg", result);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modifypwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User)request.getSession().getAttribute("user");
		String oldpwd = request.getParameter("oldpwd");
		String pwd = request.getParameter("password");
		String pwd2 = request.getParameter("password2");
		oldpwd = MyUtils.md5(oldpwd);
		pwd = MyUtils.md5(pwd);
		pwd2 = MyUtils.md5(pwd2);
		if(!oldpwd.equals(u.getPassword())){
			request.setAttribute("msg", "原密码不正确。");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
		if(!pwd.equals(pwd2)){
			request.setAttribute("msg", "两次密码不一致。");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		}
		u.setPassword(pwd);
		UserService us = new UserService();
		us.update(u);
		request.setAttribute("msg", "修改成功");
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
	}
	
	/**
	 * 用户更新个人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modifyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream is = request.getInputStream();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			String gender = null;
			String age=null;
			String fileName = null;
			String dir = null;
			String path = null;
			for(FileItem fi:items){
				String fieldName = fi.getFieldName();
				if(fi.isFormField()){
					if(fieldName.equals("gender")){
						gender=fi.getString();
					}else if(fieldName.equals("age")){
						age=fi.getString();
					}
				}else{
					InputStream iis = fi.getInputStream();
					// 获取文件名
					fileName = fi.getName();
					// 目标文件夹
					dir = "bookpic";
					// 找到当前项目的webRoot文件夹
					String rep = request.getServletContext().getRealPath("/" + dir);
					// 解决文件名冲突的问题
					fileName = System.currentTimeMillis() + (int) (Math.random() * 1000) + fileName;
					// 输出流的全路径，包括文件名。
					path = rep + "/" + fileName;
					// 输出流
					OutputStream os = new FileOutputStream(path);
					byte[] b = new byte[1024 * 1024 * 10];
					int len = 0;
					while ((len = iis.read(b)) != -1) {
						os.write(b, 0, len);
					}
					os.close();
					iis.close();
				}
			}
		User user = (User)request.getSession().getAttribute("user");
		user.setAge(Integer.parseInt(age));
		user.setGender(Integer.parseInt(gender));
		user.setProfile(dir+"/"+fileName);
		UserService us = new UserService();
		String result = us.update(user);
		request.setAttribute("msg", result);
		request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		is.close();
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("regname");
		String pwd = request.getParameter("regpassword");
		//加密
		pwd = MyUtils.md5(pwd);
		//判断用户输入的是否是邮箱，如果是邮箱则用邮箱注册；如果是用户名的话就用用户名注册
		String reg="\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";
		boolean flag = name.matches(reg);
		UserService us = new UserService();
		//是一个邮箱
		if(flag){
			String emailStr = name;
			//发邮件
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.163.com");
			email.setSSLOnConnect(true);
			email.setSmtpPort(465);
			//设置用户
			email.setAuthentication("maazhiyuan", "mazhiyuan1202");
			try {
				//设置从哪个用户发出
				email.setFrom("maazhiyuan@163.com","马志远","utf-8");
				//设置接受邮件的邮箱
				email.addTo(emailStr);//mazhiyuan@163.com
				//邮件主题
				email.setSubject("邮箱确认");
				//获取验证码
				String cap = String.valueOf(MyUtils.getRandom());
				HttpSession session = request.getSession();
				session.setAttribute("capcha", cap);
				session.setAttribute("pwd", pwd);
				session.setAttribute("email", emailStr);
				//邮件内容
				String content = "验证码："+cap;
				email.setMsg(content);
				email.send();
				//跳到指定页面，输入验证码
				response.sendRedirect(request.getContextPath()+"/ConfirmCapcha.jsp");
			} catch (EmailException e) {
				e.printStackTrace();
			}
			
			//i=us.registByEmail(email,pwd);
		}else{
			int i = us.regist(name,pwd);
			if(i>0){
				request.getSession().setAttribute("user", us.get(name, pwd));
				response.sendRedirect(request.getContextPath()+"/success.jsp");
			}else{
				response.sendRedirect(request.getContextPath()+"/fail.jsp");
			}
		}
	}

	/**
	 * 验证用户名是否存
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verifyName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		UserService us = new UserService();
		boolean isRegisted = us.verifyName(name);
		String str = "网络异常，请重试。";
		if (isRegisted) {
			str = "用户名已占用。";
		} else {
			str = "";
		}
		PrintWriter out = response.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}
	
	public void confirmEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String capcha = request.getParameter("capcha");
		Object attribute = request.getSession().getAttribute("capcha");
		String cap = (String)attribute;
		if(capcha.equals(cap)){
			UserService us = new UserService();
			String pwd = (String)request.getSession().getAttribute("pwd");
			String email = (String)request.getSession().getAttribute("email");
			int i = us.registByEmail(email, pwd);
			if(i>0){
				request.getSession().setAttribute("user", us.get(email, pwd));
				response.sendRedirect(request.getContextPath()+"/success.jsp");
			}else{
				response.sendRedirect(request.getContextPath()+"/fail.jsp");
			}
		}else{
			response.sendRedirect(request.getContextPath()+"/fail.jsp");
		}
	}

}

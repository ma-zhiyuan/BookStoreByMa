package org.lanqiao.admin.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lanqiao.admin.entity.Admin;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.entity.Reply;
import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.service.BookService;
import org.lanqiao.admin.service.ReplyService;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.bean.BookCretiera;
import org.lanqiao.base.bean.Page;
import org.lanqiao.shop.entity.Consult;
import org.lanqiao.shop.service.ConsultService;

public class BookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6864334741187931173L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			String methodName = uri.substring(uri.lastIndexOf("/") + 1);
			Method m = BookServlet.class.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}  
	}

	/**
	 * 前台查看所有书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword") == null ? "" : request.getParameter("keyword");
		String minprice = request.getParameter("minprice") == null ? "0" : request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice") == null ? "" + 999999 : request.getParameter("maxprice");
		String orderby = request.getParameter("orderby") == null ? "1" : request.getParameter("orderby");
		String pageNo = request.getParameter("pageNo") == null ? "1" : request.getParameter("pageNo");
		minprice = minprice.equals("") ? "0" : minprice;
		maxprice = maxprice.equals("") ? "999999" : maxprice;
		pageNo = pageNo.equals("") ? "1" : pageNo;
		// 封装一个查询条件的对象
		BookCretiera bc = new BookCretiera(keyword, Double.parseDouble(minprice), Double.parseDouble(maxprice),
				Integer.parseInt(pageNo), Integer.parseInt(MyUtils.config.getProperty("pageSize")),
				Integer.parseInt(orderby));
		// 设置单页容量
		bc.setPageSize(Integer.parseInt(MyUtils.config.getProperty("pageSize")));
		BookService bs = new BookService();
		Page<Book> books = bs.getAll(bc);
		request.setAttribute("books", books);
		request.getRequestDispatcher("/view/listAll.jsp").forward(request, response);
	}

	/**
	 * 后台查看所有书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void adminAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = new BookService();
		List<Book> books = bs.getAll();
		request.setAttribute("books", books);
		request.getRequestDispatcher("/admin/listAll.jsp").forward(request, response);
	}
	
	/**
	 * 获取所有未回复的咨询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getUnrepliedConults(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConsultService cs = new ConsultService();
		List<Consult> consults = cs.getUnrepliedConsults();
		request.setAttribute("consults", consults);
		request.getRequestDispatcher("/admin/reply.jsp").forward(request, response);
	}

	/**
	 * 查看图书详情
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void bookDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		BookService bs = new BookService();
		int id = Integer.parseInt(idStr);
		Book book = bs.getById(id, true);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/view/book-detail.jsp").forward(request, response);
	}

	/**
	 * 后台增加一本书
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream is = request.getInputStream();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(request);
			String dir = null;
			String fileName = null;
			String id = null;
			String name = null;
			String price = null;
			String corp = null;
			String stock = null;
			String type = null;
			String path = null;
			String details = null;
			// 遍历FileItem的集合
			for (FileItem fi : items) {
				// 判断是一个普通的表单还是一个文件
				// 是一个普通的表单
				if (fi.isFormField()) {
					String fieldName = fi.getFieldName();
					if (fieldName.equals("bookname")) {
						name = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("bookprice")) {
						price = fi.getString();
					} else if (fieldName.equals("corp")) {
						corp = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("stock")) {
						stock = fi.getString();
					} else if (fieldName.equals("type")) {
						type = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("details")) {
						details = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					} else if (fieldName.equals("id")) {
						id = new String(fi.getString().getBytes("iso-8859-1"), "utf-8");
					}
					// 是一个文件
				} else {
					// 获取输入流
					InputStream is2 = fi.getInputStream();
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
					while ((len = is2.read(b)) != -1) {
						os.write(b, 0, len);
					}
					os.close();
					is2.close();
				}
			}
			BookService bs = new BookService();
			// 新增
			if (id == null) {
				// 创建Book对象
				Book book = new Book(name, Double.parseDouble(price), corp, Integer.parseInt(stock), 0,
						Book.ISONSALE_TRUE, details, dir + "/" + fileName, type, new Date());
				// 往数据库中插入数据
				int i = bs.add(book);
				if (i > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/success.jsp");
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/fail.jsp");
				}
				// 更新
			} else {
				Book book = new Book(Integer.parseInt(id), name, Double.parseDouble(price), corp,
						Integer.parseInt(stock), 0, 1, details, dir + "/" + fileName, type, new Date());
				int i = bs.update(book);
				if (i > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/success.jsp");
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/fail.jsp");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		is.close();

	}

	/**
	 * 下架
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void down(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		BookService bs = new BookService();
		int i = bs.down(id);
		if (i > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/success.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/fail.jsp");
		}
	}

	/**
	 * 上架
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void up(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		BookService bs = new BookService();
		int i = bs.up(id);
		if (i > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/success.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/fail.jsp");
		}
	}

	/**
	 * 后台更新图书时获取图书以回显
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void to_update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		BookService bs = new BookService();
		Book book = bs.getById(id, false);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/admin/addbook.jsp").forward(request, response);
	}
	
	/**
	 * 发表咨询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void consult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		String content = request.getParameter("content");
		User u = (User) request.getSession().getAttribute("user");
		BookService bs = new BookService();
		Book book = bs.getById(Integer.parseInt(bookid), false);
		Consult consult = new Consult(new Date(), content, book, Consult.ISREPLIED_FALSE, u, null);
		ConsultService cs = new ConsultService();
		cs.add(consult);
		response.sendRedirect(request.getContextPath()+"/success.jsp");
	}
	
	/**
	 * 回复咨询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void reply(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		String consultId = request.getParameter("consultId");
		String reply = request.getParameter("reply");
		Consult consult = new Consult();
		consult.setId(Integer.parseInt(consultId));
		Reply rep = new Reply(new Date(), reply, admin, consult);
		ReplyService rs = new ReplyService();
		rs.add(rep);
		ConsultService cs = new ConsultService();
		cs.replied(consultId,rep.getId());
		response.sendRedirect(request.getContextPath()+"/admin/success.jsp");
	}
}

package org.lanqiao.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.admin.entity.Card;
import org.lanqiao.admin.service.CardService;
import org.lanqiao.admin.util.MyUtils;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CardServlet extends HttpServlet {
	private static final long serialVersionUID = 91731485905917212L;


	public CardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String number = request.getParameter("numberqr");
		String balance = request.getParameter("balanceqr");
		CardService cs = new CardService();
		List<Card> cards = cs.add(1,100);
		String content = "会员卡\n";
		for(Card c:cards){
			content = "会员卡号："+c.getId()+"\n";
		}
		System.out.println(number+balance+content);
		MyUtils.encoderQRCoder(content, response); 
		request.getRequestDispatcher("Card.jsp").forward(request, response);
	}

}

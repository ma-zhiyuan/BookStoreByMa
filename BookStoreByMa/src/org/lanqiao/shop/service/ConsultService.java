package org.lanqiao.shop.service;

import java.util.List;

import org.lanqiao.admin.dao.ReplyDao;
import org.lanqiao.admin.dao.UserDao;
import org.lanqiao.admin.entity.Book;
import org.lanqiao.admin.entity.Reply;
import org.lanqiao.admin.entity.User;
import org.lanqiao.shop.dao.ConsultDao;
import org.lanqiao.shop.entity.Consult;

public class ConsultService {

	public List<Consult> getAll(int bookId) {
		ConsultDao cdao = new ConsultDao();
		List<Consult> consults = cdao.getById(bookId);
		// 设置用户
		for (Consult c : consults) {
			this.setUser(c);
		}
		// 设置回复
		for (Consult c : consults) {
			this.setReply(c);
		}
		return consults;
	}

	public void setReply(Consult c) {
		if (c.getIsReplied() == Consult.ISREPLIED_TRUE) {
			ConsultDao dao = new ConsultDao();
			int replyId = dao.getReplyId(c);

			ReplyDao rdao = new ReplyDao();
			// 获取不带管理员的回复
			Reply reply = rdao.getT(replyId);
			c.setReply(reply);
		}
	}

	public void setUser(Consult c) {
		ConsultDao dao = new ConsultDao();
		int userId = dao.getUserId(c);

		// 获取用户
		UserDao uDao = new UserDao();
		User user = uDao.getT(userId);
		c.setUser(user);

	}

	public void add(Consult consult) {
		new ConsultDao().add(consult);
	}

	public List<Consult> getUnrepliedConsults() {
		List<Consult> consults = new ConsultDao().getUnreplied();
		for(Consult c:consults){
			this.setUser(c);
			this.setBook(c);
		}
		return consults;
	}

	public void setBook(Consult c) {
		Book book = new ConsultDao().getBook(c.getId());
		c.setBook(book);
	}

	public int getUnrepliedConsultsCount() {
		return new ConsultDao().getUnrepliedCount();
	}

	public void replied(String consultId,int replyId) {
		new ConsultDao().replied(consultId,replyId);
	}

}

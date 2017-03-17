package org.lanqiao.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanqiao.admin.dao.CardDao;
import org.lanqiao.admin.entity.Card;
import org.lanqiao.admin.entity.User;
import org.lanqiao.admin.util.MyUtils;

public class CardService {

	public String recharge(String cardid,User u) {
		String str = "充值成功。";
		CardDao dao = new CardDao();
		//获取会员卡
		Card card = dao.getT(cardid);
		if(card==null){
			return "会员卡不存在";
		}
		if(card.getIsUsed()==Card.ISUSED_TRUE){
			return "该会卡已失效";
		}
		//充值
		u.setBalance(u.getBalance()+card.getBalance());
		card.setBalance(0);
		card.setTime(new Date());
		card.setIsUsed(Card.ISUSED_TRUE);
		card.setUser(u);
		
		UserService us = new UserService();
		us.update(u);
		this.update(card);
		return str;
	}

	public void update(Card card) {
		new CardDao().update(card);
	}

	public List<Card> add(int num, double balance) {
		List<Card> cards = new ArrayList<Card>();
		Card card = null;
		CardDao dao = new CardDao();
		for(int i=0;i<num;i++){
			String id = MyUtils.md5((int)(Math.random()*1000)+num+balance+new Date().toString());
			card = new Card(id, balance, new Date(), Card.ISUSED_FALSE, null);
			dao.add(card);
			cards.add(card);
		}
		return cards;
	}

}

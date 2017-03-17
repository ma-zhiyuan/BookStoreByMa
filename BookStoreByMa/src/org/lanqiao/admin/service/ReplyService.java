package org.lanqiao.admin.service;

import org.lanqiao.admin.dao.ReplyDao;
import org.lanqiao.admin.entity.Reply;

public class ReplyService {

	public void add(Reply rep) {
		new ReplyDao().add(rep);
	}

}

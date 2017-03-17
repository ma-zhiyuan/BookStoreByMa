package org.lanqiao.admin.service;

import org.lanqiao.admin.dao.AdminDao;
import org.lanqiao.admin.entity.Admin;

public class AdminService {

	public Admin getByNameAndPwd(String name, String pwd) {
		return new AdminDao().get(name, pwd);
	}

}

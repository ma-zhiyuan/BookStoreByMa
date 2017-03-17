package org.lanqiao.admin.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lanqiao.admin.entity.Admin;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;

public class AdminDao extends BaseDao<Admin> implements Dao<Admin> {

	@Override
	public Admin getT(int id) {
		return null;
	}

	@Override
	public List<Admin> getAll(Object... args) {
		return null;
	}

	@Override
	public int update(Admin t) {
		return 0;
	}

	@Override
	public int delete(Admin t) {
		return 0;
	}

	@Override
	public int delete(int id) {
		return 0;
	}

	@Override
	public int add(Admin t) {
		return 0;
	}

	public Admin get(String name, String pwd) {
		Connection conn = MyUtils.conns.get();
		Admin admin;
		try {
			String sql = "select id,name,password from admins where name = ? and password = ?";
			BeanHandler<Admin> rsh = new BeanHandler<Admin>(Admin.class);
			admin = MyUtils.qr.query(conn, sql, rsh, name,pwd);
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}

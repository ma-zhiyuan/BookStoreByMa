package org.lanqiao.shop.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;
import org.lanqiao.shop.entity.Address;

public class AddressDao extends BaseDao<Address> implements Dao<Address> {

	@Override
	public Address getT(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAll(Object... args) {
		return null;
	}
	
	public List<Address> getByUserId(int userId) {
		List<Address> addrs;
		try {
			String sql = "select id, addr,consignee,tel from addresses where user_id = ?";
			addrs = this.getAll(sql, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return addrs;
	}

	@Override
	public int update(Address t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Address t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Address t) {
		int i = 0;
		Connection conn = MyUtils.conns.get();
		int id = this.getId();
		t.setId(id);
		try {
			String sql = "insert into addresses values (?,?,?,?,?)";
			i=MyUtils.qr.update(conn, sql, t.getId(),t.getUser().getId(),t.getAddr(),t.getConsignee(),t.getTel());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return i;
	}
	
	public int getId(){
		int b;
		try {
			String sql = "select Max(id) from addresses ";
			Object e = this.getE(sql);
			b = (Integer)e+1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return b;
	}

	public Address getByOrderId(int orderId) {
		Address addr = null;;
		try {
			String sql = "select id,addr,consignee,tel from addresses where id = ("
					+ "select address_id from orders where id = ?)";
			addr = this.get(sql, orderId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return addr;
	}
}

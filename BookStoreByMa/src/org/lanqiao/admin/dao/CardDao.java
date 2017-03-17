package org.lanqiao.admin.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lanqiao.admin.entity.Card;
import org.lanqiao.admin.util.MyUtils;
import org.lanqiao.base.dao.BaseDao;
import org.lanqiao.base.dao.Dao;

public class CardDao extends BaseDao<Card> implements Dao<Card> {

	public Card getT(String cardid) {
		Connection conn = MyUtils.conns.get();
		Card card;
		try {
			String sql = "select id,balance,time,isused from cards where id = ?";
			BeanHandler<Card> rsh = new BeanHandler<Card>(Card.class);
			card = MyUtils.qr.query(conn, sql, rsh, cardid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return card;
	}

	@Override
	public List<Card> getAll(Object... args) {
		return null;
	}

	@Override
	public int update(Card t) {
		Connection conn = MyUtils.conns.get();
		int i = 0;
		try {
			String sql = "update cards set balance=?,time=?,isused=?, user_id=? where id = ?";
			i = MyUtils.qr.update(conn, sql, t.getBalance(),new java.sql.Date(t.getTime().getTime()),t.getIsUsed(),t.getUser().getId(),t.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return i;
	}

	@Override
	public int delete(Card t) {
		return 0;
	}

	@Override
	public int delete(int id) {
		return 0;
	}

	@Override
	public int add(Card t) {
		Connection conn = MyUtils.conns.get();
		try {
			String sql = "insert into cards values (?,?,?,?,null)";
			int i = MyUtils.qr.update(conn, sql, t.getId(),t.getBalance(),new java.sql.Date(t.getTime().getTime()),t.getIsUsed());
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Card getT(int id) {
		return null;
	}

}

package org.lanqiao.admin.entity;

import java.util.Date;

import org.lanqiao.shop.entity.Consult;

public class Reply {
	private int id;
	private Date time;
	private String content;
	private Admin admin;
	private Consult consult;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Consult getConsult() {
		return consult;
	}

	public void setConsult(Consult consult) {
		this.consult = consult;
	}

	public Reply(int id, Date time, String content, Admin admin, Consult consult) {
		super();
		this.id = id;
		this.time = time;
		this.content = content;
		this.admin = admin;
		this.consult = consult;
	}

	public Reply(Date time, String content, Admin admin, Consult consult) {
		super();
		this.time = time;
		this.content = content;
		this.admin = admin;
		this.consult = consult;
	}

	public Reply() {
		super();
	}

}

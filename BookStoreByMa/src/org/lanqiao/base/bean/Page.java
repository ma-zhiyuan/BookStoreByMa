package org.lanqiao.base.bean;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private List<T> items = new ArrayList<T>();
	private Object cretiera;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Object getCretiera() {
		return cretiera;
	}

	public void setCretiera(Object cretiera) {
		this.cretiera = cretiera;
	}

	public Page(List<T> items, Object bc) {
		this.items = items;
		this.cretiera = bc;
	}

}

package org.lanqiao.shop.service;

import java.util.List;

import org.lanqiao.admin.entity.User;
import org.lanqiao.shop.dao.AddressDao;
import org.lanqiao.shop.entity.Address;
import org.lanqiao.shop.entity.Order;

public class AddressService {

	public List<Address> getAll(User u) {
		AddressDao dao = new AddressDao();
		return dao.getByUserId(u.getId());
	}
	
	public String add(Address add){
		int i = new AddressDao().add(add);
		if(i>0){
			return "增加成功！";
		}else{
			return "增加失败";
		}
	}

	public Address getByOrderId(Order o) {
		return new AddressDao().getByOrderId(o.getId());
	}

}

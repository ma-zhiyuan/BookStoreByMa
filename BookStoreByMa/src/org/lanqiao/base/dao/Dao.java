package org.lanqiao.base.dao;

import java.util.List;

public interface Dao<T> {
	T getT(int id);
	List<T> getAll(Object ... args);
	int update(T t);
	int delete(T t);
	int delete(int id);
	int add(T t);
}

package com.chahar.indent.service;

import java.io.Serializable;
import java.util.List;

public interface GenServicein<T> {

	List<T> getAll();

	List<T> getAllNew(Class<T> type);

	public List<T> getAllDistinct();

	T get(Integer id);

	T get(Class<T> clazz, Integer id);

	boolean exists(Integer id);

	T save(T object);

	public void remove(Integer id);

	public void remove(Object object);

	public List<T> findValuesByColumn(String columnName, Serializable value, Class<T> type);

	public List<T> findValuesByColumns(String[] columns, Serializable[] value, Class<T> type);

	public void persist(Object o);

	public void flushSession();
}

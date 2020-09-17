package com.chahar.indent.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.GenDao;

@Service("genService")
@Transactional
public class GenServices<T> implements GenServicein<T> {

	@Autowired
	protected GenDao<T> genericDao;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> getAllNew(Class<T> type) {
		return genericDao.getAllNew(type);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> getAllDistinct() {
		return genericDao.getAllDistinct();
	}

	@Override
	public T get(Integer id) {
		return genericDao.get(id);
	}

	@Override
	public T get(Class<T> clazz, Integer id) {
		return genericDao.get(id);
	}

	@Override
	public boolean exists(Integer id) {
		return genericDao.exists(id);
	}

	@Override
	public T save(T object) {
		return genericDao.save(object);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> findValuesByColumn(String columnName, Serializable value, Class<T> type) {
		return genericDao.findValuesByColumn(columnName, value, type);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> findValuesByColumns(String[] columns, Serializable[] value, Class<T> type) {
		return genericDao.findValuesByColumns(columns, value, type);
	}

	@Override
	public void persist(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flushSession() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> getAll() {
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void remove(Object object) {
		genericDao.remove(object);
	}

}

package com.chahar.indent.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenDaoClass<T> implements GenDao<T> {

	protected final Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllNew(Class<T> type) {
		return getCurrentSession().createCriteria(type).list();
	}

	public boolean exists(Integer id) {
		return false;
	}

	@SuppressWarnings("unchecked")
	public T save(T object) {
		return (T) getCurrentSession().merge(object);
	}

	public void remove(Integer id) {

	}

	public List<T> getAllDistinct() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findValuesByColumn(String columnName, Serializable value, Class<T> type) {
		return getCurrentSession().createCriteria(type).add(Restrictions.eq(columnName, value)).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findValuesByColumns(String[] columns, Serializable[] value, Class<T> type) {
		Criteria criteria = getCurrentSession().createCriteria(type);
		for (int i = 0; i < columns.length; i++) {
			criteria.add(Restrictions.eq(columns[i], value[i]));
		}
		return criteria.list();
	}

	public void flushSession() {
	}

	public T saveOrupdate(T object) {
		return null;
	}

	@Override
	public Class<T> getPersistentClass() {
		return null;
	}

	@Override
	public List<T> getAll() {
		return null;
	}

	@Override
	public T get(Integer id) {
		return null;
	}

	@Override
	public void remove(Object object) {
		getCurrentSession().delete(object);
	}

}

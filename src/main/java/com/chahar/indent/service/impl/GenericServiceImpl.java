package com.chahar.indent.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.GenericDao;
import com.chahar.indent.service.GenericService;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;org.appfuse.service.impl.GenericServiceImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;org.appfuse.dao.hibernate.GenericDaoHibernate&quot;&gt;
 *                 &lt;constructor-arg value=&quot;org.appfuse.model.User&quot;/&gt;
 *                 &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * <p>
 * If you're using iBATIS instead of Hibernate, use:
 * 
 * <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;org.appfuse.service.impl.GenericServiceImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;org.appfuse.dao.ibatis.GenericDaoiBatis&quot;&gt;
 *                 &lt;constructor-arg value=&quot;org.appfuse.model.User&quot;/&gt;
 *                 &lt;property name=&quot;dataSource&quot; ref=&quot;dataSource&quot;/&gt;
 *                 &lt;property name=&quot;sqlMapClient&quot; ref=&quot;sqlMapClient&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 */
public class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from
	 * Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of this class
	 */
	protected GenericDao<T, PK> genericDao;

	/**
	 * Public constructor for creating a new GenericServiceImpl.
	 * 
	 * @param genericDao the GenericDao to use for persistence
	 */
	public GenericServiceImpl(final GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> getAll() {
		return genericDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> getAllNew() {
		return genericDao.getAllNew();
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> getAllDistinct() {
		return genericDao.getAllDistinct();
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public T get(PK id) {
		return genericDao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean exists(PK id) {
		return genericDao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T save(T object) {
		return genericDao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void remove(PK id) {
		genericDao.remove(id);
	}

	/**
	 * 
	 * 
	 * @Override
	 * @Transactional(propagation = Propagation.SUPPORTS, readOnly = true) public T
	 *                            findByColumnValue(String name, Serializable value)
	 *                            { return genericDao.findByColumnValue(name,
	 *                            value); }
	 */

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> findValuesByColumn(String columnName, Serializable value) {
		return genericDao.findValuesByColumn(columnName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<T> findValuesByColumns(String[] columns, Serializable[] value) {
		return genericDao.findValuesByColumns(columns, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public void persist(Object o) {

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public T get(Class<T> clazz, PK id) {
		return null;
	}

	public void flushSession() {
		genericDao.flushSession();
	}

}

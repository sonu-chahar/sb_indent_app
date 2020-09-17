package com.chahar.indent.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericDao<T, PK extends Serializable> {

	public Class<T> getPersistentClass();

	/**
	 * Generic method used to get all objects of a particular type. This is the same
	 * as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Generic method used to get all objects of a particular type. This is the same
	 * as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllNew();

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK id);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK id);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object the object to save
	 * @return the persisted object
	 */
	T save(T object);

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id the identifier (primary key) of the object to remove
	 */
	void remove(PK id);

	/**
	 * Gets all records without duplicates.
	 * <p>
	 * Note that if you use this method, it is imperative that your model classes
	 * correctly implement the hashcode/equals methods
	 * </p>
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllDistinct();

	/**
	 * Find a list of records by using a named query
	 * 
	 * @param queryName   query name of the named query
	 * @param queryParams a map of the query names and the values
	 * @return a list of the records found
	 * 
	 *         List<T> findByNamedQuery(String queryName, Map<String, Object>
	 *         queryParams);
	 */

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * 
	 *         public T findByColumnValue(String name, Serializable value);
	 */

	/**
	 * This method will return all the objects for a given column value. This will
	 * work like select * from table where column=?
	 * 
	 * @param columnName
	 * @param value
	 * @return
	 */
	public List<T> findValuesByColumn(String columnName, Serializable value);

	/**
	 * 
	 * @param columns
	 * @param value
	 * @return
	 */
	public List<T> findValuesByColumns(String[] columns, Serializable[] value);

	public void flushSession();

	public T saveOrupdate(T object);

}
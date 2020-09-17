package com.chahar.indent.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chahar.indent.dao.OrderMasterDao;
import com.chahar.indent.dao.ProductMasterDao;
import com.chahar.indent.dao.UserMasterDao;
import com.chahar.indent.model.OrderMaster;
import com.chahar.indent.model.ProductMaster;
import com.chahar.indent.model.UserMaster;

@Repository("orderMasterDao")
public class OrderMasterDaoImpl extends GenericDaoImpl<OrderMaster, Long> implements OrderMasterDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ProductMasterDao productDAO;

	@Autowired
	private UserMasterDao userMasterDao;

	public OrderMasterDaoImpl() {
		super(OrderMaster.class);
	}

	private int getMaxOrderNum() {
		String sql = "Select max(o.orderNum) from " + OrderMaster.class.getName() + " o ";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		Integer value = (Integer) query.uniqueResult();
		if (value == null) {
			return 0;
		}
		return value;
	}

	@Override
	public OrderMaster saveOrder(String code, int quantity, String username) {

		int orderNum = this.getMaxOrderNum() + 1;

		UserMaster userMaster = userMasterDao.findByUsername(username);
		ProductMaster product = productDAO.findProduct(code);
		product.setQuantity(product.getQuantity() - quantity);
		OrderMaster order = new OrderMaster();
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setAmount(quantity * (product.getPrice()));
		order.setQuantity(quantity);
		order.setUserMaster(userMaster);
		order.setProduct(product);
		getCurrentSession().save(order);

		return order;
	}

	/*
	 * // @page = 1, 2, ...
	 * 
	 * @Override public PaginationResult<OrderInfo> listOrderInfo(int page, int
	 * maxResult, int maxNavigationPage) { String sql = "Select new " +
	 * OrderInfo.class.getName()// +
	 * "(ord.id, ord.orderDate, ord.orderNum, ord.amount, " +
	 * " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) "
	 * + " from " + Order.class.getName() + " ord "// +
	 * " order by ord.orderNum desc"; Session session =
	 * this.sessionFactory.getCurrentSession();
	 * 
	 * Query query = session.createQuery(sql);
	 * 
	 * return new PaginationResult<OrderInfo>(query, page, maxResult,
	 * maxNavigationPage); }
	 */

	public OrderMaster findOrder(String orderId) {
		Criteria crit = getCurrentSession().createCriteria(OrderMaster.class);
		crit.add(Restrictions.eq("id", orderId));
		return (OrderMaster) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderMaster> getOrdersByUserName(String username) {
		UserMaster userMaster = userMasterDao.findByUsername(username);
		Criteria crit = getCurrentSession().createCriteria(OrderMaster.class);
		crit.add(Restrictions.eq("userMaster.id", userMaster.getId()));
		List<OrderMaster> list = (List<OrderMaster>) crit.list();
		/* list.forEach(Order::getProduct); */
		return list;
	}

}

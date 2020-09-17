package com.chahar.indent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chahar.indent.dao.OrderMasterDao;
import com.chahar.indent.model.OrderMaster;
import com.chahar.indent.service.OrderMasterService;

@Service("orderMasterService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderMasterServiceImpl extends GenericServiceImpl<OrderMaster, Long> implements OrderMasterService {

	private OrderMasterDao orderMasterDao;

	@Autowired
	public OrderMasterServiceImpl(@Qualifier("orderMasterDao") OrderMasterDao orderMasterDao) {
		super(orderMasterDao);
		this.orderMasterDao = orderMasterDao;

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public OrderMaster saveOrder(String code, int quantity, String username) {
		return orderMasterDao.saveOrder(code, quantity, username);
	}

	@Override
	public List<OrderMaster> getOrdersByUserName(String username) {
		return orderMasterDao.getOrdersByUserName(username);
	}

}

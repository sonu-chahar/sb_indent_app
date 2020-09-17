package com.chahar.indent.dao;

import java.util.List;

import com.chahar.indent.model.OrderMaster;

public interface OrderMasterDao extends GenericDao<OrderMaster, Long> {
	public OrderMaster saveOrder(String code, int quantity, String username);

	public List<OrderMaster> getOrdersByUserName(String username);

}

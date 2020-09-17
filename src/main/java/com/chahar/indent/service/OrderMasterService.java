package com.chahar.indent.service;

import java.util.List;

import com.chahar.indent.model.OrderMaster;

public interface OrderMasterService extends GenericService<OrderMaster, Long> {

	public OrderMaster saveOrder(String code, int quantity, String username);

	public List<OrderMaster> getOrdersByUserName(String username);
}
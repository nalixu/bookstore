package cn.edu.lsu.dao;

import java.util.List;

import cn.edu.lsu.bean.OrderItem;


public interface OrderItemDAO {
	//������ID�õ�������Ʒ�б�
	public List<OrderItem> getOrderItemsById(String id);
}

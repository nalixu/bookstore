package cn.edu.lsu.dao;

import java.sql.SQLException;
import java.util.List;

import cn.edu.lsu.bean.Order;

public interface OrderDAO {

	// ���ɶ���
		public void addProduct(Order order);
	//�����û�����	
		public List<Order> findOrderByUser(int id);
		// ����id���Ҷ�����Ϣ
		public Order findOrderById(String id) ;
		// �������ж���
		public List<Order> findAllOrder();
		// ���ݶ������޸Ķ���״̬
		public void updateOrderState(String id);
		// ��������ѯ
		public List<Order> findOrderByManyCondition(String id, String receiverName);
		//����idɾ������
		public void delOrderById(String id);
}

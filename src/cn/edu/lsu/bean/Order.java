package cn.edu.lsu.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Order {




	public Order() {
	
	}

	public Order(String id, double money, String receiverAddress,
			String receiverName, String receiverPhone, int paystate,
			Date ordertime, int userid, String username) {
		
		this.id = id;
		this.money = money;
		this.receiverAddress = receiverAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.paystate = paystate;
		this.ordertime = ordertime;
		this.userid = userid;
		this.username = username;
	}


	private String id; // 订单编号
	private double money; // 订单总价
	private String receiverAddress; // 送货地址
	private String receiverName; // 收货人姓�?
	private String receiverPhone; // 收货人电�?
	private int paystate; // 订单状�?
	private Date ordertime; // 下单时间

	private int userid; // 订单�?��用户
	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverAddress=" + receiverAddress + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + ", paystate=" + paystate + ", ordertime="
				+ ordertime + ", userid=" + userid + ", username=" + username + "]";
	}


	private String username;//订单用户名称

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}


	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getPaystate() {
		return paystate;
	}

	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}


}

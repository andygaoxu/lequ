package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class UserBalanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会话Id

	private int id;
	//

	private String openId;
	//余额可以提现
	private double totalAmount;

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}


	
}

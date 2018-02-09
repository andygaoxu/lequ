package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class RobPacketStatEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会话Id

	private int id;
	//
	private String openId;
	private double totalAmount;
	private int totalNumber;
	
	
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
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	
}

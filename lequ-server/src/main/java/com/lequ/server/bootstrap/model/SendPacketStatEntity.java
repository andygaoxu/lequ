package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class SendPacketStatEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会话Id

	private int id;
	//

	private String openId;
	//发包总金额
	private double totalAmount;
	//发包总个数
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

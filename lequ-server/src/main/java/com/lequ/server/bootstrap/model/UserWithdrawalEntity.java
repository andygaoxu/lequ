package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class UserWithdrawalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会话Id

	private int id;
	//

	private String openId;
	
	//本次体现金额
	private double amount;
	//余额可以提现
	private double totalAmount;
	//提现状态,0未提现,1提现中,2已提现
	private String withdrawalState;
	private Date withdrawalStartDate;
	private Date withdrawalEndDate;


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

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

	public String getWithdrawalState() {
		return withdrawalState;
	}

	public void setWithdrawalState(String withdrawalState) {
		this.withdrawalState = withdrawalState;
	}

	public Date getWithdrawalStartDate() {
		return withdrawalStartDate;
	}

	public void setWithdrawalStartDate(Date withdrawalStartDate) {
		this.withdrawalStartDate = withdrawalStartDate;
	}

	public Date getWithdrawalEndDate() {
		return withdrawalEndDate;
	}

	public void setWithdrawalEndDate(Date withdrawalEndDate) {
		this.withdrawalEndDate = withdrawalEndDate;
	}
	
}

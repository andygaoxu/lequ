package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class SendPacketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会话Id
	private int id;
	//
	private String openId;
	private double amount;
	private int number;
	private String content;
	private String robbedState;
	private int robbedNumber;

	private Date createTime;
	//红包的唯一Id
	private String packetId;
	
	private List<RobPacketEntity>  robPacketList;

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

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRobbedState() {
		return robbedState;
	}

	public void setRobbedState(String robbedState) {
		this.robbedState = robbedState;
	}

	public int getRobbedNumber() {
		return robbedNumber;
	}

	public void setRobbedNumber(int robbedNumber) {
		this.robbedNumber = robbedNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPacketId() {
		return packetId;
	}

	public void setPacketId(String packetId) {
		this.packetId = packetId;
	}

	public List<RobPacketEntity> getRobPacketList() {
		return robPacketList;
	}

	public void setRobPacketList(List<RobPacketEntity> robPacketList) {
		this.robPacketList = robPacketList;
	}
	
	
}

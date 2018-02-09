package com.lequ.server.bootstrap.model;

import java.io.Serializable;
import java.util.Date;

public class RobPacketEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 会话Id

	private int id;
	//
	private String openId;
	private double amount;
	//用户什么时间抢的包
	private Date createTime;
	// 唯一关联红包的id
	private String packetId;
	// 抢包语音文件存储路径
	private String recorderFilePath;
	// 冗余存储发包人用户信息
	private String senderOpenId;
	private String nickName;
	private String avatarUrl;
	private String gender;
	private String province;
	private String country;
	private Date updateTime;
	private String splitId;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public String getRecorderFilePath() {
		return recorderFilePath;
	}

	public void setRecorderFilePath(String recorderFilePath) {
		this.recorderFilePath = recorderFilePath;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getSenderOpenId() {
		return senderOpenId;
	}

	public void setSenderOpenId(String senderOpenId) {
		this.senderOpenId = senderOpenId;
	}

	public String getSplitId() {
		return splitId;
	}

	public void setSplitId(String splitId) {
		this.splitId = splitId;
	}

}

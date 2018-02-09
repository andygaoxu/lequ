package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class LeQuIndexEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UserBalanceEntity userBalanceEntity;
	
	private List<PacketContentEntity> packetContentEntity;

	public UserBalanceEntity getUserBalanceEntity() {
		return userBalanceEntity;
	}

	public void setUserBalanceEntity(UserBalanceEntity userBalanceEntity) {
		this.userBalanceEntity = userBalanceEntity;
	}

	public List<PacketContentEntity> getPacketContentEntity() {
		return packetContentEntity;
	}

	public void setPacketContentEntity(List<PacketContentEntity> packetContentEntity) {
		this.packetContentEntity = packetContentEntity;
	}

	
}

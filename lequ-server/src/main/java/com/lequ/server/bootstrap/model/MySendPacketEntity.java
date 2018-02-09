package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class MySendPacketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SendPacketStatEntity sendPacketStatEntity;
	
	private List<SendPacketEntity> listSendPacketEntity;

	

	public SendPacketStatEntity getSendPacketStatEntity() {
		return sendPacketStatEntity;
	}

	public void setSendPacketStatEntity(SendPacketStatEntity sendPacketStatEntity) {
		this.sendPacketStatEntity = sendPacketStatEntity;
	}

	public List<SendPacketEntity> getListSendPacketEntity() {
		return listSendPacketEntity;
	}

	public void setListSendPacketEntity(List<SendPacketEntity> listSendPacketEntity) {
		this.listSendPacketEntity = listSendPacketEntity;
	}
	
	
	
}

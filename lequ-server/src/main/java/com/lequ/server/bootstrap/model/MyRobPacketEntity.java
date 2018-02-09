package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class MyRobPacketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private RobPacketStatEntity robPacketStatEntity;
	
	private List<RobPacketEntity> robPacketEntity;

	public RobPacketStatEntity getRobPacketStatEntity() {
		return robPacketStatEntity;
	}

	public void setRobPacketStatEntity(RobPacketStatEntity robPacketStatEntity) {
		this.robPacketStatEntity = robPacketStatEntity;
	}

	public List<RobPacketEntity> getRobPacketEntity() {
		return robPacketEntity;
	}

	public void setRobPacketEntity(List<RobPacketEntity> robPacketEntity) {
		this.robPacketEntity = robPacketEntity;
	}
	
	
	
	
}

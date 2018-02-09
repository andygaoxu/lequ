package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class PacketSplitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private int id;
	private String packetId;
	private double amount;
	private String splitId;


	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getPacketId() {
		return packetId;
	}

	public void setPacketId(String packetId) {
		this.packetId = packetId;
	}

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSplitId() {
		return splitId;
	}

	public void setSplitId(String splitId) {
		this.splitId = splitId;
	}

}

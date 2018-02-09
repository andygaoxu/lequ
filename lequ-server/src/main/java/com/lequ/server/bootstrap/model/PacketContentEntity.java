package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class PacketContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//会话Id

	private int id;
	private String content;
	private int numberWords;
	private int number;
	private Date creatTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNumberWords() {
		return numberWords;
	}
	public void setNumberWords(int numberWords) {
		this.numberWords = numberWords;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
}

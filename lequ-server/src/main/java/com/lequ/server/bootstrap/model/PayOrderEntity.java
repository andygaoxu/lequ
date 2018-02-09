package com.lequ.server.bootstrap.model;



import java.io.Serializable;
import java.util.Date;


public class PayOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String openId;
	//
	private int total_fee;//订单金额，单位为分。
	
	private String out_trade_no;//商户订单号
	
	private String body;//商品商品简单描述，腾讯充值中心-QQ会员充值
	
	private String time_start;//14
	
	private String time_expire;//14
	
	private String pay_status;
	

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

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	
}

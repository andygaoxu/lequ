package com.lequ.server.bootstrap.threadpool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lequ.common.concurrent.Rejectable;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketStatEntity;
import com.lequ.server.bootstrap.service.SendPacketStatService;

public class SendPacketStatfThread extends Thread implements Rejectable {
	public static final Log logger =  LogFactory.getLog(SendPacketStatfThread.class);
	SendPacketStatService sendPacketStatServiceThread = null;
	SendPacketEntity sendPacketEntityThread = null;
	String openId = null;
	double amount = 0;
	public SendPacketStatfThread(SendPacketStatService sendPacketStatService,SendPacketEntity sendPacketEntity){
		sendPacketStatServiceThread = sendPacketStatService;
		sendPacketEntityThread = sendPacketEntity;
		openId = sendPacketEntity.getOpenId();
		amount = sendPacketEntity.getAmount();
	}
	
	public void run(){
		try{
			SendPacketStatEntity sendPacketStatByOpenId = sendPacketStatServiceThread.queryPacketStat(openId);
			if(sendPacketStatByOpenId!=null&&sendPacketStatByOpenId.getTotalAmount()>0)
			{
				SendPacketStatEntity sendPacketStatUpdate = new SendPacketStatEntity();
				sendPacketStatUpdate.setOpenId(openId);
				sendPacketStatUpdate.setTotalAmount((sendPacketStatByOpenId.getTotalAmount()+amount));
				sendPacketStatUpdate.setTotalNumber((sendPacketStatByOpenId.getTotalNumber()+1));
				sendPacketStatServiceThread.updatePacketStat(sendPacketStatUpdate);
			}else{
				sendPacketStatByOpenId = new SendPacketStatEntity();
				sendPacketStatByOpenId.setOpenId(openId);
				sendPacketStatByOpenId.setTotalAmount(amount);	
				sendPacketStatByOpenId.setTotalNumber(1);
				sendPacketStatServiceThread.insertSendPacketStat(sendPacketStatByOpenId);
			}
		}catch(Exception e){
			logger.error(e);
		}
	}

	@Override
	public void reject() {
		
	}

}

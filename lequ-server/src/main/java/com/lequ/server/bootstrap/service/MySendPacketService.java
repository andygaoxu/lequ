package com.lequ.server.bootstrap.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lequ.server.bootstrap.model.MySendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketStatEntity;


@Service
public class MySendPacketService {

    public static final Log logger =  LogFactory.getLog(MySendPacketService.class);
    @Autowired
    SendPacketStatService sendPacketStatService;
    @Autowired
    SendPacketService sendPacketService;
    
    public MySendPacketEntity querySendPacket(String openId){
    	MySendPacketEntity mySendPacketInfo = new MySendPacketEntity();
    	try{
    		
    		SendPacketStatEntity sendPacketStatEntity = sendPacketStatService.queryPacketStat(openId);
    		mySendPacketInfo.setSendPacketStatEntity(sendPacketStatEntity);
    		
    		List<SendPacketEntity> listSendPacketEntity = sendPacketService.queryList(openId);
    		mySendPacketInfo.setListSendPacketEntity(listSendPacketEntity);
    		
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return mySendPacketInfo;
    }
}

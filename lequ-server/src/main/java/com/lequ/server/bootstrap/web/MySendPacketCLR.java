package com.lequ.server.bootstrap.web;

import com.lequ.server.bootstrap.model.MySendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketStatEntity;
import com.lequ.server.bootstrap.service.SendPacketService;
import com.lequ.server.bootstrap.service.SendPacketStatService;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequestMapping(value = "mysp")
public class MySendPacketCLR {
    public static final Log logger = LogFactory.getLog(MySendPacketCLR.class);

    @Resource
    SendPacketService sendPacketService ;
    @Resource
    SendPacketStatService sendPacketStatService ;
   
    @RequestMapping(value = "/queryMySPInfo")
    public  MySendPacketEntity queryPacketList(String openId) {
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

package com.lequ.server.bootstrap.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lequ.server.bootstrap.model.SendPacketStatEntity;
import com.lequ.server.bootstrap.service.SendPacketStatService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping(value = "sendpacketstat")
public class SendPacketStatCLR {
    public static final Log logger = LogFactory.getLog(SendPacketStatCLR.class);

    @Resource
    SendPacketStatService sendPacketStatService;
   
    @RequestMapping(value = "/insertPacketStat")
    public Object insertPacketStat(SendPacketStatEntity sendPacketStatEntity, HttpServletRequest request, HttpServletResponse response) {
    	boolean result = true;
    	try{

    		result = sendPacketStatService.insertSendPacketStat(sendPacketStatEntity);
    		
    	}catch(Exception e){
    		logger.error(e);
    		result = false;
    	}
    	
		return result;
    	
    }
   
    @RequestMapping(value = "/queryPacketStat")
    public  SendPacketStatEntity queryPacketStat(String openId) {
    	SendPacketStatEntity sendPacketEntity = new SendPacketStatEntity();
    	try{
    		sendPacketEntity = sendPacketStatService.queryPacketStat(openId);
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return sendPacketEntity;
	}
  
    @RequestMapping(value = "/updatePacketStat")
    public  boolean updatePacketStat(SendPacketStatEntity sendPacketStatEntity) {
    	boolean result = true;
    	try{
    		result = sendPacketStatService.updatePacketStat(sendPacketStatEntity);
    		
    	}catch(Exception e){
    		logger.error(e);
    		result = false;
    	}
    	return result;
	}
  
}

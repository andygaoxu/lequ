package com.lequ.server.bootstrap.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lequ.server.bootstrap.model.RobPacketStatEntity;
import com.lequ.server.bootstrap.service.RobPacketStatService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "robpacketstat")
public class RobPacketStatCLR {
    public static final Log logger = LogFactory.getLog(RobPacketStatCLR.class);

    @Resource
    RobPacketStatService robPacketStatService;
   
    @RequestMapping(value = "/insertPacketStat")
    public Object insertPacketStat(RobPacketStatEntity robPacketStatEntity, HttpServletRequest request, HttpServletResponse response) {
    	boolean result = true;
    	try{

    		result = robPacketStatService.insertRobPacketStat(robPacketStatEntity);
    		
    	}catch(Exception e){
    		logger.error(e);
    		result = false;
    	}
    	
		return result;
    	
    }
   
    @RequestMapping(value = "/queryPacketStat")
    public  RobPacketStatEntity queryPacketStat(String openId) {
    	RobPacketStatEntity sendPacketEntity = new RobPacketStatEntity();
    	try{
    		sendPacketEntity = robPacketStatService.queryPacketStat(openId);
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return sendPacketEntity;
	}
  
    @RequestMapping(value = "/updatePacketStat")
    public  boolean updatePacketStat(RobPacketStatEntity sendPacketStatEntity) {
    	boolean result = true;
    	try{
    		result = robPacketStatService.updatePacketStat(sendPacketStatEntity);
    		
    	}catch(Exception e){
    		logger.error(e);
    		result = false;
    	}
    	return result;
	}
  
}

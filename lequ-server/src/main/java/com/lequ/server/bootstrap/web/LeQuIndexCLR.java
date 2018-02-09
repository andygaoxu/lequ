package com.lequ.server.bootstrap.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lequ.server.bootstrap.model.LeQuIndexEntity;
import com.lequ.server.bootstrap.model.PacketContentEntity;
import com.lequ.server.bootstrap.model.UserBalanceEntity;
import com.lequ.server.bootstrap.service.PacketContentService;
import com.lequ.server.bootstrap.service.UserBalanceService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping(value = "lequ")
public class LeQuIndexCLR {
    public static final Log logger = LogFactory.getLog(LeQuIndexCLR.class);

    @Resource
    PacketContentService packetContentService;
    
    @Resource
    UserBalanceService userBalanceService;
    
    @RequestMapping(value = "/index")
    public  LeQuIndexEntity queryLeQuIndex(String openId) {
    	LeQuIndexEntity lequIndex = new LeQuIndexEntity();
    	 List<PacketContentEntity> contentList = new ArrayList<PacketContentEntity>();
    	try{
    		contentList = packetContentService.queryList();
    		lequIndex.setPacketContentEntity(contentList);
    		UserBalanceEntity userBalanceEntity= userBalanceService.queryBalance(openId);
    		lequIndex.setUserBalanceEntity(userBalanceEntity);
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return lequIndex;
	}
    

}

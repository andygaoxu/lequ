package com.lequ.server.bootstrap.web;

import com.lequ.server.bootstrap.model.MyRobPacketEntity;
import com.lequ.server.bootstrap.model.RobPacketEntity;
import com.lequ.server.bootstrap.model.RobPacketStatEntity;
import com.lequ.server.bootstrap.service.RobPacketService;
import com.lequ.server.bootstrap.service.RobPacketStatService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.List;



@RestController
@RequestMapping(value = "myrp")
public class MyRobPacketCLR {
    public static final Log logger = LogFactory.getLog(MyRobPacketCLR.class);

    @Resource
    RobPacketService robPacketService;
    @Resource
    RobPacketStatService robPacketStatService;
    
    @RequestMapping(value = "/queryMyRPInfo")
    public  MyRobPacketEntity queryRobPacket(String openId) {
    	MyRobPacketEntity robPacketEntity = new MyRobPacketEntity();
    	try{
    		
    		RobPacketStatEntity robPacketStatEntity = robPacketStatService.queryPacketStat(openId);
    		robPacketEntity.setRobPacketStatEntity(robPacketStatEntity);
    		
    		List<RobPacketEntity> robPacketEntityList = robPacketService.queryList(openId);
    		robPacketEntity.setRobPacketEntity(robPacketEntityList);
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return robPacketEntity;
	}
    

}

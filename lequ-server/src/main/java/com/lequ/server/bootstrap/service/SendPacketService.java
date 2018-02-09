package com.lequ.server.bootstrap.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.common.concurrent.ThreadPool;
import com.lequ.server.bootstrap.dao.sendpacket.SendPacketDao;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.threadpool.SendPacketStatfThread;


@Service
public class SendPacketService {

    public static final Log logger =  LogFactory.getLog(SendPacketService.class);
    @Autowired
    SendPacketDao sendPacketDao;
    @Autowired
    PacketSplitService packetSplitService;
    @Autowired
    SendPacketStatService sendPacketStatService;
    
    public boolean insertSendPacket(SendPacketEntity sendPacketEntity){
    	try{
    		//第一步
    		packetSplitService.packetSplit(sendPacketEntity.getPacketId(),sendPacketEntity.getAmount(),sendPacketEntity.getNumber());
    		sendPacketDao.insert(sendPacketEntity);
    		//第二步，去交给异步线程生成发送统计,同一个用户发包不会出现并发，这是采用多线程异步统计的前提
    		ThreadPool.addSendPacketThreadPoolTask(new SendPacketStatfThread(sendPacketStatService,sendPacketEntity));
    	}catch(Exception e){
    		logger.error(e);
    		return false;	
    	}
		return true;
    	
    }
    public List<SendPacketEntity> queryList(String openId){
    	List<SendPacketEntity> list = new ArrayList<SendPacketEntity>();
    	try{
    		list = sendPacketDao.queryList(openId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return list;
    }
    
    public SendPacketEntity queryPacket(String id){
    	SendPacketEntity packet = new SendPacketEntity();
    	try{
    		packet = sendPacketDao.queryObject(id);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packet;
    }
    public SendPacketEntity queryPacketById(String packetId){
    	SendPacketEntity packet = new SendPacketEntity();
    	try{
    		packet = sendPacketDao.queryObjectByPacketId(packetId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packet;
    }
    
    public boolean updateSendPacketRobbed(SendPacketEntity sendPacketEntity){
    	try{
    		sendPacketDao.update(sendPacketEntity);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    }
}

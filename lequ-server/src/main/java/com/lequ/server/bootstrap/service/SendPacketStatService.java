package com.lequ.server.bootstrap.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.server.bootstrap.dao.sendpacket.SendPacketStatDao;
import com.lequ.server.bootstrap.model.SendPacketStatEntity;



@Service
public class SendPacketStatService {

    public static final Log logger =  LogFactory.getLog(SendPacketStatService.class);
    @Autowired
    SendPacketStatDao sendPacketStatDao;
    
    /**保存用户发包统计记录
     * @param sendPacketStatEntity
     * @return
     */
    public boolean insertSendPacketStat(SendPacketStatEntity sendPacketStatEntity){
    	try{
    		sendPacketStatDao.insert(sendPacketStatEntity);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
    
    /**获取个人发包统计记录
     * @param openId
     * @return
     */
    public SendPacketStatEntity queryPacketStat(String openId){
    	SendPacketStatEntity packetStat = new SendPacketStatEntity();
    	try{
    		packetStat = sendPacketStatDao.queryObject(openId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packetStat;
    }
    
    /**更新累加计算结果
     * @param packetStat
     * @return
     */
    public boolean updatePacketStat(SendPacketStatEntity packetStat){
    	
    	try{
    		sendPacketStatDao.update(packetStat);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
}

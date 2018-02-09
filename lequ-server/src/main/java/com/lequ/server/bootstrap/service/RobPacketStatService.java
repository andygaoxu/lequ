package com.lequ.server.bootstrap.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.server.bootstrap.dao.robpacket.RobPacketStatDao;
import com.lequ.server.bootstrap.model.RobPacketStatEntity;




@Service
public class RobPacketStatService {

    public static final Log logger =  LogFactory.getLog(RobPacketStatService.class);
    @Autowired
    RobPacketStatDao robPacketStatDao;
    
    /**保存用户发包统计记录
     * @param sendPacketStatEntity
     * @return
     */
    public boolean insertRobPacketStat(RobPacketStatEntity robPacketStatEntity){
    	try{
    		robPacketStatDao.insert(robPacketStatEntity);
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
    public RobPacketStatEntity queryPacketStat(String openId){
    	RobPacketStatEntity packetStat = new RobPacketStatEntity();
    	try{
    		packetStat = robPacketStatDao.queryObject(openId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packetStat;
    }
    
    /**更新累加计算结果
     * @param packetStat
     * @return
     */
    public boolean updatePacketStat(RobPacketStatEntity packetStat){
    	
    	try{
    		robPacketStatDao.update(packetStat);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
}

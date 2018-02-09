package com.lequ.server.bootstrap.service;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.server.bootstrap.dao.packet.PacketContentDao;
import com.lequ.server.bootstrap.model.PacketContentEntity;
import com.lequ.server.bootstrap.model.RobPacketEntity;



@Service
public class PacketContentService {

    public static final Log logger =  LogFactory.getLog(PacketContentService.class);
    @Autowired
    PacketContentDao packetContentDao;
    
    public boolean insertSendPacketSplit(PacketContentEntity packetContentEntity){
    	try{
    		packetContentDao.insert(packetContentEntity);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
    public List<PacketContentEntity> queryList() {
		List<PacketContentEntity> list = new ArrayList<PacketContentEntity>();
		try {
			list = packetContentDao.queryList(null);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
   
}

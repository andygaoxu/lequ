package com.lequ.server.bootstrap.web;

import com.lequ.server.bootstrap.model.PacketSplitEntity;
import com.lequ.server.bootstrap.model.RobPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.service.PacketSplitService;
import com.lequ.server.bootstrap.service.RobPacketService;
import com.lequ.server.bootstrap.service.SendPacketService;
import com.lequ.server.bootstrap.util.RoundRobinPacket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping(value = "robpacket")
public class RobPacketCLR {
    public static final Log logger = LogFactory.getLog(RobPacketCLR.class);

    @Resource
    RobPacketService robPacketService;
    @Resource
	PacketSplitService 	packetSplitService;
    @Resource
    SendPacketService 	sendPacketService;
    @Resource
    RoundRobinPacket 	roundRobinPacket;
    
    
    @RequestMapping(value = "/insertRobPacket")
    public Object insertRobPacket(RobPacketEntity robPacketEntity, HttpServletRequest request, HttpServletResponse response) {
    	boolean result = true;
    	String packetId = "";
    	try{
    		packetId = robPacketEntity.getPacketId();
    		//0：检查红包是否还存在，根据红包Id来检查。
    		SendPacketEntity sendPacketEntity = sendPacketService.queryPacketById(packetId);
    		if(sendPacketEntity!=null&&(sendPacketEntity.getNumber()==sendPacketEntity.getRobbedNumber())){
    			logger.info("[HaveBeenRobbed]-[packetId:"+packetId+"]-[openId:"+sendPacketEntity.getOpenId()+"] rob Fail! The red packets have been robbed! ");
    			return false;
    		}
    		
    		//1
    		List<PacketSplitEntity> queryPacketList = packetSplitService.queryPacketList(packetId);
    		if(queryPacketList==null||queryPacketList.size()<=0)
    			return false;
    		PacketSplitEntity packetSplitEntity = roundRobinPacket.doSelect(queryPacketList, packetId);
    		
    		
    		//2：校验用户语音包的准确性，也可以不校验，按第一遍失败、或是第二遍失败、第三遍成功的策略发放红包。		
    		try{
        		//3：抢到包保存，并做统计
    			robPacketEntity.setAmount(packetSplitEntity.getAmount());
    			robPacketEntity.setSplitId(packetSplitEntity.getSplitId());
        		robPacketEntity.setCreateTime(robPacketEntity.getCreateTime()!=null?robPacketEntity.getCreateTime():(new Date()));
        		result = robPacketService.insertRobPacket(robPacketEntity,sendPacketEntity,packetSplitEntity);	
        	}catch(Exception e){
        		logger.error(e);
        		return  false;
        	}
    		
    	}catch(Exception e){
    		logger.error(e);
    		return false;
    	}
    	
		return result;
    	
    }
   
    @RequestMapping(value = "/queryRobPacket")
    public  RobPacketEntity queryRobPacket(String id) {
    	RobPacketEntity robPacketEntity = new RobPacketEntity();
    	try{
    		robPacketEntity = robPacketService.queryPacket(id);
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return robPacketEntity;
	}
    @RequestMapping(value = "/queryRobPacketList")
    public  List<RobPacketEntity> queryRobPacketList(String openId) {
    	List<RobPacketEntity> lsit = new ArrayList<RobPacketEntity>();
    	try{
    		lsit = robPacketService.queryList(openId);
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return lsit;
	}

}

package com.lequ.server.bootstrap.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.server.bootstrap.dao.packet.PacketSplitDao;
import com.lequ.server.bootstrap.model.PacketSplitEntity;
import com.lequ.server.bootstrap.util.PacketUtil;


@Service
public class PacketSplitService {

    public static final Log logger =  LogFactory.getLog(PacketSplitService.class);
    @Autowired
    PacketSplitDao packetSplitDao;
    
    public boolean insertSendPacketSplit(PacketSplitEntity packetSplitEntity){
    	try{
    		packetSplitDao.insert(packetSplitEntity);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
  
    public List<PacketSplitEntity> queryPacketList(String packetId){
    	List<PacketSplitEntity> packetList = new ArrayList<PacketSplitEntity>();
    	try{
    		packetList = packetSplitDao.queryList(packetId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packetList;
    }
    public PacketSplitEntity queryPacket(String packetId,String splitid){
    	PacketSplitEntity packet = new PacketSplitEntity();
    	try{
    		packet = packetSplitDao.queryObjectByPS(packetId,splitid);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packet;
    }
    
    public boolean deletePacketSplitItem(int id){
    	try{
    		packetSplitDao.delete(id);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    }
    
    public  void packetSplit(String packetId ,double total_money ,int total_people){
		// total_money红包总金额
		// total_people抢红包总人数
    	List<PacketSplitEntity> packetSplitList = new ArrayList<PacketSplitEntity>();
		double min_money;  // 每个人最少能收到0.01元
		double tmp_total_money = total_money;
		PacketSplitEntity packetSplit = null;
//		total_money = 10;
//		total_people = 5;
		min_money = 0.99;
		double tmp_sum = 0 ;
//		long start = System.currentTimeMillis();
		for (int i = 0; i < total_people - 1; i++) {
			packetSplit = new PacketSplitEntity();
			int j = i + 1;
			double safe_money = (total_money - (total_people - j) * min_money)
					/ (total_people - j);
			//当前红包钱数
			double tmp_money = (Math.random()
					* (safe_money * 100 - min_money * 100) + min_money * 100) / 100;
			//当前拆分的钱做2位小数保留处理后的额值
			double tmp_money_2 = PacketUtil.formatDouble1(tmp_money);
			total_money = total_money - tmp_money;
			packetSplit.setSplitId(new String().valueOf(j));
			packetSplit.setAmount(tmp_money_2);
			packetSplit.setPacketId(packetId);
	
			tmp_sum += tmp_money_2;
			
			packetSplitList.add(packetSplit);
//			 boolean re = insertSendPacketSplit(packetSplit);
			 
//			 if(re){
//				//成功写到库里就不需要记录日志了，如果没有成功写库需要写日志
//			 }else{
//				 logger.info("[PACKET]:["+packetId+""+"||"+new String().valueOf(j)+"||"+tmp_money_2+"]");
//			 }
			
		}
//		long end = System.currentTimeMillis();
//		logger.info("执行时间："+(end-start));

		logger.info("qian ji ge ："+tmp_sum);
		double end_money = PacketUtil.formatDouble1(total_money);
		double init_end_money = PacketUtil.formatDouble1(tmp_total_money - tmp_sum) ;
		logger.info("end："+ init_end_money);
		logger.info("end_money ："+end_money);
		
		if( init_end_money == end_money)
		{
			//等于，说明拆分的正好
		}else {
			if( init_end_money > end_money)
				end_money = end_money+(init_end_money-end_money);
			else
				end_money = end_money-(end_money-init_end_money);
		}

		packetSplit = new PacketSplitEntity();
		packetSplit.setPacketId(packetId);
		packetSplit.setSplitId(new String().valueOf(total_people));
		packetSplit.setAmount(end_money);
		packetSplitList.add(packetSplit);

		packetSplitDao.insertByBatch(packetSplitList);


		
//		 boolean reEnd = insertSendPacketSplit(packetSplit);
//		 if(reEnd){
//				//成功写到库里就不需要记录日志了，如果没有成功写库需要写日志
//		} else {
//			logger.info("[PACKET]:[" + packetId + "" + "||"
//					+ new String().valueOf(total_people) + "||" + end_money
//					+ "]");
//		}

	}
}

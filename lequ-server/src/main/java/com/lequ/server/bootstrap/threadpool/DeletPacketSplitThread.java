package com.lequ.server.bootstrap.threadpool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lequ.common.concurrent.Rejectable;
import com.lequ.server.bootstrap.model.PacketSplitEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketStatEntity;
import com.lequ.server.bootstrap.service.PacketSplitService;
import com.lequ.server.bootstrap.service.SendPacketStatService;

public class DeletPacketSplitThread extends Thread implements Rejectable {
	public static final Log logger =  LogFactory.getLog(DeletPacketSplitThread.class);
	PacketSplitService packetSplitServiceThread = null;
	PacketSplitEntity packetSplitEntityThread = null;
	public DeletPacketSplitThread(PacketSplitService packetSplitService,PacketSplitEntity packetSplitEntity){
		packetSplitServiceThread = packetSplitService;
		packetSplitEntityThread = packetSplitEntity;
	}
	
	public void run(){
		try{
			packetSplitServiceThread.deletePacketSplitItem(packetSplitEntityThread.getId());
		}catch(Exception e){
			logger.error(e);
		}
	}

	@Override
	public void reject() {
		
	}

}

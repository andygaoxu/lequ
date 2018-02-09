package com.lequ.server.bootstrap.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.common.concurrent.ThreadPool;
import com.lequ.server.bootstrap.dao.robpacket.RobPacketDao;
import com.lequ.server.bootstrap.model.PacketSplitEntity;
import com.lequ.server.bootstrap.model.RobPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.threadpool.DeletPacketSplitThread;
import com.lequ.server.bootstrap.threadpool.RobPacketStatfThread;
import com.lequ.server.bootstrap.threadpool.SendPacketStatfThread;

@Service
public class RobPacketService {

	public static final Log logger = LogFactory.getLog(RobPacketService.class);
	@Autowired
	RobPacketDao robPacketDao;
	@Autowired
	RobPacketStatService robPacketStatService;
	@Autowired
	SendPacketService sendPacketService;
	@Autowired
	PacketSplitService packetSplitService;
	@Autowired
	UserBalanceService userBalanceService;

	public boolean insertRobPacket(RobPacketEntity robPacketEntity,
			SendPacketEntity packetEntity,PacketSplitEntity packetSplitEntity) {
		try {
			robPacketDao.insert(robPacketEntity);
			logger.info("[PacketRobbed]-[packetId:"+packetSplitEntity.getPacketId()+"]-[openId:"+robPacketEntity.getOpenId()+"]-[splitId:"+packetSplitEntity.getSplitId()+"]-[amount:"+packetSplitEntity.getAmount()+"]");
			// 更新被抢红包的已抢包数量
			packetEntity
					.setRobbedNumber(packetEntity.getRobbedNumber() < packetEntity
							.getNumber() ? packetEntity.getRobbedNumber() + 1
							: packetEntity.getNumber());
			packetEntity
					.setRobbedState(packetEntity.getRobbedNumber() < packetEntity
							.getNumber() ? "1" : "0");// 0是包已抢完，1正在进行中
			sendPacketService.updateSendPacketRobbed(packetEntity);
			// 第二步，去交给异步线程生成发送统计,同一个用户抢包不会出现并发，这是采用多线程异步统计的前提
			ThreadPool.addSendPacketThreadPoolTask(new DeletPacketSplitThread(packetSplitService,packetSplitEntity));
			ThreadPool.addRobPacketThreadPoolTask(new RobPacketStatfThread(robPacketStatService, userBalanceService,robPacketEntity));
		} catch (Exception e) {
			logger.error(e);
			return false;

		}
		return true;

	}

	public List<RobPacketEntity> queryList(String openId) {
		List<RobPacketEntity> list = new ArrayList<RobPacketEntity>();
		try {
			list = robPacketDao.queryList(openId);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public RobPacketEntity queryPacket(String id) {
		RobPacketEntity packet = new RobPacketEntity();
		try {
			packet = robPacketDao.queryObject(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return packet;
	}

	public List<RobPacketEntity> queryListByPacketId(String packetId) {
		List<RobPacketEntity> list = new ArrayList<RobPacketEntity>();
		try {
			list = robPacketDao.queryListByPacketId(packetId);
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
}

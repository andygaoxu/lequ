package com.lequ.server.bootstrap.web;

import com.lequ.server.bootstrap.model.RobPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.service.RobPacketService;
import com.lequ.server.bootstrap.service.SendPacketService;
import com.lequ.server.bootstrap.util.UUIDUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "sendpacket")
public class SendPacketCLR {
	public static final Log logger = LogFactory.getLog(SendPacketCLR.class);

	@Resource
	SendPacketService sendPacketService;
	@Resource
	RobPacketService robPacketService;

	@RequestMapping(value = "/insertPacket")
	public Object insertPacket(SendPacketEntity sendPacketEntity,
			HttpServletRequest request, HttpServletResponse response) {
		boolean result = true;
		try {
			sendPacketEntity
					.setCreateTime(sendPacketEntity.getCreateTime() != null ? sendPacketEntity
							.getCreateTime() : (new Date()));
			sendPacketEntity.setPacketId(UUIDUtil.getUUID32());
			result = sendPacketService.insertSendPacket(sendPacketEntity);

		} catch (Exception e) {
			logger.error(e);
			result = false;
		}

		return result;
	}

	/**根据自增Id获取发包信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryPacket")
	public SendPacketEntity queryPacket(String id) {
		SendPacketEntity sendPacketEntity = new SendPacketEntity();
		try {
			sendPacketEntity = sendPacketService.queryPacket(id);
			//需要同时把抢包的列表一起拿出来展示，包括退款、抢包列表。
			if (sendPacketEntity != null
					&& sendPacketEntity.getPacketId() != null
					&& sendPacketEntity.getPacketId() != "") {
				List<RobPacketEntity> robPacketList = robPacketService
						.queryListByPacketId(sendPacketEntity.getPacketId());

				sendPacketEntity.setRobPacketList(robPacketList);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return sendPacketEntity;
	}
	/**根据packetId获取发包信息
	 * @param packetId
	 * @return
	 */
	@RequestMapping(value = "/queryPById")
	public SendPacketEntity queryPacketByPacketId(String packetId) {
		SendPacketEntity sendPacketEntity = new SendPacketEntity();
		try {
			sendPacketEntity = sendPacketService.queryPacketById(packetId);
			//需要同时把抢包的列表一起拿出来展示，包括退款、抢包列表。
			if (sendPacketEntity != null
					&& sendPacketEntity.getPacketId() != null
					&& sendPacketEntity.getPacketId() != "") {
				
				List<RobPacketEntity> robPacketList = robPacketService
						.queryListByPacketId(packetId);

				sendPacketEntity.setRobPacketList(robPacketList);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return sendPacketEntity;
	}
	@RequestMapping(value = "/queryPacketList")
	public List<SendPacketEntity> queryPacketList(String openId) {
		List<SendPacketEntity> lsit = new ArrayList<SendPacketEntity>();
		try {
			lsit = sendPacketService.queryList(openId);
		} catch (Exception e) {
			logger.error(e);
		}
		return lsit;
	}

}

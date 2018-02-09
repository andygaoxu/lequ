package com.lequ.server.bootstrap.dao.sendpacket;

import com.lequ.server.bootstrap.dao.BaseDao;
import com.lequ.server.bootstrap.model.SendPacketEntity;



public interface SendPacketDao extends BaseDao<SendPacketEntity> {

	public SendPacketEntity queryObjectByPacketId(Object packetId);
}

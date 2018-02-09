package com.lequ.server.bootstrap.dao.packet;

import java.util.List;

import com.lequ.server.bootstrap.dao.BaseDao;
import com.lequ.server.bootstrap.model.PacketSplitEntity;

public interface PacketSplitDao extends BaseDao<PacketSplitEntity> {

	public PacketSplitEntity queryObjectByPS(Object packetId,Object splitid);
	
	public void insertByBatch(List<PacketSplitEntity> packetSplitList);
	
}

package com.lequ.server.bootstrap.dao.robpacket;





import java.util.List;

import com.lequ.server.bootstrap.dao.BaseDao;
import com.lequ.server.bootstrap.model.RobPacketEntity;



public interface RobPacketDao extends BaseDao<RobPacketEntity> {

	public List<RobPacketEntity> queryListByPacketId(String packetId);
	
}

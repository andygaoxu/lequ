package com.lequ.server.bootstrap.threadpool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lequ.common.concurrent.Rejectable;
import com.lequ.server.bootstrap.model.RobPacketEntity;
import com.lequ.server.bootstrap.model.RobPacketStatEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketStatEntity;
import com.lequ.server.bootstrap.model.UserBalanceEntity;
import com.lequ.server.bootstrap.service.RobPacketStatService;
import com.lequ.server.bootstrap.service.SendPacketStatService;
import com.lequ.server.bootstrap.service.UserBalanceService;

public class RobPacketStatfThread extends Thread implements Rejectable {
	public static final Log logger =  LogFactory.getLog(RobPacketStatfThread.class);

	RobPacketStatService robPacketStatServiceThread = null;
	RobPacketEntity robPacketEntityThread = null;
	UserBalanceService userBalanceServiceThread = null;
	String openId = null;
	double amount = 0; 
	public RobPacketStatfThread(RobPacketStatService robPacketStatService,UserBalanceService userBalanceService,RobPacketEntity robPacketEntity){
		robPacketStatServiceThread = robPacketStatService;
		userBalanceServiceThread = userBalanceService;
		robPacketEntityThread = robPacketEntity;
		openId = robPacketEntity.getOpenId();
		amount = robPacketEntity.getAmount();
	}
	
	public void run(){
		//自己统计自己的钱，不会出现并发，所以理论上是幂等操作
		RobPacketStatEntity robPacketStatEntityByOpenId = robPacketStatServiceThread.queryPacketStat(openId);
		try{
			if(robPacketStatEntityByOpenId!=null&&robPacketStatEntityByOpenId.getTotalAmount()>0){
				RobPacketStatEntity  robPacketStatEntity = new  RobPacketStatEntity();
				robPacketStatEntity.setOpenId(openId);
				robPacketStatEntity.setTotalAmount((robPacketStatEntityByOpenId.getTotalAmount()+amount));
				robPacketStatEntity.setTotalNumber((robPacketStatEntityByOpenId.getTotalNumber()+1));
				robPacketStatServiceThread.updatePacketStat(robPacketStatEntity);
			}else{
				robPacketStatEntityByOpenId = new  RobPacketStatEntity();
				robPacketStatEntityByOpenId.setOpenId(openId);
				robPacketStatEntityByOpenId.setTotalAmount(amount);
				robPacketStatEntityByOpenId.setTotalNumber(1);
				robPacketStatServiceThread.insertRobPacketStat(robPacketStatEntityByOpenId);
			}
			//退款有可能和抢包冲突，但可以进行二次校验完成对款和抢包的余额计算
			UserBalanceEntity userBalanceEntity = userBalanceServiceThread.queryBalance(openId);
			try{
				if(userBalanceEntity!=null&&userBalanceEntity.getTotalAmount()>0){
					userBalanceEntity.setOpenId(openId);
					userBalanceEntity.setTotalAmount(userBalanceEntity.getTotalAmount()+amount);
					userBalanceServiceThread.updateBalance(userBalanceEntity);
					
				}else{
					userBalanceEntity = new UserBalanceEntity();
					userBalanceEntity.setOpenId(openId);
					userBalanceEntity.setTotalAmount(amount);
					userBalanceServiceThread.insertUserBalance(userBalanceEntity);
				}
				
			}catch(Exception e){
				logger.error("==> UserBalanceStat fail!"+e);
			}
			
		}catch(Exception e){
			logger.error("==> RobPacketStat fail!"+e);
		}
	}

	@Override
	public void reject() {
		
	}

}

package com.lequ.server.bootstrap.service;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.common.concurrent.ThreadPool;
import com.lequ.server.bootstrap.dao.userwithdrawal.UserWithdrawalDao;
import com.lequ.server.bootstrap.model.UserWithdrawalEntity;
import com.lequ.server.bootstrap.service.weixin.WechatPaymentService;
import com.lequ.server.bootstrap.threadpool.SendPacketStatfThread;
import com.lequ.server.bootstrap.threadpool.UserWithdrawaThread;




@Service
public class UserWithdrawalService {

    public static final Log logger =  LogFactory.getLog(UserWithdrawalService.class);
    @Autowired
    UserWithdrawalDao userWithdrawalDao;
    @Autowired
    WechatPaymentService weiXinPaymentService;
    
    public boolean insertUserWithdrawal(UserWithdrawalEntity userWithdrawalEntity){
    	try{
    		userWithdrawalDao.insert(userWithdrawalEntity);
    		//发起提现,商家给用户打钱
    		ThreadPool.addUserBalanceThreadPoolTask(new UserWithdrawaThread(userWithdrawalEntity,weiXinPaymentService));
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
    public List<UserWithdrawalEntity> queryList(String openId){
    	List<UserWithdrawalEntity> list = new ArrayList<UserWithdrawalEntity>();
    	try{
    		list = userWithdrawalDao.queryList(openId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return list;
    }
    
    public UserWithdrawalEntity queryPacket(String id){
    	UserWithdrawalEntity packet = new UserWithdrawalEntity();
    	try{
    		packet = userWithdrawalDao.queryObject(id);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return packet;
    }
}

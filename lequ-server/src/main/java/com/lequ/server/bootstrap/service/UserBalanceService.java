package com.lequ.server.bootstrap.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.server.bootstrap.dao.userbalance.UserBalanceDao;
import com.lequ.server.bootstrap.model.UserBalanceEntity;



@Service
public class UserBalanceService {

    public static final Log logger =  LogFactory.getLog(UserBalanceService.class);
    @Autowired
    UserBalanceDao userBalanceDao;
    
    public boolean insertUserBalance(UserBalanceEntity userBalanceEntity){
    	try{
    		userBalanceDao.insert(userBalanceEntity);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
    
    public UserBalanceEntity queryBalance(String openId){
    	UserBalanceEntity userBalance = new UserBalanceEntity();
    	try{
    		userBalance = userBalanceDao.queryObject(openId);
    	}catch(Exception e){
    		logger.error(e); 	
    	}
		return userBalance;
    }
    public boolean updateBalance(UserBalanceEntity userBalanceEntity){
    	try{
    		userBalanceDao.update(userBalanceEntity);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
        	
    	}
		return true;
    	
    }
}

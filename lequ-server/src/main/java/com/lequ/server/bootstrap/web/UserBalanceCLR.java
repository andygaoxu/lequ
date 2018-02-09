package com.lequ.server.bootstrap.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lequ.server.bootstrap.model.UserBalanceEntity;
import com.lequ.server.bootstrap.service.UserBalanceService;




@RestController
@RequestMapping(value = "userbalance")
public class UserBalanceCLR {
    public static final Log logger = LogFactory.getLog(UserBalanceCLR.class);

    @Resource
    UserBalanceService userBalanceService;
   
    @RequestMapping(value = "/insertUserBalance")
    public Object insertUserBalance(UserBalanceEntity userBalanceEntity, HttpServletRequest request, HttpServletResponse response) {
    	boolean result = true;
    	try{
    		result = userBalanceService.insertUserBalance(userBalanceEntity);
    		
    	}catch(Exception e){
    		logger.error(e);
    		result = false;
    	}
    	
		return result;
    	
    }
   
    @RequestMapping(value = "/queryUserBalance")
    public  UserBalanceEntity queryUserBalance(String openId) {
    	UserBalanceEntity userBalanceEntity = new UserBalanceEntity();
    	try{
    		userBalanceEntity = userBalanceService.queryBalance(openId);
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return userBalanceEntity;
	}
   

}

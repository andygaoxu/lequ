package com.lequ.server.bootstrap.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lequ.server.bootstrap.model.UserBalanceEntity;
import com.lequ.server.bootstrap.model.UserWithdrawalEntity;
import com.lequ.server.bootstrap.service.UserBalanceService;
import com.lequ.server.bootstrap.service.UserWithdrawalService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping(value = "userwithdrawal")
public class UserWithdrawaCLR {
    public static final Log logger = LogFactory.getLog(UserWithdrawaCLR.class);

    @Resource
    UserWithdrawalService userWithdrawalService;
    @Resource
    UserBalanceService userBalanceService;
   
    @RequestMapping(value = "/insertWithdrawal")
    public Object insertUserWithdrawal(UserWithdrawalEntity userWithdrawalEntity, HttpServletRequest request, HttpServletResponse response) {
    	boolean result = true;
    	try{
    		userWithdrawalEntity.setWithdrawalStartDate(new Date());
    		result = userWithdrawalService.insertUserWithdrawal(userWithdrawalEntity);

    		try{
    			UserBalanceEntity userBalanceEntity = new UserBalanceEntity();
    			userBalanceEntity.setTotalAmount(userWithdrawalEntity.getTotalAmount()-userWithdrawalEntity.getAmount());
    			userBalanceEntity.setOpenId(userWithdrawalEntity.getOpenId());
    			userBalanceService.updateBalance(userBalanceEntity);
    		}catch(Exception e){
    			logger.error(""+e);
    			return false;
    		}   		
    	}catch(Exception e){
    		logger.error(""+e);
    		return false;
    	}
    	
		return result;
    }
   
    @RequestMapping(value = "/queryWithdrawal")
    public  UserWithdrawalEntity queryPacket(String id) {
    	UserWithdrawalEntity userWithdrawalEntity = new UserWithdrawalEntity();
    	try{
    		userWithdrawalEntity = userWithdrawalService.queryPacket(id);
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return userWithdrawalEntity;
	}
    @RequestMapping(value = "/queryWithdrawalList")
    public  List<UserWithdrawalEntity> queryPacketList(String openId) {
    	List<UserWithdrawalEntity> lsit = new ArrayList<UserWithdrawalEntity>();
    	try{
    		lsit = userWithdrawalService.queryList(openId);
    	}catch(Exception e){
    		logger.error(e);
    	}
    	return lsit;
	}

}

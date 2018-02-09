package com.lequ.server.bootstrap.threadpool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lequ.common.concurrent.Rejectable;
import com.lequ.server.bootstrap.model.UserWithdrawalEntity;
import com.lequ.server.bootstrap.service.weixin.WechatPaymentService;


public class UserWithdrawaThread extends Thread implements Rejectable {
	public static final Log logger =  LogFactory.getLog(UserWithdrawaThread.class);
	
	private WechatPaymentService weiXinPaymentServiceThread;
	private UserWithdrawalEntity userWithdrawalEntityTrerad;
	
	public UserWithdrawaThread(UserWithdrawalEntity userWithdrawalEntity,WechatPaymentService weiXinPaymentService){
		weiXinPaymentServiceThread = weiXinPaymentService;
		userWithdrawalEntityTrerad = userWithdrawalEntity;
	}
	
	public void run(){
		try{
			
		}catch(Exception e){
			logger.error(e);
		}
	}

	@Override
	public void reject() {
		
	}

}

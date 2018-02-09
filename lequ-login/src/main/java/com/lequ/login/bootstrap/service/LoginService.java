package com.lequ.login.bootstrap.service;


import com.lequ.login.bootstrap.dao.userinfo.UserInfoDao;
import com.lequ.login.bootstrap.model.UserInfoEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class LoginService {

    public static final Log logger =  LogFactory.getLog(LoginService.class);
    @Autowired
    UserInfoDao userInfoDao;
    
    /**插入登錄成功用戶
     * @param userInfo
     * @return
     */
    public boolean insertLoginUserInfo(UserInfoEntity userInfo){
    	try{
    		userInfoDao.insert(userInfo);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
    	}
		return true;
    }
    
    /**根據用戶Id刪除用戶信息
     * @param openId
     * @return
     */
    public boolean delete(String openId){
    	try{
    		userInfoDao.delete(openId);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
    	}
		return true;
    }
    
    /**根據用戶Id更新用戶信息
     * @param userInfo
     * @return
     */
    public boolean update(UserInfoEntity userInfo){
    	try{
    		userInfoDao.update(userInfo);
    	}catch(Exception e){
    		logger.error(e);
    		return false;
    	}
		return true;
    }
}

package com.lequ.login.bootstrap.web;

import com.alibaba.fastjson.JSON;
import com.lequ.login.bootstrap.model.UserInfoEntity;
import com.lequ.login.bootstrap.service.LoginService;
import com.lequ.login.bootstrap.service.WeiXinService;
import com.lequ.login.bootstrap.util.AES128Util;
import com.lequ.login.bootstrap.weixin.DecryptedData;
import com.lequ.login.bootstrap.weixin.EncryptedData;
import com.lequ.login.bootstrap.weixin.WeiXinServerLoginResult;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "weixin")
public class WeiXinLoginCLR {
    public static final Log logger = LogFactory.getLog(WeiXinLoginCLR.class);
    @Resource
    WeiXinService weiXinService;
    @Resource
    LoginService loginService;
   
    @RequestMapping(value = "/login/user")
    public Object loginUser(EncryptedData encryptedData, HttpServletRequest request, HttpServletResponse response) {
    	 DecryptedData decryptedData = null;
    	 UserInfoEntity userInfo = new UserInfoEntity();
    	try{
//    		 WeiXinServerLoginResult loginResult = weiXinService.login(encryptedData);
//    		 /**
//              * 解密加密数据
//              */
//             String jsonDecryptedData = AES128Util.wxDecrypt(encryptedData.getEncryptedData(), loginResult.getSession_key(), encryptedData.getIv());
//             logger.warn(jsonDecryptedData);
//             decryptedData = JSON.parseObject(jsonDecryptedData, DecryptedData.class);
//             
             /**
              * 建立网站的sesssion，将这个session返回给小程序
              */
    		
    		/**
             * 保存每一个登录用户的信息
             */
            decryptedData = new DecryptedData();
     		decryptedData.setNickName("gaoxu");
     		decryptedData.setOpenId("9999999");
    		initUserInfo(decryptedData,userInfo);
    		boolean insertResult = loginService.insertLoginUserInfo(userInfo);
    		if(insertResult)
    			logger.info("insert user ["+userInfo.getOpenId()+"-"+userInfo.getNickName()+"] success!");
    		else
    			logger.info("insert user ["+userInfo.getOpenId()+"-"+userInfo.getNickName()+"] faild!");
    		
    		
    	}catch(Exception e){
    		logger.error(e);
    	}
    	
		return decryptedData;
    	
    }
   
    /**初始化用戶信息
     * @param decryptedData
     * @param userInfo
     */
    private void initUserInfo(DecryptedData decryptedData,
			UserInfoEntity userInfo) {
		userInfo.setOpenId(decryptedData.getOpenId());
		userInfo.setNickName(decryptedData.getNickName());
		userInfo.setCountry(decryptedData.getCountry());
		userInfo.setProvince(decryptedData.getProvince());
		userInfo.setCity(decryptedData.getCity());
		userInfo.setLanguage(decryptedData.getLanguage());
		userInfo.setAvatarUrl(decryptedData.getAvatarUrl()!=null?decryptedData.getAvatarUrl():"");
		userInfo.setCreateDate(new Date());
		
	}

	private void validate(EncryptedData encryptedData, WeiXinServerLoginResult loginResult) {
       // byte[] keybytes = Base64.decode(loginResult.getSession_key());
//        logger.info(keybytes.length + ":" + loginResult.getSession_key());
        //byte[] content = Base64.decode(encryptedData.getEncryptedData());
//        logger.info(content.length + ":" + encryptedData.getEncryptedData());
        //byte[] lv = Base64.decode(encryptedData.getIv());
//        logger.info(lv.length + ":" + encryptedData.getIv());

//        logger.info(encryptedData.getRawData() + loginResult.getSession_key());
//        logger.info(DigestUtils.sha1Hex(encryptedData.getRawData() + loginResult.getSession_key()));
//        logger.info(encryptedData.getSignature());
        if (!DigestUtils.sha1Hex(encryptedData.getRawData() + loginResult.getSession_key()).equals(encryptedData.getSignature())) {
            throw new RuntimeException("校验失败");
        }
    }
}

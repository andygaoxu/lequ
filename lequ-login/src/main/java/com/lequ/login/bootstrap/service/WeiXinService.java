package com.lequ.login.bootstrap.service;

import com.alibaba.fastjson.JSON;
import com.lequ.login.bootstrap.weixin.EncryptedData;
import com.lequ.login.bootstrap.weixin.WeiXinApiService;
import com.lequ.login.bootstrap.weixin.WeiXinServerLoginResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import static com.lequ.login.bootstrap.weixin.WeiXinConstant.LOGIN_REQUEST;

@Service
public class WeiXinService {

    public static final Log logger =  LogFactory.getLog(WeiXinService.class);
    @Resource
    WeiXinApiService weiXinApiService;
    public WeiXinServerLoginResult login(EncryptedData encryptedData){
        String loginResult = null;
        WeiXinServerLoginResult login =null;
        try {
            loginResult = weiXinApiService.doGet(String.format(LOGIN_REQUEST, (encryptedData.getCode())));
            login= JSON.parseObject(loginResult,WeiXinServerLoginResult.class);
            if(StringUtils.isEmpty(login.getOpenid())){
                throw new RuntimeException("登录失败");
            }
            logger.warn(JSON.toJSONString(login));
        } catch (IOException e) {
            throw new RuntimeException("未能获取登录code");
        }

        return login;
    }
}

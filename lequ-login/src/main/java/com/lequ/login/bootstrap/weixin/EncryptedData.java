package com.lequ.login.bootstrap.weixin;

import com.lequ.login.bootstrap.model.CommonRequestObject;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class EncryptedData extends CommonRequestObject{
    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    private String encryptedData;
    /**
     *
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息。
     */
    private String signature;
    /**
     * 加密算法的初始向量
     */
    private String iv;

    private String sessionKey;
    private String appid;
    private String tinyCookie;
    private String rawData;
    @NotEmpty
    @NotNull
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getTinyCookie() {
        return tinyCookie;
    }

    public void setTinyCookie(String tinyCookie) {
        this.tinyCookie = tinyCookie;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}

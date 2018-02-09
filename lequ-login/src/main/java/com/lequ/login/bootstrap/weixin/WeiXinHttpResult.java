package com.lequ.login.bootstrap.weixin;

/**
 * 这个主要用于向微信服务器发起的http请求
 */
public class WeiXinHttpResult {
    private long code;
    private String data;

    public WeiXinHttpResult(Integer code, String data) {
        this.code = code;
        this.data = data;
    }

    public WeiXinHttpResult() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
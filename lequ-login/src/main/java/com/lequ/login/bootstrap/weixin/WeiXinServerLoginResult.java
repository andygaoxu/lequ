package com.lequ.login.bootstrap.weixin;

/**
 *  这个是获取session_id和openid的结果，注意错误的处理
 * @author liushun
 *         2017/1/12.
 */
public class WeiXinServerLoginResult {
    /**
     * openid	用户唯一标识
     session_key	会话密钥
     */
    private String openid;
    private String session_key;
    /**
     * //错误时返回JSON数据包(示例为Code无效)
     {
     "errcode": 40029,
     "errmsg": "invalid code"
     }
     */
    private long errcode = 0l;
    private String errmsg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public long getErrcode() {
        return errcode;
    }

    public void setErrcode(long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}

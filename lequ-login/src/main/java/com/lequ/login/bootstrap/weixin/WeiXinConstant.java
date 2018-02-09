package com.lequ.login.bootstrap.weixin;


/**
 * @author gaoxu
 *
 */
public class WeiXinConstant {
    private WeiXinConstant(){
        throw new UnsupportedOperationException("No instance");
    }
    public static final String APPID = "wx1d0aa01dbc6678a4";
    private static final String APP_SECRET = "888b609dfaa6d682485e449b5dafed46";
    /**
     * APPID	是	小程序唯一标识
        secret	是	小程序的 app secret
        js_code	是	登录时获取的 code
     grant_type	是	填写为 authorization_code
     */
    public static final String LOGIN_REQUEST ="https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+APP_SECRET+"&js_code=%s&grant_type=authorization_code";
    public static final String ACCESS_TOKEN_REQUEST ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APP_SECRET;


    /**
     *
     */
    public static final String SIMPLE = "SIMPLE_";
    public static final String SESSION = "session_";
    public static final String ACCESS_TOKEN = "access_token_";
    public static final String TINY_COOKIE_PREFIX = "AAAAAAAAAAAAAAA";
    //微信登录用到的唯一标识，用于替换掉原微信的SNSUserId
    public static final String SNS_UNION_ID_KEY  = "SNSUnionId";

    //区别微信和微商城来源的标志，因为它们用的是用一个whereFrom，所以多加一个参数来区分
    public static final String SNS_REGISTER_SOURCE_KEY  = "registerSource";

    /**
     * 用户来源
     */
    public static final String LOGIN_USER_FROM  = "wechat";
    /**
     * 调用渠道
     */
//    public static final String INVOKE_CHANNEL  = "mobileNewBiz";
    public static final String INVOKE_CHANNEL  = "weApp";
    /**
     * 用户等级调用的来源
     */
    public static final String USER_GRADE_FROM  = "FrontGroup";
    /**
     * 存储cookie的地方
     */
    public static final String MAIN_DOMAIN  = "gome.com.cn";

}

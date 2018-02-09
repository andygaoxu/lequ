package com.lequ.server.bootstrap.service.weixin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lequ.server.bootstrap.constant.ConfigConstant;
import com.lequ.server.bootstrap.dao.pay.PayOrderDao;
import com.lequ.server.bootstrap.model.PayOrderEntity;

@Service
public class WechatPaymentService {
	public static final Log logger = LogFactory
			.getLog(WechatPaymentService.class);
	@Autowired
	PayOrderDao payOrderDao;

	public int savePayOrder(PayOrderEntity payOrderEntity) {
		int id = 0;
		try {
			id = payOrderDao.insert(payOrderEntity);
		} catch (Exception e) {
			logger.error("" + e);
		}
		return id;
	}

	public PayOrderEntity queryPayOrder(int id) {

		PayOrderEntity payOrderEntity = payOrderDao.queryObject(id);
		return payOrderEntity;
	}
	public PayOrderEntity queryPayOrderByTradeNo(String out_trade_no) {

		PayOrderEntity payOrderEntity = payOrderDao.queryObjectByOutTradeNo(out_trade_no);
		return payOrderEntity;
	}

	public static String getXmlData(String body, String nonce_str,
			String tradeNo, String ip, BigDecimal totla, String openid)
			throws Exception {

		Map<String, Object> signMap = new HashMap<String, Object>();
		signMap.put("appid", ConfigConstant.APPID);// 应用APPID
		signMap.put("nonce_str", nonce_str);// 随机数
		signMap.put("body", body);// 商品描述
		signMap.put("mch_id", ConfigConstant.MCH_ID);// 微信支付商户号
		signMap.put("notify_url", "http://域名/CyWechatPay/paysuccess.do");// 异步通知回调地址
		signMap.put("out_trade_no", tradeNo);// 订单号
		signMap.put("total_fee", totla.multiply(new BigDecimal(100)).intValue());// 总金额
		signMap.put("trade_type", "JSAPI");// 支付类型
		signMap.put("spbill_create_ip", ip);// 客户端IP地址
		signMap.put("openid", openid);// 支付用户的唯一标识

		StringBuffer xml = new StringBuffer();
		xml.append("<xml>");
		xml.append("<appid>" + signMap.get("appid") + "</appid>");// 应用ID
		xml.append("<body>" + signMap.get("body") + "</body>");
		xml.append("<mch_id>" + signMap.get("mch_id") + "</mch_id>");// 微信支付分配的商户号
		xml.append("<nonce_str>" + signMap.get("nonce_str") + "</nonce_str>");// 随机字符串，不长于32位。
		xml.append("<notify_url>" + signMap.get("notify_url") + "</notify_url>");// 接收微信支付异步通知回调地址
		xml.append("<openid>" + signMap.get("openid") + "</openid>");// 用户的openid
																		// 唯一标示，用户授权时或的
		xml.append("<out_trade_no>" + signMap.get("out_trade_no")
				+ "</out_trade_no>");// 订单号
		xml.append("<spbill_create_ip>" + signMap.get("spbill_create_ip")
				+ "</spbill_create_ip>");// 用户端实际ip
		xml.append("<total_fee>" + signMap.get("total_fee") + "</total_fee>");// 订单总金额，单位为分
		xml.append("<trade_type>" + signMap.get("trade_type") + "</trade_type>");// 支付类型
		xml.append("<sign>"
//				+ WxTool.getSignUtil(signMap, ConfigConstant.APP_SECRECT)
				+ "</sign>");// 签名
		xml.append("</xml>");
		return xml.toString();
	}

	public void updatePayOrder(PayOrderEntity payOrderEntity) {
		payOrderDao.update(payOrderEntity);
	}

}

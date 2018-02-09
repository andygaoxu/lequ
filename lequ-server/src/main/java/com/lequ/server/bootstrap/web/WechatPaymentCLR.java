package com.lequ.server.bootstrap.web;

import com.lequ.server.bootstrap.model.PayOrderEntity;
import com.lequ.server.bootstrap.model.RobPacketEntity;
import com.lequ.server.bootstrap.model.SendPacketEntity;
import com.lequ.server.bootstrap.service.RobPacketService;
import com.lequ.server.bootstrap.service.SendPacketService;
import com.lequ.server.bootstrap.service.weixin.WechatPaymentService;
import com.lequ.server.bootstrap.util.DateTimeUtil;
import com.lequ.server.bootstrap.util.UUIDUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "WechatPay")
public class WechatPaymentCLR {
	public static final Log logger = LogFactory.getLog(WechatPaymentCLR.class);
	@Resource
	WechatPaymentService wechatPaymentService;

	@RequestMapping(value = "/savePayOrder")
	public Object savePayOrder(PayOrderEntity payOrderEntity,
			HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		try {
			String out_trade_no = UUIDUtil.getOrderIdByUUId();
			payOrderEntity.setOut_trade_no(out_trade_no);
			payOrderEntity.setTime_start(DateTimeUtil.DateToYYMMDDHHMMSS());
			result = wechatPaymentService.savePayOrder(payOrderEntity);
		} catch (Exception e) {
			logger.error("" + e);
		}
		return result;
	}

	@RequestMapping(value = "/unifiedorder")
	public Object unifiedorder(int id, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PayOrderEntity payOrderEntiy = wechatPaymentService.queryPayOrder(id);
			String nonce_str = UUIDUtil.getUUID32();
			
			
		} catch (Exception e) {
			logger.error("" + e);
		}

		return map;
	}
	@RequestMapping(value = "/paystatus")
	public void payStatusPush(HttpServletRequest request,
			HttpServletResponse response) {
		try {

            PrintWriter print = null;
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");
            //告诉编译器忽略 unchecked 警告信息，如使用List，ArrayList等未进行参数化产生的警告信息。
            @SuppressWarnings("unchecked")
            Map<Object, Object> map = null;//XMLUtil.doXMLParse(result);
            //return_code为微信返回的状态码，SUCCESS表示支付成功
            //return_msg 如非空，为错误原因 签名失败 参数格式校验错误
            logger.info("微信返回的信息："+map.toString());
            if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")
                    && map.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
                String tradeNo = map.get("out_trade_no").toString();
                //这里做出判断，防止微信服务的多次调用这里，造成一次支付，生成多个订单
                PayOrderEntity payOrder =  wechatPaymentService.queryPayOrderByTradeNo(tradeNo);
                if(payOrder != null && payOrder.getPay_status().equals("0")){
                	try{
                		payOrder.setPay_status("1");
                    	wechatPaymentService.updatePayOrder(payOrder);//更新订单状态
                	}catch(Exception e){
                		logger.info("");
                	}
                    //该订单已经支付成功，直接return
                    response.getWriter().write(
                            "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
                    
                }else{
                	logger.info("[PayTradeNo:"+tradeNo+"] The order status has been processed!");
                }
            }
            response.getWriter().write(
                    "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");

        }catch (Exception e) {
        	logger.error(""+e);
        }

	}
}

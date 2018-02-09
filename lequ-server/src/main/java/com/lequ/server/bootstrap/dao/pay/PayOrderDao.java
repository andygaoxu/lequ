package com.lequ.server.bootstrap.dao.pay;

import com.lequ.server.bootstrap.dao.BaseDao;
import com.lequ.server.bootstrap.model.PayOrderEntity;



public interface PayOrderDao extends BaseDao<PayOrderEntity> {

	public PayOrderEntity queryObjectByOutTradeNo(Object out_trade_no);
}

package com.wjl.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wjl.common.pojo.TaotaoResult;
import com.wjl.common.util.HttpClientUtil;
import com.wjl.common.util.JsonUtils;
import com.wjl.portal.pojo.Order;

/**
 * 用户点击创建订单的service
 * @author wujiale
 * 2017-11-04 上午2:13:06
 */
@Service
public class OrderServiceImpl implements OrderService{
	
	@Value("${CreateOrderUrl}")
	private String CreateOrderUrl;

	@Override
	public String createOrder(Order order) {
		String json = HttpClientUtil.doPostJson(CreateOrderUrl,JsonUtils.objectToJson(order));
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		if (taotaoResult.getStatus() == 200) {
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}
}

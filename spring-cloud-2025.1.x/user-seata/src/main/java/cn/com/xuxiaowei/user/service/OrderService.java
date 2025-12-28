package cn.com.xuxiaowei.user.service;

import cn.com.xuxiaowei.user.dto.OrderRequest;

public interface OrderService {

	void order(OrderRequest request);

	int count(String commodityCode);

}

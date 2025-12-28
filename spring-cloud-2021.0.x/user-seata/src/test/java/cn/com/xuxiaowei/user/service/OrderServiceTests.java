package cn.com.xuxiaowei.user.service;

import cn.com.xuxiaowei.user.dto.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class OrderServiceTests {

	@Autowired
	private OrderService orderService;

	@Test
	void count() {
		String commodityCode = "A001";
		Integer count = orderService.count(commodityCode);
		assertNotNull(count);
	}

	@Test
	void order() {
		String commodityCode = "A001";
		String userId = "xuxiaowei";
		int count1 = orderService.count(commodityCode);

		int count = 1;
		int money = 1;
		OrderRequest request = new OrderRequest();
		request.setUserId(userId);
		request.setCommodityCode(commodityCode);
		request.setCount(count);
		request.setMoney(money);

		orderService.order(request);

		int count2 = orderService.count(commodityCode);
		assertEquals(count1 + 1, count2);
	}

}

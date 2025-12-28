package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.dto.SeataRequest;
import cn.com.xuxiaowei.user.service.AccountService;
import cn.com.xuxiaowei.user.service.OrderService;
import cn.com.xuxiaowei.user.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
@SpringBootTest
class SeataRestControllerTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private StorageService storageService;

	@Test
	void ok() {
		String userId = "xuxiaowei";
		String commodityCode = "A001";
		int count = -2;
		int money = -1;

		Integer money1 = accountService.getMoney(userId);
		int orderCount1 = orderService.count(commodityCode);
		Integer storageCount1 = storageService.count(commodityCode);

		{
			SeataRequest request = new SeataRequest();
			request.setCommodityCode(commodityCode);
			request.setUserId(userId);
			request.setCount(count);
			request.setMoney(money);

			HttpEntity<SeataRequest> httpEntity = new HttpEntity<>(request);

			String url = "http://127.0.0.1:5026/seata";
			String value = new RestTemplate().postForObject(url, httpEntity, String.class);
			log.info(value);
			assertEquals("{\"code\":200}", value);
		}

		Integer money2 = accountService.getMoney(userId);
		int orderCount2 = orderService.count(commodityCode);
		Integer storageCount2 = storageService.count(commodityCode);

		assertEquals(money1 + money, money2);
		assertEquals(orderCount1 + 1, orderCount2);
		assertEquals(storageCount1 + count, storageCount2);
	}

	/**
	 * 测试分布式事务：数据回滚
	 */
	@Test
	void error() {
		String userId = "xuxiaowei";
		String commodityCode = "A001";

		// 第一：扣款成功
		int money = -1;

		// 第二：扣库存失败
		int count = -20000000;

		Integer money1 = accountService.getMoney(userId);
		int orderCount1 = orderService.count(commodityCode);
		Integer storageCount1 = storageService.count(commodityCode);

		{
			SeataRequest request = new SeataRequest();
			request.setCommodityCode(commodityCode);
			request.setUserId(userId);
			request.setCount(count);
			request.setMoney(money);

			HttpEntity<SeataRequest> httpEntity = new HttpEntity<>(request);

			String url = "http://127.0.0.1:5026/seata";
			assertThrows(Exception.class, () -> {
				try {
					new RestTemplate().postForObject(url, httpEntity, Map.class);
				}
				catch (Exception e) {
					log.error("分布式事务异常：", e);
					String message = e.getMessage();
					assertThat(message).doesNotContain("No instances available");
					assertThat(message).doesNotContain("I/O error on POST request");
					assertThat(message).contains("数量不足");
					throw e;
				}
			});

		}

		Integer money2 = accountService.getMoney(userId);
		int orderCount2 = orderService.count(commodityCode);
		Integer storageCount2 = storageService.count(commodityCode);

		// 第三：数据回滚，数据库保持原样
		assertEquals(money1, money2);
		assertEquals(orderCount1, orderCount2);
		assertEquals(storageCount1, storageCount2);
	}

}

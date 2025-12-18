package cn.com.xuxiaowei.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();

	@LocalServerPort
	private int port;

	@Test
	void properties() {
		String url = "http://localhost:" + port + "/properties";
		Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
		log.info(String.valueOf(map));

		assertNotNull(map);

		assertNotNull(map.get("password"));
		assertEquals("xuxiaowei.com.cn", map.get("password"));

		assertNotNull(map.get("test.name"));
		assertEquals("zhangsan", map.get("test.name"));
	}

	@Test
	void gatewayProperties() {
		String url = "http://localhost:5021/user/properties";
		Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
		log.info(String.valueOf(map));

		assertNotNull(map);

		assertNotNull(map.get("password"));
		assertEquals("xuxiaowei.com.cn", map.get("password"));

		assertNotNull(map.get("test.name"));
		assertEquals("zhangsan", map.get("test.name"));
	}

	@Test
	void gatewayMvcProperties() {
		String url = "http://localhost:5022/user/properties";
		Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
		log.info(String.valueOf(map));

		assertNotNull(map);

		assertNotNull(map.get("password"));
		assertEquals("xuxiaowei.com.cn", map.get("password"));

		assertNotNull(map.get("test.name"));
		assertEquals("zhangsan", map.get("test.name"));
	}

}

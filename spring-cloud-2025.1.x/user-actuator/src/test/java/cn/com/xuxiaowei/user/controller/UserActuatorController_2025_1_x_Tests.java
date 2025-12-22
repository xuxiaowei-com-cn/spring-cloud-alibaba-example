package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.properties.TokenProperties;
import cn.com.xuxiaowei.user.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

import static com.alibaba.nacos.client.auth.impl.NacosAuthLoginConstant.ACCESSTOKEN;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserActuatorController_2025_1_x_Tests {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();

	@LocalServerPort
	private int port;

	@Autowired
	private TokenProperties tokenProperties;

	@Autowired
	private UserProperties userProperties;

	@BeforeEach
	void setUp() throws InterruptedException {
		String token = tokenProperties.getToken();
		editPassword(token, "xuxiaowei.com.cn");
		Thread.sleep(1_000);
	}

	@Test
	void properties() throws InterruptedException {
		// 验证配置
		{
			String password = userProperties.getPassword();
			assertNotNull(password);
			assertEquals("xuxiaowei.com.cn", password);

			String url = "http://localhost:" + port + "/properties";
			Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
			log.info(String.valueOf(map));

			assertNotNull(map);

			assertNotNull(map.get("test.name.value"));
			assertEquals("zhangsan", map.get("test.name.value"));

			assertNotNull(map.get("user.env.name"));
			assertEquals("lisi", map.get("user.env.name"));

			assertNotNull(map.get("password"));
			assertEquals("xuxiaowei.com.cn", map.get("password"));

			assertNotNull(map.get("test.name"));
			assertEquals("zhangsan", map.get("test.name"));
		}

		// 刷新配置
		{
			String token = tokenProperties.getToken();

			String password = UUID.randomUUID().toString();
			editPassword(token, password);

			Thread.sleep(1_000);

			assertNotNull(userProperties.getPassword());
			assertEquals(password, userProperties.getPassword());

			editPassword(token, "xuxiaowei.com.cn");

			Thread.sleep(1_000);

			assertNotNull(userProperties.getPassword());
			assertEquals("xuxiaowei.com.cn", userProperties.getPassword());
		}

	}

	void editPassword(String token, String password) {
		String url = "http://127.0.0.1:8080/v3/console/cs/config";
		HttpHeaders headers = new HttpHeaders();
		headers.add(ACCESSTOKEN, token);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.set("dataId", "application-user.yml");
		requestBody.set("group", "DEFAULT_GROUP");
		requestBody.set("groupName", "DEFAULT_GROUP");
		requestBody.set("content", "user:\n" + "  password: " + password);
		requestBody.set("type", "yaml");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(requestBody, headers);
		RestTemplate restTemplate = new RestTemplate();
		Map map = restTemplate.postForObject(url, entity, Map.class);
		log.info(String.valueOf(map));
		assertNotNull(map);
		assertNotNull(map.get("data"));
		assertInstanceOf(Boolean.class, map.get("data"));
		assertTrue((boolean) map.get("data"));
	}

	@Test
	void gatewayServerWebfluxProperties() {
		String url = "http://localhost:5011/user-actuator/properties";
		Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
		log.info(String.valueOf(map));

		assertNotNull(map);

		assertNotNull(map.get("password"));
		assertEquals("xuxiaowei.com.cn", map.get("password"));

		assertNotNull(map.get("test.name"));
		assertEquals("zhangsan", map.get("test.name"));
	}

	@Test
	void gatewayServerWebmvcProperties() {
		String url = "http://localhost:5012/user-actuator/properties";
		Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
		log.info(String.valueOf(map));

		assertNotNull(map);

		assertNotNull(map.get("password"));
		assertEquals("xuxiaowei.com.cn", map.get("password"));

		assertNotNull(map.get("test.name"));
		assertEquals("zhangsan", map.get("test.name"));
	}

}

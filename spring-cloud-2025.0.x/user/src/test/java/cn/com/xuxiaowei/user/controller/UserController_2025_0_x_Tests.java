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

/**
 * UserController 集成测试（Spring Cloud 2025.0.x）。
 * <p>
 * 测试范围：
 * <ul>
 * <li>配置中心（Nacos）属性读取与动态刷新</li>
 * <li>通过 Spring Cloud Gateway（spring-cloud-gateway）访问 user 服务端点</li>
 * <li>通过 Spring Cloud Gateway MVC（spring-cloud-gateway-mvc）访问 user 服务端点</li>
 * </ul>
 * 每个测试方法启动随机端口，通过 {@link RestTemplate} 发起 HTTP 请求。
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserController_2025_0_x_Tests {

	/** 复用的 {@link RestTemplate} 实例，避免在每个测试方法中重复创建。 */
	private static final RestTemplate REST_TEMPLATE = new RestTemplate();

	/** 当前测试应用的随机 HTTP 端口。 */
	@LocalServerPort
	private int port;

	/** Nacos 认证 token 配置，用于调用 Nacos OpenAPI 修改配置。 */
	@Autowired
	private TokenProperties tokenProperties;

	/** User 业务属性配置，包含 {@code password} 等可动态刷新的字段。 */
	@Autowired
	private UserProperties userProperties;

	/**
	 * 每个测试方法执行前将 Nacos 配置中心的 password 重置为固定初始值 {@code xuxiaowei.com.cn}， 确保各测试的起点状态一致。等待
	 * 1 秒让配置刷新生效。
	 */
	@BeforeEach
	void setUp() throws InterruptedException {
		String token = tokenProperties.getToken();
		editPassword(token, "xuxiaowei.com.cn");
		Thread.sleep(1_000);
	}

	/**
	 * 测试配置属性读取与 Nacos 动态刷新。
	 * <ol>
	 * <li>验证本地 {@code /properties} 端点返回的初始配置值与预期一致。</li>
	 * <li>通过 Nacos OpenAPI 修改配置内容，验证 {@code @RefreshScope} 动态刷新后属性值同步更新。</li>
	 * <li>测试完成后将配置恢复为初始值，避免影响其他测试。</li>
	 * </ol>
	 */
	@Test
	void properties() throws InterruptedException {
		// 验证本地配置端点的初始配置值
		{
			// 验证 @ConfigurationProperties 绑定的 password 与 Nacos 配置中心一致
			String password = userProperties.getPassword();
			log.info("@ConfigurationProperties 绑定的 password: {}", password);
			assertNotNull(password);
			assertEquals("xuxiaowei.com.cn", password);

			// 请求 /properties 端点，获取所有配置属性
			String url = "http://localhost:" + port + "/properties";
			Map<?, ?> map = REST_TEMPLATE.getForObject(url, Map.class);
			log.info(String.valueOf(map));

			assertNotNull(map);

			// Nacos extension-configs 加载的 test.name
			assertNotNull(map.get("test.name.value"));
			assertEquals("zhangsan", map.get("test.name.value"));

			// Nacos shared-configs 加载的用户环境配置 user-env.yml
			assertNotNull(map.get("user.env.name"));
			assertEquals("lisi", map.get("user.env.name"));

			// Nacos extension-configs 加载的 user-a1.yml
			assertNotNull(map.get("user.a1.name"));
			assertEquals("wangwu", map.get("user.a1.name"));

			// application-user.yml 中的 password（Nacos 配置中心），验证 @RefreshScope 字段初始值
			assertNotNull(map.get("password"));
			assertEquals("xuxiaowei.com.cn", map.get("password"));

			// 本地 application.yml 中的 test.name
			assertNotNull(map.get("test.name"));
			assertEquals("zhangsan", map.get("test.name"));
		}

		// 通过 Nacos OpenAPI 动态修改配置并验证 @RefreshScope 刷新
		{
			String token = tokenProperties.getToken();

			log.info("刷新前 @ConfigurationProperties 绑定的 password: {}", userProperties.getPassword());

			// 生成随机 password 并通过 Nacos OpenAPI 写入配置中心
			String password = UUID.randomUUID().toString();
			log.info("生成随机 password 并通过 Nacos OpenAPI 写入配置中心: {}", password);
			editPassword(token, password);
			log.info("Nacos OpenAPI 修改配置完成");

			// 等待 Nacos 配置变更通知推送到客户端（@RefreshScope 刷新间隔）
			Thread.sleep(1_000);

			// 验证 @RefreshScope 已将新的 password 值注入到 userProperties
			log.info("期望新 password: {}, 实际 @ConfigurationProperties 绑定的 password: {}", password,
					userProperties.getPassword());
			assertNotNull(userProperties.getPassword());
			assertEquals(password, userProperties.getPassword());

			// 恢复 Nacos 配置为初始值，避免影响其他测试方法
			editPassword(token, "xuxiaowei.com.cn");

			// 等待 Nacos 配置变更通知推送到客户端
			Thread.sleep(1_000);

			// 验证 @RefreshScope 已恢复为初始值
			log.info("期望恢复为初始值: xuxiaowei.com.cn, 实际 @ConfigurationProperties 绑定的 password: {}",
					userProperties.getPassword());
			assertNotNull(userProperties.getPassword());
			assertEquals("xuxiaowei.com.cn", userProperties.getPassword());
		}

	}

	/**
	 * 通过 Nacos OpenAPI 修改 {@code application-user.yml} 的 password 配置，并断言修改成功。
	 * @param token Nacos 认证 accessToken
	 * @param password 新的 password 值
	 */
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

	/**
	 * 测试通过 Spring Cloud Gateway（spring-cloud-gateway，端口 5021）网关路由访问 user 服务的
	 * {@code /user/properties} 端点，验证配置属性与预期一致。
	 */
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

	/**
	 * 测试通过 Spring Cloud Gateway MVC（spring-cloud-gateway-mvc，端口 5022）网关路由访问 user 服务的
	 * {@code /user/properties} 端点，验证配置属性与预期一致。
	 */
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

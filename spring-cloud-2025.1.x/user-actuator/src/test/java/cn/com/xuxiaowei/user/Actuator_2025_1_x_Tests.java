package cn.com.xuxiaowei.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Actuator_2025_1_x_Tests {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();

	@LocalServerPort
	private int port;

	@Test
	void actuator() {
		Map map = REST_TEMPLATE.getForObject("http://localhost:" + port + "/actuator", Map.class);
		log.info(String.valueOf(map));
		assertNotNull(map);
		assertNotNull(map.get("_links"));
	}

}

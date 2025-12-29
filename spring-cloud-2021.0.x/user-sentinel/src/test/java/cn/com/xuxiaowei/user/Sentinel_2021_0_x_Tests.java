package cn.com.xuxiaowei.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class Sentinel_2021_0_x_Tests {

	@Test
	void status() throws InterruptedException {

		// 访问一个接口，处理懒加载问题，否则 sentinel health 可能会一直不正常
		{
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://127.0.0.1:5058/time/now";
			Map map = restTemplate.getForObject(url, Map.class);
			log.info("time: {}", map);
			assertNotNull(map);
			assertNotNull(map.get("now"));
		}

		// 使用 /actuator/health 来测试 sentinel health 是否已经上线
		{
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://127.0.0.1:5058/actuator/health";
			for (int i = 0; i < 5; i++) {
				String string = restTemplate.getForObject(url, String.class);
				log.info("status {}: {}", i, string);
				if (string != null && !string.contains("UNKNOWN")) {
					// 不存在 UNKNOWN，说明已经全部上线
					break;
				}
				Thread.sleep(2_000);
			}

			Map map = restTemplate.getForObject(url, Map.class);
			log.info("status: {}", map);
			assertNotNull(map);
			assertNotNull(map.get("status"));
			assertEquals("UP", map.get("status"));

			Object componentsObj = map.get("components");
			assertNotNull(componentsObj);
			assertInstanceOf(Map.class, componentsObj);
			Map components = (Map) componentsObj;
			Object sentinelObj = components.get("sentinel");
			assertNotNull(sentinelObj);
			assertInstanceOf(Map.class, sentinelObj);
			Map sentinel = (Map) sentinelObj;
			assertNotNull(sentinel.get("status"));
			assertEquals("UP", sentinel.get("status"));
		}
	}

}

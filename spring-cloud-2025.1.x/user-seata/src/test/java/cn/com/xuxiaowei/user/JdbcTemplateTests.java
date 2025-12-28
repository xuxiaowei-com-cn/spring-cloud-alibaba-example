package cn.com.xuxiaowei.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class JdbcTemplateTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void queryForObject() {
		String sql = "select 2 * 3";
		Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
		log.info("查询结果: {}", result);
		assertNotNull(result);
		assertEquals(6, result);
	}

}

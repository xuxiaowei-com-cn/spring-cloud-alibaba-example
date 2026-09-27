package cn.com.xuxiaowei.user.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.TimeZone;

@Configuration
public class ScheduledLockConfig {

	@Bean
	public LockProvider lockProvider(JdbcTemplate jdbcTemplate) {
		// TimeZone zone = TimeZone.getTimeZone("UTC");
		TimeZone zone = TimeZone.getDefault();
		return new JdbcTemplateLockProvider(JdbcTemplateLockProvider.Configuration.builder()
			.withJdbcTemplate(jdbcTemplate)
			.withTimeZone(zone)
			.build());
	}

}

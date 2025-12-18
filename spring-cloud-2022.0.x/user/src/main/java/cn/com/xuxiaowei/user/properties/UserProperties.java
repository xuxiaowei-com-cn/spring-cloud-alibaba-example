package cn.com.xuxiaowei.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @see RefreshScope 可缺省
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "user")
public class UserProperties {

	/**
	 *
	 */
	private String name;

	/**
	 *
	 */
	private String description;

	/**
	 *
	 */
	private String username;

	/**
	 *
	 */
	private String password;

}

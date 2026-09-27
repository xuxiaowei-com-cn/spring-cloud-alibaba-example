package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.properties.TestProperties;
import cn.com.xuxiaowei.user.properties.UserA1Properties;
import cn.com.xuxiaowei.user.properties.UserEnvProperties;
import cn.com.xuxiaowei.user.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserController {

	private UserProperties userProperties;

	private UserEnvProperties userEnvProperties;

	private UserA1Properties userA1Properties;

	private TestProperties testProperties;

	@Value("${test.name}")
	private String testName;

	@Autowired
	public void setUserProperties(UserProperties userProperties) {
		this.userProperties = userProperties;
	}

	@Autowired
	public void setUserEnvProperties(UserEnvProperties userEnvProperties) {
		this.userEnvProperties = userEnvProperties;
	}

	@Autowired
	public void setUserA1Properties(UserA1Properties userA1Properties) {
		this.userA1Properties = userA1Properties;
	}

	@Autowired
	public void setTestProperties(TestProperties testProperties) {
		this.testProperties = testProperties;
	}

	@GetMapping("/properties")
	public Map<String, Object> getUserProperties() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", userProperties.getName());
		map.put("description", userProperties.getDescription());
		map.put("username", userProperties.getUsername());
		map.put("password", userProperties.getPassword());
		map.put("test.name", testProperties.getName());
		map.put("test.name.value", testName);
		map.put("user.env.name", userEnvProperties.getName());
		map.put("user.a1.name", userA1Properties.getName());

		String hostName;
		try {
			// Get the local host instance
			InetAddress localHost = InetAddress.getLocalHost();
			// Get the computer name (hostname)
			hostName = localHost.getHostName();
		}
		catch (UnknownHostException e) {
			log.error("获取 IP 异常：", e);
			hostName = e.getMessage();
		}
		map.put("hostName", hostName);

		return map;
	}

}

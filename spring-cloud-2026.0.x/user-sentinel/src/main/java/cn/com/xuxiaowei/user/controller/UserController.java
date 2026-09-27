package cn.com.xuxiaowei.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

	@GetMapping(value = "/hello")
	@SentinelResource("hello")
	public String hello() {
		return "Hello Sentinel";
	}

}

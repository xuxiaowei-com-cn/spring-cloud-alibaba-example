package cn.com.xuxiaowei.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/time")
public class TimeController {

	@RequestMapping("/now")
	public Map<String, Object> now() {
		return Map.of("now", LocalDateTime.now());
	}

}

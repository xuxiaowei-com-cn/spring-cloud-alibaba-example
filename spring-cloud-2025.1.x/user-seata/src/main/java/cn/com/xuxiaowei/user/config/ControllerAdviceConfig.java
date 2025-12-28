package cn.com.xuxiaowei.user.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@Slf4j
@ControllerAdvice({ "cn.com.xuxiaowei.user" })
public class ControllerAdviceConfig {

	/**
	 * HTTP 返回状态码 设置为 500：用于分布式事务回滚
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Map<String, Object> exception(HttpServletRequest request, HttpServletResponse response,
			Exception exception) {
		String message = exception.getMessage();
		return Map.of("status", 500, "error", message);
	}

}

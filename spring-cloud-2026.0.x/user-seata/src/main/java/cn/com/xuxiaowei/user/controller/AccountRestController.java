package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.dto.AccountMoneyRequest;
import cn.com.xuxiaowei.user.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.core.context.RootContext;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountRestController {

	private final AccountService accountService;

	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * 修改用户金额
	 * @param request 请求参数
	 * @param keyXid 分布式事务ID
	 */
	@PostMapping("/money")
	public Map<String, Object> money(@RequestBody AccountMoneyRequest request,
			@RequestHeader(value = RootContext.KEY_XID, required = false) String keyXid) {
		log.info("分布式事务 {}: {}", RootContext.KEY_XID, keyXid);

		accountService.money(request);
		return Map.of("code", 200);
	}

	@GetMapping("/money/{userId}")
	public int money(@PathVariable String userId) {
		return accountService.getMoney(userId);
	}

}

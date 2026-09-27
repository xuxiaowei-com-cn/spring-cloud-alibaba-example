package cn.com.xuxiaowei.user.service;

import cn.com.xuxiaowei.user.dto.AccountMoneyRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AccountServiceTests {

	@Autowired
	private AccountService accountService;

	@Test
	void getMoney() {
		String userId = "xuxiaowei";
		Integer money = accountService.getMoney(userId);
		assertNotNull(money);
	}

	@Test
	void money_1() {
		String userId = "xuxiaowei";

		Integer money1 = accountService.getMoney(userId);
		assertNotNull(money1);

		int money = 1;
		AccountMoneyRequest request = new AccountMoneyRequest();
		request.setUserId(userId);
		request.setMoney(money);

		accountService.money(request);

		Integer money2 = accountService.getMoney(userId);
		assertNotNull(money2);

		assertEquals(money1 + money, money2);
	}

	@Test
	void money_2() {
		String userId = "xuxiaowei";

		Integer money1 = accountService.getMoney(userId);
		assertNotNull(money1);

		int money = -1;
		AccountMoneyRequest request = new AccountMoneyRequest();
		request.setUserId(userId);
		request.setMoney(money);
		accountService.money(request);

		Integer money2 = accountService.getMoney(userId);
		assertNotNull(money2);

		assertEquals(money1 + money, money2);
	}

	@Test
	void money_3() {
		String userId = "xuxiaowei";
		int money = -10000000;
		AccountMoneyRequest request = new AccountMoneyRequest();
		request.setUserId(userId);
		request.setMoney(money);
		assertThrows(RuntimeException.class, () -> {
			accountService.money(request);
		});
	}

}

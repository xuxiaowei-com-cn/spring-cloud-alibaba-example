package cn.com.xuxiaowei.user.service;

import cn.com.xuxiaowei.user.dto.AccountMoneyRequest;

public interface AccountService {

	void money(AccountMoneyRequest request);

	Integer getMoney(String userId);

}

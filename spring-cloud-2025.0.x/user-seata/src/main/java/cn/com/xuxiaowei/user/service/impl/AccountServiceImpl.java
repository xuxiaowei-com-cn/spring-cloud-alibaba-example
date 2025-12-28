package cn.com.xuxiaowei.user.service.impl;

import cn.com.xuxiaowei.user.dto.AccountMoneyRequest;
import cn.com.xuxiaowei.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void money(AccountMoneyRequest request) {

		String userId = request.getUserId();
		Integer money = request.getMoney();

		if (money == null) {
			return;
		}

		if (money == 0) {
			return;
		}

		if (money > 0) {
			String sql = "update account_tbl set money = money + ? where user_id = ?";
			jdbcTemplate.update(sql, money, userId);
		}
		else {
			String sql = "update account_tbl set money = money - ? where user_id = ? and money >= ?";
			int update = jdbcTemplate.update(sql, -money, userId, -money);
			if (update == 0) {
				throw new RuntimeException("金额不足");
			}
		}

	}

	@Override
	public Integer getMoney(String userId) {
		String sql = "select money from account_tbl where user_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userId);
	}

}

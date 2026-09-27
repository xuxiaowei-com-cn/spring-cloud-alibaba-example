package cn.com.xuxiaowei.user.service.impl;

import cn.com.xuxiaowei.user.dto.OrderRequest;
import cn.com.xuxiaowei.user.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void order(OrderRequest request) {
		Integer count = request.getCount();
		Integer money = request.getMoney();
		String commodityCode = request.getCommodityCode();
		String userId = request.getUserId();
		if (count == null) {
			throw new RuntimeException("订单数量不能为空");
		}
		if (money == null) {
			throw new RuntimeException("订单金额不能为空");
		}
		count = Math.abs(count);
		money = Math.abs(money);
		String sql = "insert into order_tbl (user_id, commodity_code, count, money) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, userId, commodityCode, count, money);
	}

	@Override
	public int count(String commodityCode) {
		String sql = "select count(1) from order_tbl";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

}

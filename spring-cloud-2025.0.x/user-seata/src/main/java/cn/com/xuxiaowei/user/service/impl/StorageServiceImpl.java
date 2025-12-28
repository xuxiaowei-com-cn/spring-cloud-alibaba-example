package cn.com.xuxiaowei.user.service.impl;

import cn.com.xuxiaowei.user.dto.StorageRequest;
import cn.com.xuxiaowei.user.service.StorageService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

	private final JdbcTemplate jdbcTemplate;

	public StorageServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void storage(StorageRequest request) {
		String commodityCode = request.getCommodityCode();
		Integer count = request.getCount();
		if (count == null) {
			return;
		}

		if (count == 0) {
			return;
		}

		if (count > 0) {
			String sql = "update storage_tbl set count = count + ? where commodity_code = ?";
			jdbcTemplate.update(sql, count, commodityCode);
		}
		else {
			String sql = "update storage_tbl set count = count - ? where commodity_code = ? and count >= ?";
			int update = jdbcTemplate.update(sql, -count, commodityCode, -count);
			if (update == 0) {
				throw new RuntimeException("数量不足");
			}
		}
	}

	@Override
	public Integer count(String commodityCode) {
		String sql = "select count from storage_tbl where commodity_code = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, commodityCode);
	}

}

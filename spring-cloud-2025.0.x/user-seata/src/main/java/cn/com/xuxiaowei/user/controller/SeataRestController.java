package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.dto.AccountMoneyRequest;
import cn.com.xuxiaowei.user.dto.OrderRequest;
import cn.com.xuxiaowei.user.dto.SeataRequest;
import cn.com.xuxiaowei.user.dto.StorageRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.core.context.RootContext;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/seata")
public class SeataRestController {

	private final RestTemplate restTemplate;

	public SeataRestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * 测试分布式事务
	 * @param request 请求参数
	 */
	@GlobalTransactional
	@PostMapping
	public Map<String, Object> seata(@RequestBody SeataRequest request) {

		String xid = RootContext.getXID();
		log.info("分布式事务 {}: {}", RootContext.KEY_XID, xid);

		// 扣款
		{
			String url = "http://user-seata/account/money";

			AccountMoneyRequest accountMoneyRequest = new AccountMoneyRequest();
			accountMoneyRequest.setUserId(request.getUserId());
			accountMoneyRequest.setMoney(request.getMoney());

			HttpHeaders httpHeaders = new HttpHeaders();
			HttpEntity<AccountMoneyRequest> httpEntity = new HttpEntity<>(accountMoneyRequest, httpHeaders);

			Map map = restTemplate.postForObject(url, httpEntity, Map.class);
			log.info("扣款结果：{}", map);
		}

		// 扣库存
		{
			String url = "http://user-seata/storage";

			StorageRequest storageRequest = new StorageRequest();
			storageRequest.setCommodityCode(request.getCommodityCode());
			storageRequest.setCount(request.getCount());

			HttpHeaders httpHeaders = new HttpHeaders();
			HttpEntity<StorageRequest> httpEntity = new HttpEntity<>(storageRequest, httpHeaders);

			Map map = restTemplate.postForObject(url, httpEntity, Map.class);
			log.info("扣库存结果：{}", map);
		}

		// 创建订单
		{
			String url = "http://user-seata/order";

			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setUserId(request.getUserId());
			orderRequest.setCommodityCode(request.getCommodityCode());
			orderRequest.setCount(request.getCount());
			orderRequest.setMoney(request.getMoney());

			HttpHeaders httpHeaders = new HttpHeaders();
			HttpEntity<OrderRequest> httpEntity = new HttpEntity<>(orderRequest, httpHeaders);

			Map map = restTemplate.postForObject(url, httpEntity, Map.class);
			log.info("创建订单结果：{}", map);
		}

		return Map.of("code", 200);
	}

}

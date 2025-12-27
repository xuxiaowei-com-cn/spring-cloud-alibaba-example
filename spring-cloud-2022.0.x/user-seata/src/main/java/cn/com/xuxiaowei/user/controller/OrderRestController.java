package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.dto.OrderRequest;
import cn.com.xuxiaowei.user.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderRestController {

	private final OrderService orderService;

	public OrderRestController(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 创建订单
	 * @param request 请求参数
	 * @param keyXid 分布式事务ID
	 */
	@PostMapping
	public Map<String, Object> order(@RequestBody OrderRequest request,
			@RequestHeader(value = RootContext.KEY_XID, required = false) String keyXid) {
		log.info("分布式事务 {}: {}", RootContext.KEY_XID, keyXid);

		orderService.order(request);
		return Map.of("code", 200);
	}

	@GetMapping("/{commodityCode}")
	public int count(@PathVariable String commodityCode) {
		return orderService.count(commodityCode);
	}

}

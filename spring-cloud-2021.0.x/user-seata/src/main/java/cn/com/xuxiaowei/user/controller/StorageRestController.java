package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.dto.StorageRequest;
import cn.com.xuxiaowei.user.service.StorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageRestController {

	private final StorageService storageService;

	public StorageRestController(StorageService storageService) {
		this.storageService = storageService;
	}

	/**
	 * 修改库存数量
	 * @param request 请求参数
	 * @param keyXid 分布式事务ID
	 * @return
	 */
	@PostMapping
	public Map<String, Object> storage(@RequestBody StorageRequest request,
			@RequestHeader(value = RootContext.KEY_XID, required = false) String keyXid) {
		log.info("分布式事务 {}: {}", RootContext.KEY_XID, keyXid);

		storageService.storage(request);
		return Map.of("code", 200);
	}

	@GetMapping("/{commodityCode}")
	public int count(@PathVariable String commodityCode) {
		return storageService.count(commodityCode);
	}

}

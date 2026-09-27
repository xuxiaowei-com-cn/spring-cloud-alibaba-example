package cn.com.xuxiaowei.user.service;

import cn.com.xuxiaowei.user.dto.StorageRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class StorageServiceTests {

	@Autowired
	private StorageService storageService;

	@Test
	void count() {
		String commodityCode = "A001";
		Integer count = storageService.count(commodityCode);
		assertNotNull(count);
	}

	@Test
	void storage_1() {
		String commodityCode = "A001";

		Integer count1 = storageService.count(commodityCode);
		assertNotNull(count1);

		int count = 1;
		StorageRequest request = new StorageRequest();
		request.setCommodityCode(commodityCode);
		request.setCount(count);
		storageService.storage(request);

		Integer count2 = storageService.count(commodityCode);
		assertNotNull(count2);
		assertEquals(count1 + count, count2);
	}

	@Test
	void storage_2() {
		String commodityCode = "A001";

		Integer count1 = storageService.count(commodityCode);
		assertNotNull(count1);

		int count = -1;
		StorageRequest request = new StorageRequest();
		request.setCommodityCode(commodityCode);
		request.setCount(count);
		storageService.storage(request);

		Integer count2 = storageService.count(commodityCode);
		assertNotNull(count2);
		assertEquals(count1 + count, count2);
	}

	@Test
	void storage_3() {
		String commodityCode = "A001";
		int count = -10000000;
		StorageRequest request = new StorageRequest();
		request.setCommodityCode(commodityCode);
		request.setCount(count);
		assertThrows(RuntimeException.class, () -> {
			storageService.storage(request);
		});
	}

}

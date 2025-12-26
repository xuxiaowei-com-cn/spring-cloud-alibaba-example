package cn.com.xuxiaowei.user.service;

import cn.com.xuxiaowei.user.dto.StorageRequest;

public interface StorageService {

	void storage(StorageRequest request);

	Integer count(String commodityCode);

}

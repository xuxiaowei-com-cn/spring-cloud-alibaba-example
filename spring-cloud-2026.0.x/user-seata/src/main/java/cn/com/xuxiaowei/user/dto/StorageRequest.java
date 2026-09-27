package cn.com.xuxiaowei.user.dto;

import lombok.Data;

/**
 * 库存修改请求参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class StorageRequest {

	/**
	 * 商品ID
	 */
	private String commodityCode;

	/**
	 * 数量：正值：增加，负值：减小，不足时抛出异常
	 */
	private Integer count;

}

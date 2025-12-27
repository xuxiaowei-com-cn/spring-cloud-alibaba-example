package cn.com.xuxiaowei.user.dto;

import lombok.Data;

/**
 * Seata分布式事务请求参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class SeataRequest {

	/**
	 * 商品ID
	 */
	private String commodityCode;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 总数量
	 */
	private Integer count;

	/**
	 * 总金额
	 */
	private Integer money;

}

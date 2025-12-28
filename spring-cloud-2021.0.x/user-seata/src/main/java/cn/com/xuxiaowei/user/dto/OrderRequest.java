package cn.com.xuxiaowei.user.dto;

import lombok.Data;

/**
 * 订单创建请求参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class OrderRequest {

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 商品ID
	 */
	private String commodityCode;

	/**
	 * 总数量
	 */
	private Integer count;

	/**
	 * 总金额
	 */
	private Integer money;

}

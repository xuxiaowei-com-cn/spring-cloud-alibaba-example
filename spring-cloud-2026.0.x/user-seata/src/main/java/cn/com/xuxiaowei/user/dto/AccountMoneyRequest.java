package cn.com.xuxiaowei.user.dto;

import lombok.Data;

/**
 * 账户金额修改请求参数
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Data
public class AccountMoneyRequest {

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 金额：正值：增加，负值：减小，不足时抛出异常
	 */
	private Integer money;

}

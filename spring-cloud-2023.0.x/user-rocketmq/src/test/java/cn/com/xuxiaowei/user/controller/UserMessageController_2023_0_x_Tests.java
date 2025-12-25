package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.consumer.MessageConsumer;
import cn.com.xuxiaowei.user.message.SimpleMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.messaging.Message;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserMessageController_2023_0_x_Tests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MessageConsumer messageConsumer;

	@Test
	void message() throws InterruptedException {
		// 清空消息列表
		messageConsumer.getList().clear();

		// 调用接口获取消息
		SimpleMessage expectedMessage = restTemplate.getForObject("/message", SimpleMessage.class);
		assertNotNull(expectedMessage);

		// 等待消息消费
		Thread.sleep(5000);

		// 获取消费的消息列表
		List<Message<SimpleMessage>> messages = messageConsumer.getList();
		assertTrue(messages != null && !messages.isEmpty(), "消息列表应为非空");

		// 验证消息内容
		boolean messageFound = messages.stream().anyMatch(msg -> msg.getPayload().equals(expectedMessage));
		assertTrue(messageFound, "应包含预期的消息");
	}

}

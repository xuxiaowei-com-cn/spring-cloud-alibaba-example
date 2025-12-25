package cn.com.xuxiaowei.user.controller;

import cn.com.xuxiaowei.user.message.SimpleMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class UserMessageController {

	private StreamBridge streamBridge;

	@Autowired
	public void setStreamBridge(StreamBridge streamBridge) {
		this.streamBridge = streamBridge;
	}

	@GetMapping("/message")
	public SimpleMessage message() {
		long currentTimeMillis = System.currentTimeMillis();
		String key = "KEY" + currentTimeMillis;
		Map<String, Object> headers = new HashMap<>();
		headers.put(MessageConst.PROPERTY_KEYS, key);
		headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, currentTimeMillis);
		SimpleMessage payload = new SimpleMessage("Hello RocketMQ " + currentTimeMillis);
		Message<SimpleMessage> msg = new GenericMessage<>(payload, headers);

		boolean sent = streamBridge.send("producer-out-0", msg);
		if (sent) {
			log.info("Message sent successfully, key: {}", key);
		}
		else {
			log.error("Failed to send message, key: {}", key);
		}

		return payload;
	}

}

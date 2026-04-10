package cn.com.xuxiaowei.user.consumer;

import cn.com.xuxiaowei.user.message.SimpleMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 消费者
 */
@Slf4j
@Configuration
public class MessageConsumer {

	@Getter
	private final List<Message<SimpleMessage>> list = Collections.synchronizedList(new ArrayList<>());

	@Bean
	public Consumer<Message<SimpleMessage>> consumer() {
		return message -> {
			log.info("{} Consumer Receive New Messages: {}", Thread.currentThread().getName(),
					message.getPayload().getMessage());
			log.info("Message Headers: {}", message.getHeaders());

			list.add(message);
			log.info("Message Size: {}", list.size());
		};
	}

}

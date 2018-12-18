package pro.yuchen.demo.spring_demo.mq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class TopicSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 * 发送消息
	 * @param routingKey 路由key
	 * @param context 消息内容
	 */
	public void send(String routingKey, Object context) {
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend(TopicRabbitConfig.exchange, routingKey, context);
	}

}

package pro.yuchen.demo.spring_demo.mq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hbase")
public class TopicReceiverQueuesHBase {

	@RabbitHandler
	public void process(String message) {
		System.out.println("Topic HBase : " + message);
	}
}
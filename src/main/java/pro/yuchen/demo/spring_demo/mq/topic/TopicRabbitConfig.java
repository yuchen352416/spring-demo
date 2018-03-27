package pro.yuchen.demo.spring_demo.mq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

	public final static String exchange = "messages";
	public final static String mysql = "message.disk.mysql";
	public final static String hbase = "message.disk.hbase";

	@Bean
	public Queue queueMySQL() {
		return new Queue("mysql");
	}

	@Bean
	public Queue queueHBase() {
		return new Queue("hbase");
	}

	@Bean
	public Queue queueDisk() {
		return new Queue("disk");
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TopicRabbitConfig.exchange, true, true);
	}

	@Bean
	Binding bindingExchangeMySQL(Queue queueMySQL, TopicExchange exchange) {
		return BindingBuilder.bind(queueMySQL).to(exchange).with(TopicRabbitConfig.mysql);
	}

	@Bean
	Binding bindingExchangeHBase(Queue queueHBase, TopicExchange exchange) {
		return BindingBuilder.bind(queueHBase).to(exchange).with(TopicRabbitConfig.hbase);
	}

	@Bean
	Binding bindingExchangeDisk(Queue queueDisk, TopicExchange exchange) {
		return BindingBuilder.bind(queueDisk).to(exchange).with("message.disk.#");
	}

}

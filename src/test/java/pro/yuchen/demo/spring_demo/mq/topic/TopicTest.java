package pro.yuchen.demo.spring_demo.mq.topic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.yuchen.demo.spring_demo.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class TopicTest {

	@Autowired
	TopicSender sender;

	@Test
	public void testSender() {
		sender.send("message.disk.mysql", "Hello MySQL Queues...");
		sender.send("message.disk.hbase", "Hello HBase Queues...");
	}





}

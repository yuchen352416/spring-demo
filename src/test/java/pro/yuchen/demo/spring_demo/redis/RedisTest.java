package pro.yuchen.demo.spring_demo.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import pro.yuchen.demo.spring_demo.entity.Book;
import pro.yuchen.demo.spring_demo.entity.User;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

//	Redis Data Type: 1.String, 2.Hash 3.List, 4.Set, 5.zSet

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "张三");
		Assert.assertEquals("张三", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void testObj() throws Exception {
		User user=new User();
		user.setName("tom");
		user.setAge(10);
		user.setId(1);
		User user1 = new User();
		user1.setName("Jack");
		user1.setAge(48);
		user1.setId(3);
		Book book = new Book();
		book.setDate(new Date());
		book.setId(1);
		book.setName("hheehe");
		book.setPress("aaa").setPress("bbb").setPress("ccc");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
		String book_json = gson.toJson(book);
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		operations.set("com.neox", user);
		operations.set("com.neo.f", user1);
		operations.set("book", book_json);
		Book book1 = gson.fromJson(operations.get("book").toString(), Book.class);

		operations.set("com.neo.f", user,99, TimeUnit.SECONDS);
		Thread.sleep(1000);
		redisTemplate.delete("com.neo.f");
		boolean exists=redisTemplate.hasKey("com.neo.f");
		if(exists){
			System.out.println("exists is true");
		}else{
			System.out.println("exists is false");
		}
		// Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
	}
}

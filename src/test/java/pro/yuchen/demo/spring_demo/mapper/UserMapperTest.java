package pro.yuchen.demo.spring_demo.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.yuchen.demo.spring_demo.Application;
import pro.yuchen.demo.spring_demo.entity.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockServletContext.class, Application.class})
public class UserMapperTest {

	@Autowired
	private UserMapper mapper;

	@Test
	public void insert() {
		User user = new User();
		user.setAge(20);
		user.setName("mmw");
		user.setCity("dd");
		mapper.insert(user);
	}

	@Test
	public void delete() {
		mapper.delete(1);
	}

	@Test
	public void update() {
		User user = mapper.get(2);
		System.out.println(user.getName());
		user.setName("Mr.Li");
 		mapper.update(user);
	}

	@Test
	public void get() {
		List<User> list = mapper.getAll();
		for (User user : list) {
			System.out.println(user.getName());
		}
		User user = mapper.get(2);
		System.out.println(user.getName());
	}

}

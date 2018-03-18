package pro.yuchen.demo.spring_demo.mapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.yuchen.demo.spring_demo.Application;
import pro.yuchen.demo.spring_demo.entity.Visitor;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockServletContext.class, Application.class})
public class VisitorMapperTest {

	@Autowired
	private VisitorMapper mapper;

	@Test
	public void insert() {
		Visitor visitor = new Visitor();
		visitor.setCreateTime(new Date());
		visitor.setName("Ws");
		visitor.setEmail("ws@163.com");
		visitor.setStatus(1);
		mapper.insert(visitor);
	}

	@Test
	public void delete() {
		mapper.delete(8);
	}

	@Test
	public void update() {
		Visitor visitor = mapper.get(2);
		System.out.println(visitor.getName());
		visitor.setName("Mr.Li");
		mapper.update(visitor);
	}

	@Test
	public void get() {
		List<Visitor> list = mapper.getAll();
		for (Visitor visitor : list) {
			System.out.println(visitor.getName());
		}
		Visitor visitor = mapper.get(2);
		System.out.println(visitor.getName());
	}

}

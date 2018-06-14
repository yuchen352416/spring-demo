package pro.yuchen.demo.spring_demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pro.yuchen.demo.spring_demo.Application;
import pro.yuchen.demo.spring_demo.entity.Blog;
import pro.yuchen.demo.spring_demo.entity.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockServletContext.class, Application.class})
@WebAppConfiguration
public class BlogRepositoryTest {

	@Autowired
	private BlogRepository repository;

	@Test
	public void insert() throws Exception {

		Blog blog = new Blog();
		String title = "TEST.......";
		blog.setTitle(title);
		blog.setContent("jjjjkkkkjjsfjseoijqksahbfgoyhujikldfghjklsdfgesdrtfgzxrdcfvgbnazesxdcfvgbhnjklaWzesxdcfvgbhjklazsdfghjkazesxdcfghj");
		repository.save(blog);
	}

	@Test
	public void delete() {
		repository.delete(10);
	}

	@Test
	public void update() {
		Blog blog = repository.findOne(12);
		blog.setTitle("花一样的人生....");
		repository.save(blog);
		System.out.println(repository.findOne(12).getTitle());
	}

	@Test
	public void findOne() {
		Blog result = repository.findOne(45);
		System.out.println(result.getTitle());
		for (Comment comment:result.getComments()) {
			System.out.println(comment.getContent());
		}
	}

	@Test
	public void findOneByTitle() {
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<Blog> ex = Example.of(new Blog("TEST...."), matcher);

		Blog result = repository.findOne(ex);
		System.out.println(result.getTitle());
		for (Comment comment:result.getComments()) {
			System.out.println(comment.getContent());
		}
	}

	@Test
	public void findAllByPage() {
		Sort sort = new Sort(Sort.Direction.DESC, "title");
		Pageable pageable = new PageRequest(1,10, sort);
		Page<Blog> list = repository.findAll(pageable);
		for (Blog blog : list) {
			System.out.println(blog.getTitle());
		}
	}
}

package pro.yuchen.demo.spring_demo.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pro.yuchen.demo.spring_demo.Application;
import pro.yuchen.demo.spring_demo.entity.Blog;
import pro.yuchen.demo.spring_demo.entity.Comment;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class CommentRepositoryTest {

	@Autowired
	private CommentRepository repository;

	@Test
	public void insert() {
		Blog blog = new Blog();
		for (int i = 30; i < 100; i++) {
			blog.setId(i);
			for(int j = 0; j < 10; j++) {
				Comment comment = new Comment();
				comment.setBlog(blog);
				comment.setContent("评论...." + j);
				repository.save(comment);
			}
		}
	}

	@Test
	public void delete() {
		repository.delete(23);
	}
	
	@Test
	public void update() {
		Comment comment = repository.findOne(10);
		comment.setContent("lll");
		repository.save(comment);
	}

	@Test
	public void findOne() {
		Comment comment = repository.findOne(10);
		System.out.println(comment.getContent());
	}

	@Test
	public void findAllByPage() {
		Sort sort = new Sort(Sort.Direction.ASC, "blog_id", "id");
		Pageable pageable = new PageRequest(23,10, sort);
		Page<Comment> list = repository.findAll(pageable);
		for (Comment comment : list) {
			System.out.println(comment.getContent() + "              " + comment.getId());
		}
	}
}

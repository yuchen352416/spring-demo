package pro.yuchen.demo.spring_demo.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.yuchen.demo.spring_demo.entity.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findAll(Specification<Blog> title);
}

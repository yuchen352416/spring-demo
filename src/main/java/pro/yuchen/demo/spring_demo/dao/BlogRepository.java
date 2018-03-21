package pro.yuchen.demo.spring_demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.yuchen.demo.spring_demo.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}

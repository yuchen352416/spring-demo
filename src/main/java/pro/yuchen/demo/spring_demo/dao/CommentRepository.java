package pro.yuchen.demo.spring_demo.dao;

		import org.springframework.data.jpa.repository.JpaRepository;
		import pro.yuchen.demo.spring_demo.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
package pro.yuchen.demo.spring_demo.mapper;

import org.apache.ibatis.annotations.*;
import pro.yuchen.demo.spring_demo.entity.Visitor;

import java.util.List;

public interface VisitorMapper {

	@Insert("INSERT INTO visitor(name, email, status, create_time) VALUES(#{name}, #{email}, #{status}, #{createTime})")
	void insert(Visitor visitor);

	@Delete("DELETE FROM visitor WHERE id=#{id}")
	void delete(Integer id);

	@Update("UPDATE visitor SET name=#{name}, email=#{email}, status=#{status}, create_time=#{createTime}  WHERE id=#{id}")
	void update(Visitor visitor);

	@Select("SELECT * FROM visitor")
	@Results({
			@Result(property = "createTime",  column = "create_time")
	})
	List<Visitor> getAll(); // 注意这里的方法, 是不可以重载的

	@Select("SELECT * FROM visitor WHERE id=#{id}")
	@Results({
			@Result(property = "createTime",  column = "create_time")
	})
	Visitor get(Integer id);

}

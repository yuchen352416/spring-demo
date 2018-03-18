package pro.yuchen.demo.spring_demo.mapper;

import pro.yuchen.demo.spring_demo.entity.User;

import java.util.List;

public interface UserMapper {

	void insert(User user);

	void delete(Integer id);

	void update(User user);

	List<User> getAll(); // 注意这里的方法, 是不可以重载的

	User get(Integer id);
}

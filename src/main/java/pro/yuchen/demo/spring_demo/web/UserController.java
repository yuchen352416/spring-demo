package pro.yuchen.demo.spring_demo.web;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import pro.yuchen.demo.spring_demo.entity.User;

import java.util.*;

/**
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	/**
	 * 添加用户
	 * @param user 需要添加的用户
	 * @return 成功的标志("success")
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String postUser(@ModelAttribute User user) {
		// 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		users.put(user.getId(), user);
		return "success";
	}

	/**
	 * 根据用户id, 删除用户
	 * @param id 用户id
	 * @return 成功的标志("success")
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return "success";
	}

	/**
	 * 根据用户id, 修改用户信息
	 * @param id
	 * @param user
	 * @return 成功的标志("success")
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @ModelAttribute User user) {
		User u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}

	/**
	 * 根据用户id, 查询用户信息
	 * @param id 用户id
	 * @return 查询到的User
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Cacheable("user-key") // 会默认添加缓存到Redis, 当缓存中存在想要查询的值时, 直接读取缓存中的数据, 而不调用方法
	public User getUser(@PathVariable Long id) {
		// url中的id可通过@PathVariable绑定到函数的参数中
		User user = users.get(id);
		return user;
	}

	/**
	 * 查询所有用户
	 * @return 所有用户列表
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getUsers() {
		// 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
		List<User> list = new ArrayList<User>(users.values());
		return list;
	}

}

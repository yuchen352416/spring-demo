package pro.yuchen.demo.spring_demo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {MockServletContext.class})
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mvc;

	@Before
	public void setup() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void testPostUser() throws Exception {
		RequestBuilder result = MockMvcRequestBuilders.post("/users/")
				.param("id", "1")
				.param("name", "Tom")
				.param("age", "5");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

	@Test
	public void testDeleteUser() throws Exception {
		RequestBuilder result = MockMvcRequestBuilders.delete("/users/1");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

	@Test
	public void testPutUser() throws Exception {
		RequestBuilder result = MockMvcRequestBuilders.put("/users/1")
				.param("name", "Tom")
				.param("age", "7");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));
	}

	@Test
	public void testGetUser() throws Exception {
		RequestBuilder result = MockMvcRequestBuilders.get("/users/1");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\\\"id\\\":1,\\\"name\\\":\\\"Tom\\\",\\\"age\\\":5}")));
	}

	@Test
	public void testGetUsers() throws Exception {
		RequestBuilder result = MockMvcRequestBuilders.get("/users/");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\\\"id\\\":1,\\\"name\\\":\\\"Tom\\\",\\\"age\\\":5}]")));
	}

	/**
	 * 联合测试
	 * @throws Exception
	 */
	@Test
	public void testUserController() throws Exception {
		RequestBuilder result = null;
		// 1. 测试-查询所有用户信息
		result = MockMvcRequestBuilders.get("/users/");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		// 2. 测试-新增用户
		result = MockMvcRequestBuilders.post("/users/")
				.param("id", "1")
				.param("name", "Tom")
				.param("age", "5");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 3. 测试-修改用户信息
		result = MockMvcRequestBuilders.put("/users/1")
				.param("name", "Tom")
				.param("age", "7");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 4. 测试-查询单用户, 并验证修改
		result = MockMvcRequestBuilders.get("/users/1");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"Tom\",\"age\":7}")));

		// 5. 测试-删除用户
		result = MockMvcRequestBuilders.delete("/users/1");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		// 6. 测试-是否删除成功
		result = MockMvcRequestBuilders.get("/users/");
		mvc.perform(result).andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}



}

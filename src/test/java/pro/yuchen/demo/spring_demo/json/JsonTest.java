package pro.yuchen.demo.spring_demo.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import pro.yuchen.demo.spring_demo.entity.Book;

import java.util.Date;

public class JsonTest {

	@Test
	public void testJson() {
		Book book = new Book();
		book.setId(1);
		book.setName("Java 编程思想");
		book.setDate(new Date());
		book.setPress("a").setPress("b").setPress("c");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
		String json = gson.toJson(book);
		System.out.println(json);

		book = gson.fromJson(json, Book.class);
		System.out.println(book.getId());

	}


}

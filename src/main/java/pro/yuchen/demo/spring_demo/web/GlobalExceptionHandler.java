package pro.yuchen.demo.spring_demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pro.yuchen.demo.spring_demo.entity.Book;
import pro.yuchen.demo.spring_demo.pojo.GlobalException;
import pro.yuchen.demo.spring_demo.pojo.PageException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = GlobalException.class)
	@ResponseBody
	public Map<String, Object> defaultErrorHandler(HttpServletResponse response, HttpServletRequest request, GlobalException e) throws Exception {
		logger.error("", e);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", e.getCode());
		result.put("message", e.getMessage());
		result.put("data", e.getData());
		response.setStatus(e.getCode());
		return result; // 返回 JSON
	}

	@ExceptionHandler(value = {PageException.class})
	public ModelAndView pageErrorHandler(PageException e) throws Exception {
		logger.error("", e);
		ModelAndView m = new ModelAndView();
		// 设置返回数据
		Book book = new Book();
		book.setName("Java 编程思想1");
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		m.addObject("book", book);
		m.addObject("books", books);
		// 设置返回页面
		m.setViewName("error/500");
		return m; // 返回thymeleaf渲染的页面
	}

}
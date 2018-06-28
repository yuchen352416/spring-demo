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
import java.util.*;


@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = GlobalException.class)
	public Map<String, Object> defaultErrorHandler(HttpServletResponse response, HttpServletRequest request, GlobalException e) throws Exception {
		log.error("测试异常处理通用拦截器...", e);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", e.getCode());
		result.put("message", e.getMessage());
		result.put("data", e.getData());
		response.setStatus(e.getCode());
		return result; // 返回 JSON
	}

	@ExceptionHandler(value = {PageException.class})
	public ModelAndView pageErrorHandler(PageException e) throws Exception {
		log.error("测试异常处理页面拦截器...", e);
		log.error("错误处理中...");
		ModelAndView m = new ModelAndView();
		// 设置返回数据
		Book book = new Book();
		book.setId(1);
		book.setName("Java 编程思想");
		book.setPress("Bruce").setPress("李述昱");
		book.setDate(new Date());
		m.addObject("book", book);
		m.addObject("url", "404.html");
		// 设置返回页面
		m.setViewName("error/500");
		return m; // 返回thymeleaf渲染的页面
	}

}
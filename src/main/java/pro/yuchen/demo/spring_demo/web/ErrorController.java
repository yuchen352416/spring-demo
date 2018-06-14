package pro.yuchen.demo.spring_demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.yuchen.demo.spring_demo.pojo.GlobalException;
import pro.yuchen.demo.spring_demo.pojo.PageException;


@RestController
public class ErrorController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/error/{id}")
	public String error(@PathVariable Integer id) throws Exception {
		if(id == 1) {
			log.info("跳转通用错误处理程序...");
			throw new GlobalException(503, "测试...");
		} else if (id > 50) {
			log.info("跳转页面错误处理程序...");
			throw new PageException(500, "测试页面...");
		}
		return "error";
	}
}

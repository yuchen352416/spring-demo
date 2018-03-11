package pro.yuchen.demo.spring_demo.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.yuchen.demo.spring_demo.pojo.GlobalException;
import pro.yuchen.demo.spring_demo.pojo.PageException;


@RestController
public class ErrorController {

	@RequestMapping("/error/{id}")
	public String error(@PathVariable Integer id) throws Exception {
		if(id == 1) {
			throw new GlobalException(503, "测试.......");
		} else if (id > 50) {
			throw new PageException(500, "测试页面");
		}
		return "error";
	}
}

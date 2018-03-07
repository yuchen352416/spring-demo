package pro.yuchen.demo.spring_demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.yuchen.demo.spring_demo.pojo.ConfigBean;

@RestController
public class HelloController {

	@Autowired
	private ConfigBean config;

	@Value("${config.application}")
	private String name;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}

	@RequestMapping("/config")
	public String conifg() {
		return name + "   " + config.getName() + "   " + config.getAge() + "   " + config.getSex();
	}

}

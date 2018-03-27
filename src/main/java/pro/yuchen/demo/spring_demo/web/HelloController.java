package pro.yuchen.demo.spring_demo.web;


import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.yuchen.demo.spring_demo.pojo.ConfigBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class HelloController {

	@Autowired
	private ConfigBean config;

	@Value("${config.application}")
	private String name;

	@RequestMapping("/hello")
	public String hello(HttpServletRequest request, HttpServletResponse response) {
		request.getRemoteAddr();
		request.getRemoteHost();
		return "Hello World";
	}

	@RequestMapping("/config")
	public String conifg() {
		return name + "   " + config.getName() + "   " + config.getAge() + "   " + config.getSex();
	}

}

package pro.yuchen.demo.spring_demo.thymeleaf;

import org.springframework.stereotype.Controller;
		import org.springframework.ui.ModelMap;
		import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 如果使用@RestController，页面只返回index这个字符串，这个注解等同于使用@ResponseBody
public class ThymeleafController {

	@RequestMapping(value = {"/", "", "/index.html"})
	public String index(ModelMap map) {
		// 加入一个属性，用来在模板中读取
		map.put("hello","helloweb");
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "index";
	}

	@RequestMapping(value = {"/edit", "edit", "/edit.html"})
	public String edit(ModelMap map) {
		// return模板文件的名称，对应src/main/resources/templates/index.html
		return "edit";
	}

}

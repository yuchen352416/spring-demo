package pro.yuchen.demo.spring_demo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.yuchen.demo.spring_demo.pojo.GlobalException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
		return result;
	}
}
package pro.yuchen.demo.spring_demo.http.pojo;

import java.util.HashMap;
import java.util.Map;

public class ResponseBean {

	/**
	 * 响应Code
	 */
	private int status;

	/**
	 * 响应结果
	 */
	private String body;

	/**
	 * cookies
	 */
	private Map<String, String> cookies;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public void setCookie(String key, String value) {
		if(this.cookies == null) {
			this.cookies = new HashMap<String, String>();
		}
		cookies.put(key, value);
	}

	public String getCookie(String key) {
		if (this.cookies != null) {
			return this.cookies.get(key);
		}
		return null;
	}
}

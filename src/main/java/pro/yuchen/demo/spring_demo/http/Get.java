package pro.yuchen.demo.spring_demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import pro.yuchen.demo.spring_demo.http.pojo.ResponseBean;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Get {

	/**
	 * 请求地址
	 */
	private String url = null;

	/**
	 * 请求头
	 */
	private Map<String, String> heads = null;

	/**
	 * 请求参数
	 */
	private Map<String, String> params = null;


	private Get() {}

	/**
	 * 构造Get对象
	 * @return get
	 */
	public static Get create() {
		return new Get();
	}

	/**
	 * 设置请求地址
	 * @param url 请求地址
	 * @return get
	 */
	public Get url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * 设置全部请求头信息
	 * @param heads 头信息
	 * @return get
	 */
	public Get heads(Map<String, String> heads) {
		this.heads = heads;
		return this;
	}

	/**
	 * 逐条添加请求头信息
	 * @param key 请求头 key
	 * @param value 请求头 value
	 * @return get
	 */
	public Get head(String key, String value) {
		if(this.heads == null) {
			this.heads = new HashMap<String, String>();
		}
		this.heads.put(key, value);
		return this;
	}

	/**
	 * 设置全部请求参数
	 * @param params 请求参数
	 * @return get
	 */
	public Get params(Map<String, String> params) {
		this.params = params;
		return this;
	}

	/**
	 * 逐条添加请求参数
	 * @param key 参数key
	 * @param value 参数value
	 * @return get
	 */
	public Get param(String key, String value) {
		if (this.params == null) {
			this.params = new HashMap<String, String>();
		}
		this.params.put(key, value);
		return this;
	}

	/**
	 * 发起请求
	 */
	public ResponseBean go() {
		ResponseBean result = new ResponseBean();
		CookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient client = HttpClientFactory.create(cookieStore);
		HttpGet get = new HttpGet();
		CloseableHttpResponse response = null;
		// 设置请求参数
		if (this.params != null) {
			StringBuffer bar = new StringBuffer();
			bar.append("?");
			for (String key : this.params.keySet()) {
				bar.append(key).append("=").append(this.params.get(key)).append("&");
			}
			String param = bar.substring(0, bar.length() - 1);
			this.url += param;
		}
		get.setURI(URI.create(this.url));
		// 设置请求头
		if (this.heads != null) {
			for (String key : this.heads.keySet()) {
				get.setHeader(key, this.heads.get(key));
			}
		}
		// 发起请求
		try {
			// 执行请求
			response = client.execute(get);
			// 获取响应实体
			HttpEntity entity = response.getEntity();
			// 获取响应Code
			result.setStatus(response.getStatusLine().getStatusCode());
			// 获取响应信息
			String body = EntityUtils.toString(entity, "UTF-8");
			result.setBody(body);
			// 获取Cookies
			List<Cookie> cookies = cookieStore.getCookies();
			for (int i = 0; i < cookies.size(); i++) {
				result.setCookie(cookies.get(i).getName(), cookies.get(i).getValue());
			}
		} catch (ClientProtocolException e) {
			System.err.println("协议错误");
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println("解析错误");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IO错误");
			e.printStackTrace();
		} finally {
			if (null != response) {
				try {
					EntityUtils.consume(response.getEntity());
					response.close();
				} catch (IOException e) {
					System.err.println("释放链接错误");
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}

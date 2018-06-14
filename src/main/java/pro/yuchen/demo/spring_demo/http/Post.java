package pro.yuchen.demo.spring_demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.util.EntityUtils;
import pro.yuchen.demo.spring_demo.http.pojo.ResponseBean;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post {

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
	private Map<String, Object> params = null;

	/**
	 * 正确的响应Code
	 */
	private int status = 200;

	private Post() {}

	/**
	 * 构造Post对象
	 * @return post
	 */
	public static Post create() {
		return new Post();
	}

	/**
	 * 设置请求地址
	 * @param url 请求地址
	 * @return get
	 */
	public Post url(String url) {
		this.url = url;
		return this;
	}

	public Post status(int status) {
		this.status = status;
		return this;
	}
	/**
	 * 设置全部请求头信息
	 * @param heads 头信息
	 * @return get
	 */
	public Post heads(Map<String, String> heads) {
		this.heads = heads;
		return this;
	}

	/**
	 * 逐条添加请求头信息
	 * @param key 请求头 key
	 * @param value 请求头 value
	 * @return get
	 */
	public Post head(String key, String value) {
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
	public Post params(Map<String, Object> params) {
		this.params = params;
		return this;
	}

	/**
	 * 逐条添加请求参数
	 * @param key 参数key
	 * @param value 参数value
	 *              String 类型的数据, 可以直接加
	 *              图片的话, 要以 File类型 或 InputStream类型 传入
	 * @return get
	 */
	public Post param(String key, Object value) {
		if (this.params == null) {
			this.params = new HashMap<String, Object>();
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
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(this.url);
		// 设置参数
		MultipartEntityBuilder builder = MultipartEntityBuilder.create().setCharset(Charset.forName("utf-8"));
		if (this.params != null) {
			for (String key : this.params.keySet()) {
				if (this.params.get(key) instanceof String) {
					builder.addTextBody(key, (String) this.params.get(key));
				} else if (this.params.get(key) instanceof File) {
					builder.addBinaryBody(key, (File) this.params.get(key));
				} else if (this.params.get(key) instanceof InputStream) {
					builder.addBinaryBody(key, (InputStream) this.params.get(key));
				}
			}
		}
		HttpEntity entity = builder.build();
		// 设置请求头
		post.setEntity(entity);
		if (this.heads != null) {
			for(String key : this.heads.keySet()) {
				post.setHeader(key, this.heads.get(key));
			}
		}

		// 发起请求
		try {
			// 执行请求
			response = client.execute(post);
			if(response.getStatusLine().getStatusCode() == status) {
				// 获取响应Code
				result.setStatus(response.getStatusLine().getStatusCode());
				// 获取响应信息
				String body = EntityUtils.toString(response.getEntity());
				result.setBody(body);
				// 获取Cookies
				List<Cookie> cookies = cookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++) {
					result.setCookie(cookies.get(i).getName(), cookies.get(i).getValue());
				}
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

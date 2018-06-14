package pro.yuchen.demo.spring_demo.http;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

public class HttpClientFactory {

	/**
	 * 全局连接池对象
	 */
	private static final PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

	/**
	 * 超时时长
	 */
	private static final int MAX_TIMEOUT = 7000;

	/**
	 * 静态代码块配置连接池信息
	 */
	static {
		// 设置最大连接数
		manager.setMaxTotal(200);
		// 设置每个连接的路由数
		manager.setDefaultMaxPerRoute(20);
	}


	public static CloseableHttpClient create(CookieStore store) {
		// 创建Http请求配置参数
		RequestConfig requestConfig = RequestConfig.custom()
				// 获取连接超时时间
				.setConnectionRequestTimeout(MAX_TIMEOUT)
				// 请求超时时间
				.setConnectTimeout(MAX_TIMEOUT)
				// 响应超时时间
				.setSocketTimeout(MAX_TIMEOUT)
				.build();

		/**
		 * 测出超时重试机制为了防止超时不生效而设置
		 * 如果直接放回false,不重试
		 * 这里会根据情况进行判断是否重试
		 */
		HttpRequestRetryHandler retry = new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				if (executionCount >= 3) {// 如果已经重试了3次，就放弃
					return false;
				}
				if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
					return true;
				}
				if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
					return false;
				}
				if (exception instanceof InterruptedIOException) {// 超时
					return true;
				}
				if (exception instanceof UnknownHostException) {// 目标服务器不可达
					return false;
				}
				if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
					return false;
				}
				if (exception instanceof SSLException) {// ssl握手异常
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				// 如果请求是幂等的，就再次尝试
				if (!(request instanceof HttpEntityEnclosingRequest)) {
					return true;
				}
				return false;
			}
		};

		// 创建httpClient
		return HttpClients.custom()
				// 把请求相关的超时信息设置到连接客户端
				.setDefaultRequestConfig(requestConfig)
				// 把请求重试设置到连接客户端
				.setRetryHandler(retry)
				// 配置连接池管理对象
				.setConnectionManager(manager)
				.setDefaultCookieStore(store)
				.build();
	}
}

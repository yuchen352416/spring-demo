package pro.yuchen.demo.spring_demo.http;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import pro.yuchen.demo.spring_demo.http.pojo.ResponseBean;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpTest {

	@Test
	public void test() {

		ResponseBean get_response = Get.create().url("https://www.windowmalaysia.my/evisa/vlno_register.jsp?type=register").go();
		Document doc = Jsoup.parse(get_response.getBody());
		Element element = doc.select("div.col-xs-12 > p > b").first();
		System.out.println(element);

		// 设置请求头信息
		Map<String, String> heads = new LinkedHashMap<String, String>();
		heads.put(":authority", "www.windowmalaysia.my");
		heads.put(":method", "POST");
		heads.put(":path", "/evisa/register");
		heads.put(":scheme", "https");
		heads.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		heads.put("accept-encoding", "gzip, deflate, br");
		heads.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		heads.put("cache-control", "max-age=0");
		heads.put("content-type", "application/x-www-form-urlencoded");
		heads.put("origin", "https://www.windowmalaysia.my");
		heads.put("referer", "https://www.windowmalaysia.my/evisa/vlno_register.jsp?type=register");
		heads.put("upgrade-insecure-requests", "1");
		heads.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");

		StringBuffer cookie = new StringBuffer();
		Iterator it = get_response.getCookies().keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = get_response.getCookie(key);
			cookie.append(key).append("=").append(value).append("; ");
		}
		heads.put("cookie", cookie.substring(0, cookie.length() - 2));

		String email = "yuchen352416@yeah.net";

		ResponseBean post_response = Post.create()
				.url("https://www.windowmalaysia.my:443/evisa/register")
				.status(302)
				.heads(heads)
				.param("session_id", get_response.getCookie("JSESSIONID"))
				.param("ipAddress", "176.122.138.114") // 服务器常量
				.param("fullPage", "https://www.windowmalaysia.my/evisa/vlno_register.jsp?type=register")
				.param("locIPAddress", "United States")
				.param("refImg", "")
				.param("firstName", "san")
				.param("lastName", "zhang")
				.param("nationality", "47")
				.param("nationalityhid", "47")
				.param("passportNo", "ef78927999")
				.param("gender", "1")
				.param("dob", "07/08/1916")
				.param("dobDay", "7")
				.param("dobMonth", "8")
				.param("dobYear", "1916")
				.param("address1", "fghjhg")
				.param("address2", "ghjuhg")
				.param("postcode", "010000")
				.param("city", "dfghju")
				.param("country", "246")
				.param("phoneNumber", "18733775865")
				.param("email", email)
				.param("password", "1234567890")
				.param("cPassword", "1234567890")
				.go();

		System.out.println(post_response.getStatus());

		get_response = Get.create().url("https://www.windowmalaysia.my/evisa/resendVerification?email=" + email).heads(heads).go();

		System.out.println(get_response.getBody());

	}

}
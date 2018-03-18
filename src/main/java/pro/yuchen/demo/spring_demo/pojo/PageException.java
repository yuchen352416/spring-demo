package pro.yuchen.demo.spring_demo.pojo;

/**
 * 页面错误信息
 */
public class PageException extends Exception {

	/**
	 * 错误的状态码
	 */
	private Integer code;

	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private String data;

	public PageException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

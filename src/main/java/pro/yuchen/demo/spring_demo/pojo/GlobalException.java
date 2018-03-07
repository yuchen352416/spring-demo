package pro.yuchen.demo.spring_demo.pojo;



/**
 * HTTP请求通用错误信息
 */
public class GlobalException extends Exception {

	/**
	 * 错误的状态码
	 */
	private Integer code;

	/**
	 * HTTP 错误信息
	 */
	private String message;
	/**
	 * HTTP 返回数据(JSON 格式)
	 */
	private String data;

	public GlobalException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

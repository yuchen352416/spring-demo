package pro.yuchen.demo.spring_demo.entity;

import java.io.Serializable;
import java.util.Date;

public class Visitor implements Serializable {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 状态标识
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

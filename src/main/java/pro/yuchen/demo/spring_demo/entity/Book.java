package pro.yuchen.demo.spring_demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
	private Integer id;
	private String name;
	private Date date;
	private List<String> press;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getPress() {
		return press;
	}

	public void setPress(List<String> press) {
		this.press = press;
	}

	public Book setPress(String paess) {
		if (this.press == null) {
			this.press = new ArrayList<String>();
		}
		this.press.add(paess);
		return this;
	}
}

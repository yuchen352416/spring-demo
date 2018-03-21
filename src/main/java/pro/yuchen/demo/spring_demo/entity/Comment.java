package pro.yuchen.demo.spring_demo.entity;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	@Column
	private String content;

	//多对一，@JoinColumn与@column类似，指定映射的数据库字段
	@ManyToOne(targetEntity = Blog.class)
	@JoinColumn(name="blog_id",updatable=false)
	private Blog blog;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
}

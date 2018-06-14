package pro.yuchen.demo.spring_demo.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="blog")
public class Blog implements Serializable {

	public Blog() {}

	public Blog(String title) {
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column
	private String title;

	@Column
	private String content;

	@OneToMany(targetEntity=Comment.class,cascade=CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	//updatable=false很关键，如果没有它，在级联删除的时候就会报错(反转的问题)
	@JoinColumn(name="blog_id",updatable=false)
	private List<Comment> comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}

package es.symbioserver.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Posts")

public class PostsBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull (message = "Title cannot be null")
	@Size (min=2, max=30, message="Title must be between 10 and 30 characters")
	private String title;
	
	@NotNull (message = "Content cannot be null")
	@Size (min=2, max=50, message="Content must be between 10 and 50 characters")
	private String content;
	
	private int userid;
	
	@NotNull(message = "Username cannot be null")
	private String username;
	
	@NotNull(message = "Date cannot be null")
	@Pattern(regexp = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9]):([0-9]|[0-5][0-9])$")
	//@DateTimeFormat(pattern="YYYY-MM-dd HH:mm:ss")
	private String date;

	public PostsBean(){
		id = 0;
		title = "";
		content = "";
		userid = 0;
		username = "";
		date = "";
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}

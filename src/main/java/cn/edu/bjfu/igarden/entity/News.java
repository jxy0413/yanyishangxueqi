package cn.edu.bjfu.igarden.entity;



/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class News {
	private Long Id;

	private String content;

	private String news_time;

	private String title;

	private Integer module_id;

	private String url;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNews_time() {
		return news_time;
	}

	public void setNews_time(String news_time) {
		this.news_time = news_time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getModule_id() {
		return module_id;
	}

	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
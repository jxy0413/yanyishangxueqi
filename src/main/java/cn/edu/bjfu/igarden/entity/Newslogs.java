package cn.edu.bjfu.igarden.entity;



/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Newslogs{
	private Long Id;

	private Long user_id;

	private Long news_id;

	private String view_time;

	private Integer prefer_degree;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getNews_id() {
		return news_id;
	}

	public void setNews_id(Long news_id) {
		this.news_id = news_id;
	}

	public String getView_time() {
		return view_time;
	}

	public void setView_time(String view_time) {
		this.view_time = view_time;
	}

	public Integer getPrefer_degree() {
		return prefer_degree;
	}

	public void setPrefer_degree(Integer prefer_degree) {
		this.prefer_degree = prefer_degree;
	}
}

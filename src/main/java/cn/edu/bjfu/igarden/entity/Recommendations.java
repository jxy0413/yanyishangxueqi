package cn.edu.bjfu.igarden.entity;




/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Recommendations{
	private Long Id;

	private Long user_id;

	private Long news_id;

	private String derive_time;

	private Integer derive_algorithm;

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

	public String getDerive_time() {
		return derive_time;
	}

	public void setDerive_time(String derive_time) {
		this.derive_time = derive_time;
	}

	public Integer getDerive_algorithm() {
		return derive_algorithm;
	}

	public void setDerive_algorithm(Integer derive_algorithm) {
		this.derive_algorithm = derive_algorithm;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	private String feedback;
}
package cn.edu.bjfu.igarden.entity;


import java.util.Date;

/**
 * Created by Cookie on 2019/1/23.
 */
public class newsUser {

//    role,username,password,mobile,email,avatar,sex,area,description,expertstag,expertstitle,expertsintro
    private Long id;
    private String pref_list;
    private String latest_log_time;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPref_list() {
        return pref_list;
    }

    public void setPref_list(String pref_list) {
        this.pref_list = pref_list;
    }

    public String getLatest_log_time() {
        return latest_log_time;
    }

    public void setLatest_log_time(String latest_log_time) {
        this.latest_log_time = latest_log_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

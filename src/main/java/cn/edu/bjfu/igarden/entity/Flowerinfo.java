package cn.edu.bjfu.igarden.entity;

public class Flowerinfo {
    private Integer flowerId;

    private String name;

    private String family;

    private String appearance;

    private String location;

    private String bloomingtime;

    private String photo;

    public Integer getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Integer flowerId) {
        this.flowerId = flowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family == null ? null : family.trim();
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance == null ? null : appearance.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getBloomingtime() {
        return bloomingtime;
    }

    public void setBloomingtime(String bloomingtime) {
        this.bloomingtime = bloomingtime == null ? null : bloomingtime.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
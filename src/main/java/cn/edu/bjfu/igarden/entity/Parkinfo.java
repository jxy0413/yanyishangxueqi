package cn.edu.bjfu.igarden.entity;

/**
 * Created by jxy on 2019/10/25.
 */
public class Parkinfo {
    private Integer parkId;

    private String name;

    private String zone;

    private String address;

    private String type;

    private String area;

    private String suggesttime;

    private String activity;

    private String website;

    private String photo;

    private String parkindex;

    private String distribute;

    public String getDistribute() {
        return distribute;
    }

    public void setDistribute(String distribute) {
        this.distribute = distribute;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getSuggesttime() {
        return suggesttime;
    }

    public void setSuggesttime(String suggesttime) {
        this.suggesttime = suggesttime == null ? null : suggesttime.trim();
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity == null ? null : activity.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getParkindex() {
        return parkindex;
    }

    public void setParkindex(String parkindex) {
        this.parkindex = parkindex == null ? null : parkindex.trim();
    }

    @Override
    public String toString() {
        return "Parkinfo{" +
                "parkId=" + parkId +
                ", name='" + name + '\'' +
                ", zone='" + zone + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", area='" + area + '\'' +
                ", suggesttime='" + suggesttime + '\'' +
                ", activity='" + activity + '\'' +
                ", website='" + website + '\'' +
                ", photo='" + photo + '\'' +
                ", parkindex='" + parkindex + '\'' +
                '}';
    }

    public Parkinfo(Integer parkId, String name, String zone, String address, String type, String area, String suggesttime, String activity, String website, String photo, String parkindex) {
        this.parkId = parkId;
        this.name = name;
        this.zone = zone;
        this.address = address;
        this.type = type;
        this.area = area;
        this.suggesttime = suggesttime;
        this.activity = activity;
        this.website = website;
        this.photo = photo;
        this.parkindex = parkindex;
    }

    public Parkinfo() {
    }
}

package cn.edu.bjfu.igarden.entity;

/**
 * Created by jxy on 2019/10/25.
 */
public class ParkinfoWithBLOBs extends Parkinfo {
    private String price;

    private String openinghours;

    private String description;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getOpeninghours() {
        return openinghours;
    }

    public void setOpeninghours(String openinghours) {
        this.openinghours = openinghours == null ? null : openinghours.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}

package cn.java.entity;

public class Good {
    private Integer id;

    private String goodName;

    private String goodPrice;

    private String goodPhone;

    private String goodAddress;

    private String goodNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice == null ? null : goodPrice.trim();
    }

    public String getGoodPhone() {
        return goodPhone;
    }

    public void setGoodPhone(String goodPhone) {
        this.goodPhone = goodPhone == null ? null : goodPhone.trim();
    }

    public String getGoodAddress() {
        return goodAddress;
    }

    public void setGoodAddress(String goodAddress) {
        this.goodAddress = goodAddress == null ? null : goodAddress.trim();
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum == null ? null : goodNum.trim();
    }
}
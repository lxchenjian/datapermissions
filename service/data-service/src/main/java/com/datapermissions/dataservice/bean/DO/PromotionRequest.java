package com.datapermissions.dataservice.bean.DO;


import java.util.Objects;

//@DataPermissionOnClass("goods_code")
public class PromotionRequest {
    //@DataPermissionOnField("auction_code")
    private String auctionCode;
    //@DataPermissionOnField("front_code")
    private String frontCode;

    public String getAuctionCode() {
        return auctionCode;
    }

    public void setAuctionCode(String auctionCode) {
        this.auctionCode = auctionCode;
    }

    public String getFrontCode() {
        return frontCode;
    }

    public void setFrontCode(String frontCode) {
        this.frontCode = frontCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionRequest that = (PromotionRequest) o;
        return Objects.equals(auctionCode, that.auctionCode) && Objects.equals(frontCode, that.frontCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionCode, frontCode);
    }

    @Override
    public String toString() {
        return "promotionRequest{" +
                "auctionCode='" + auctionCode + '\'' +
                ", frontCode='" + frontCode + '\'' +
                '}';
    }
}

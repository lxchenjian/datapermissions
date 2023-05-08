package com.data.permissions.bean.DO;

import java.util.Objects;

public class PromotionSelectionSingle {


    private Long id;

    private String auction_code;

    private String front_code;

    private String goods_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuction_code() {
        return auction_code;
    }

    public void setAuction_code(String auction_code) {
        this.auction_code = auction_code;
    }

    public String getFront_code() {
        return front_code;
    }

    public void setFront_code(String front_code) {
        this.front_code = front_code;
    }

    public String getGoods_code() {
        return goods_code;
    }

    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionSelectionSingle that = (PromotionSelectionSingle) o;
        return Objects.equals(id, that.id) && Objects.equals(auction_code, that.auction_code) && Objects.equals(front_code, that.front_code) && Objects.equals(goods_code, that.goods_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, auction_code, front_code, goods_code);
    }

    @Override
    public String toString() {
        return "PromotionSelectionSingle{" +
                "id=" + id +
                ", auction_code='" + auction_code + '\'' +
                ", front_code='" + front_code + '\'' +
                ", goods_code='" + goods_code + '\'' +
                '}';
    }
}

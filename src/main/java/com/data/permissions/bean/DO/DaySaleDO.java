package com.data.permissions.bean.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class DaySaleDO implements Serializable {

    private static final long serialVersionUID = 253797867143783195L;
    /**
     * 销售日期
     */
    @TableField("acc_date")
    private String accDate;

    /**
     * 门店代码
     */
    @TableField("store_code")
    private String storeCode;


    /**
     * 商品编码
     */
    @TableField("goods_code")
    private String goodsCode;

    /**
     * 类别编码
     */
    @TableField("category_code")
    private String categoryCode;

    /**
     * 销售数量
     */
    @TableField("sale_qty")
    private BigDecimal saleQty;

    /**
     * 应售金额
     */
    @TableField("should_amt")
    private BigDecimal shouldAmt;

    /**
     * 实售金额
     */
    @TableField("actual_amt")
    private BigDecimal actualAmt;

    /**
     * 成本金额
     */
    @TableField("cost_amt")
    private BigDecimal costAmt;
    /**
     * 结转库存
     */
    @TableField("carry_over_stock")
    private BigDecimal carryOverStock;

    /**
     * 销售次数
     */
    @TableField("sale_number")
    private Integer saleNumber;

    /**
     * 预估毛利额
     */
    @TableField(exist = false)
    private BigDecimal grossAmt;
    /**
     * 更新时间
     *
     */
    @TableField(value = "updated_at",fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;


    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public BigDecimal getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(BigDecimal saleQty) {
        this.saleQty = saleQty;
    }

    public BigDecimal getShouldAmt() {
        return shouldAmt;
    }

    public void setShouldAmt(BigDecimal shouldAmt) {
        this.shouldAmt = shouldAmt;
    }

    public BigDecimal getActualAmt() {
        return actualAmt;
    }

    public void setActualAmt(BigDecimal actualAmt) {
        this.actualAmt = actualAmt;
    }

    public BigDecimal getCostAmt() {
        return costAmt;
    }

    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }

    public BigDecimal getCarryOverStock() {
        return carryOverStock;
    }

    public void setCarryOverStock(BigDecimal carryOverStock) {
        this.carryOverStock = carryOverStock;
    }

    public Integer getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(Integer saleNumber) {
        this.saleNumber = saleNumber;
    }

    public BigDecimal getGrossAmt() {
        return grossAmt;
    }

    public void setGrossAmt(BigDecimal grossAmt) {
        this.grossAmt = grossAmt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DaySaleDO daySaleDO = (DaySaleDO) o;
        return Objects.equals(accDate, daySaleDO.accDate) && Objects.equals(storeCode, daySaleDO.storeCode) && Objects.equals(goodsCode, daySaleDO.goodsCode) && Objects.equals(categoryCode, daySaleDO.categoryCode) && Objects.equals(saleQty, daySaleDO.saleQty) && Objects.equals(shouldAmt, daySaleDO.shouldAmt) && Objects.equals(actualAmt, daySaleDO.actualAmt) && Objects.equals(costAmt, daySaleDO.costAmt) && Objects.equals(carryOverStock, daySaleDO.carryOverStock) && Objects.equals(saleNumber, daySaleDO.saleNumber) && Objects.equals(grossAmt, daySaleDO.grossAmt) && Objects.equals(updatedAt, daySaleDO.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accDate, storeCode, goodsCode, categoryCode, saleQty, shouldAmt, actualAmt, costAmt, carryOverStock, saleNumber, grossAmt, updatedAt);
    }

    @Override
    public String toString() {
        return "DaySaleDO{" +
                "accDate='" + accDate + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", saleQty=" + saleQty +
                ", shouldAmt=" + shouldAmt +
                ", actualAmt=" + actualAmt +
                ", costAmt=" + costAmt +
                ", carryOverStock=" + carryOverStock +
                ", saleNumber=" + saleNumber +
                ", grossAmt=" + grossAmt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

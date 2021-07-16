
package com.example.ahmedkhaled.model.cart;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Serializable
{

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("total_text")
    @Expose
    private String totalText;
    @SerializedName("product")
    @Expose
    private Product__1 product;
    private final static long serialVersionUID = 988334858892080404L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Product() {
    }

    /**
     * 
     * @param amount
     * @param total
     * @param product
     * @param totalText
     */
    public Product(Integer amount, Integer total, String totalText, Product__1 product) {
        super();
        this.amount = amount;
        this.total = total;
        this.totalText = totalText;
        this.product = product;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTotalText() {
        return totalText;
    }

    public void setTotalText(String totalText) {
        this.totalText = totalText;
    }

    public Product__1 getProduct() {
        return product;
    }

    public void setProduct(Product__1 product) {
        this.product = product;
    }

}

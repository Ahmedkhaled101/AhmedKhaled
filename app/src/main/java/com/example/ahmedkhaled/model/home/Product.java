
package com.example.ahmedkhaled.model.home;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Product implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("discount_type")
    @Expose
    private Object discountType;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("in_stock")
    @Expose
    private Integer inStock;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("price_final")
    @Expose
    private Double priceFinal;
    @SerializedName("price_final_text")
    @Expose
    private String priceFinalText;
    private final static long serialVersionUID = -8151994357790943893L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Product() {
    }

    /**
     * 
     * @param description
     * @param discount
     * @param priceFinal
     * @param avatar
     * @param title
     * @param price
     * @param name
     * @param discountType
     * @param currency
     * @param inStock
     * @param id
     * @param priceFinalText
     * @param categoryId
     */
    public Product(Integer id, String name, String title, Integer categoryId, String description, Double price, Integer discount, Object discountType, String currency, Integer inStock, String avatar, Double priceFinal, String priceFinalText) {
        super();
        this.id = id;
        this.name = name;
        this.title = title;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.discountType = discountType;
        this.currency = currency;
        this.inStock = inStock;
        this.avatar = avatar;
        this.priceFinal = priceFinal;
        this.priceFinalText = priceFinalText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Object getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Object discountType) {
        this.discountType = discountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getPriceFinal() {
        return priceFinal;
    }

    public void setPriceFinal(Double priceFinal) {
        this.priceFinal = priceFinal;
    }

    public String getPriceFinalText() {
        return priceFinalText;
    }

    public void setPriceFinalText(String priceFinalText) {
        this.priceFinalText = priceFinalText;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", discountType=" + discountType +
                ", currency='" + currency + '\'' +
                ", inStock=" + inStock +
                ", avatar='" + avatar + '\'' +
                ", priceFinal=" + priceFinal +
                ", priceFinalText='" + priceFinalText + '\'' +
                '}';
    }
}

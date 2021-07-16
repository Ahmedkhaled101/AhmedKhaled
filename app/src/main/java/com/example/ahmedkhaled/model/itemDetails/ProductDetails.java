
package com.example.ahmedkhaled.model.itemDetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductDetails implements Serializable
{

    @SerializedName("product")
    @Expose
    private Product product;
    private final static long serialVersionUID = -7221820117060022825L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductDetails() {
    }

    /**
     * 
     * @param product
     */
    public ProductDetails(Product product) {
        super();
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "product=" + product +
                '}';
    }
}

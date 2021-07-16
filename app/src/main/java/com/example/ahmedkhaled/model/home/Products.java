
package com.example.ahmedkhaled.model.home;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Products implements Serializable {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    private final static long serialVersionUID = -448093395384513965L;

    /**
     * No args constructor for use in serialization
     */
    public Products() {
    }

    /**
     * @param products
     */
    public Products(List<Product> products) {
        super();
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }
}

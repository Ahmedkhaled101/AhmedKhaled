
package com.example.ahmedkhaled.model.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductInCart implements Serializable
{

    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;
    private final static long serialVersionUID = -6635758304284919889L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProductInCart() {
    }

    /**
     * 
     * @param products
     */
    public ProductInCart(ArrayList<Product> products) {
        super();
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductInCart{" +
                "products=" + products +
                '}';
    }
}


package com.example.ahmedkhaled.model.categories;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.ahmedkhaled.model.home.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CategoryId implements Serializable
{

    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;
    private final static long serialVersionUID = -3255672948888433429L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoryId() {
    }

    /**
     * 
     * @param category
     * @param products
     */
    public CategoryId(Category category, ArrayList<Product> products) {
        super();
        this.category = category;
        this.products = products;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}

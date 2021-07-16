
package com.example.ahmedkhaled.model.categories;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.ahmedkhaled.model.home.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("products")
    @Expose
    private ArrayList<Product> products = null;
    private final static long serialVersionUID = -7482617642417852495L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Category() {
    }

    /**
     *
     * @param name
     * @param id
     * @param avatar
     * @param products
     */
    public Category(Integer id, String name, String avatar, ArrayList<Product> products) {
        super();
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.products = products;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", products=" + products +
                '}';
    }
}

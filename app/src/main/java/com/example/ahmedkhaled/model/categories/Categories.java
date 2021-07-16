
package com.example.ahmedkhaled.model.categories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories implements Serializable
{

    @SerializedName("categories")
    @Expose
    private ArrayList<Category> categories = null;
    private final static long serialVersionUID = 7866795366939047650L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Categories() {
    }

    /**
     * 
     * @param categories
     */
    public Categories(ArrayList<Category> categories) {
        super();
        this.categories = categories;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categories=" + categories +
                '}';
    }
}

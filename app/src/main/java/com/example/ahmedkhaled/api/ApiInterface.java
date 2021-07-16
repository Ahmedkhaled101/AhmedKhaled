package com.example.ahmedkhaled.api;

import com.example.ahmedkhaled.model.cart.AddToCart;
import com.example.ahmedkhaled.model.cart.ProductInCart;
import com.example.ahmedkhaled.model.categories.Categories;
import com.example.ahmedkhaled.model.categories.CategoryId;
import com.example.ahmedkhaled.model.home.Product;
import com.example.ahmedkhaled.model.home.Products;
import com.example.ahmedkhaled.model.itemDetails.ProductDetails;
import com.example.ahmedkhaled.model.register.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<LoginResponse> register(@Field("name") String name,
                                 @Field("email") String email,
                                 @Field("password") String password);

    @GET("products")
    Call<Products> getProductsData(@Header("Authorization") String token);

    @GET("products/{productId}")
    Call<ProductDetails> getProductDetails(@Header("Authorization") String token, @Path("productId") int productId);

    @GET("categories")
    Call<Categories> getCategories(@Header("Authorization") String token);

    @GET("categories/{categoryId}")
    Call<CategoryId> getCategoriesDetails(@Header("Authorization") String token, @Path("categoryId") int categoryId);

    @GET("user/products")
    Call<ProductInCart> getProductInCart(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("user/products/{productId}")
    Call<AddToCart> addProductToCart(@Header("Authorization") String token, @Path("productId") int productId , @Field("amount") String amount);

}

package com.example.ahmedkhaled.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.adapter.ProductsAdapter;
import com.example.ahmedkhaled.api.RetrofitManger;
import com.example.ahmedkhaled.databinding.FragmentHomeBinding;
import com.example.ahmedkhaled.model.cart.AddToCart;
import com.example.ahmedkhaled.model.categories.CategoryId;
import com.example.ahmedkhaled.model.home.Product;
import com.example.ahmedkhaled.model.home.Products;
import com.example.ahmedkhaled.model.itemDetails.ProductDetails;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ahmedkhaled.ui.LoginFragment.USER_TOKEN;

public class HomeFragment extends Fragment implements ProductsAdapter.ProductsAdapterPositionInterface {

    private static final String TAG = "HomeTag";
    private NavController navController;
    private FragmentHomeBinding binding;
    private ProductsAdapter productsAdapter;
    private ArrayList<Product> productsList = new ArrayList<Product>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);


        setupRecyclerView();
        getProductsDataFromApi();


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
    }

    public String getToken() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(USER_TOKEN, MODE_PRIVATE);
        String token = prefs.getString(USER_TOKEN, "Token is null");
        return token;
    }

    private void setupRecyclerView() {
        productsAdapter = new ProductsAdapter(requireContext(), this, productsList);
        binding.rvHome.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.rvHome.setAdapter(productsAdapter);
    }

    private void getProductsDataFromApi() {
        RetrofitManger.getInstance().getProductsData(getToken()).enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: getProductsDataFromApi " + response.body().getProducts().toString());
                    productsList.clear();
                    productsList = (ArrayList<Product>) response.body().getProducts();
                    productsAdapter.setList(productsList);


                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i(TAG, "onFailure: " + t.getMessage());
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }


    @Override
    public void getProductsAdapterPosition(int position) {
        Toast.makeText(requireContext(), productsList.get(position).getName(), Toast.LENGTH_LONG).show();

        int productId = productsList.get(position).getId();
        HomeFragmentDirections.ActionHomeFragmentToItemDetailsFragment action = HomeFragmentDirections.actionHomeFragmentToItemDetailsFragment(productId);

        navController.navigate(action);


    }

    private void addProductToCart(int productId, String amount) {
        RetrofitManger.getInstance().addProductToCart(getToken(), productId, amount).enqueue(new Callback<AddToCart>() {
            @Override
            public void onResponse(Call<AddToCart> call, Response<AddToCart> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: addProductToCart " + response.body().getMessage());
                    Toast.makeText(requireContext(), "Product Added To Cart", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCart> call, Throwable t) {
                Log.i(TAG, "onFailure: addProductToCart" + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void addProductInCart(int position) {
        addProductToCart(productsList.get(position).getId(), "1");
    }
}
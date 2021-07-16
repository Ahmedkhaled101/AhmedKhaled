package com.example.ahmedkhaled.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.adapter.CategoriesDetailsAdapter;
import com.example.ahmedkhaled.adapter.ProductsAdapter;
import com.example.ahmedkhaled.api.RetrofitManger;
import com.example.ahmedkhaled.databinding.FragmentCategoriesDetailsBinding;
import com.example.ahmedkhaled.model.cart.AddToCart;
import com.example.ahmedkhaled.model.categories.CategoryId;
import com.example.ahmedkhaled.model.home.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ahmedkhaled.ui.LoginFragment.USER_TOKEN;


public class CategoriesDetailsFragment extends Fragment implements CategoriesDetailsAdapter.CategoriesDetailsAdapterPositionInterface {

    private static final String TAG = "CategoriesDetailsTag";
    private NavController navController;
    private FragmentCategoriesDetailsBinding binding;
    private CategoriesDetailsAdapter adapter;
    private ArrayList<Product> productsList = new ArrayList<Product>();
    private CategoriesDetailsFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCategoriesDetailsBinding.inflate(inflater);

        if (getArguments() != null) {
            args = CategoriesDetailsFragmentArgs.fromBundle(getArguments());
            getCategoriesData(args.getCategoryId());
            Log.i(TAG, "onCreateView: " + args.toBundle().toString());
        }
        setupRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void getCategoriesData(int categoryId) {
        RetrofitManger.getInstance().getCategoriesDetails(getToken(), categoryId).enqueue(new Callback<CategoryId>() {
            @Override
            public void onResponse(Call<CategoryId> call, Response<CategoryId> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().toString());
                    Log.i(TAG, "onResponse: " + response.body().getCategory().toString());
                    productsList.clear();
                    productsList = response.body().getProducts();
                    adapter.setList(productsList);

                }
            }

            @Override
            public void onFailure(Call<CategoryId> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public String getToken() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(USER_TOKEN, MODE_PRIVATE);
        String token = prefs.getString(USER_TOKEN, "Token is null");

        return token;
    }

    private void setupRecyclerView() {
        adapter = new CategoriesDetailsAdapter(requireContext(), this, productsList);
        binding.categoriesDetailsRv.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.categoriesDetailsRv.setAdapter(adapter);
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
    public void getCategoriesDetailsAdapterPosition(int position) {
        int productId = productsList.get(position).getId();
        CategoriesDetailsFragmentDirections.ActionCategoriesDetailsFragmentToItemDetailsFragment action = CategoriesDetailsFragmentDirections.actionCategoriesDetailsFragmentToItemDetailsFragment(productId);
        navController.navigate(action);
    }

    @Override
    public void addProductInCart(int position) {
        addProductToCart(productsList.get(position).getId(), "1");
    }
}
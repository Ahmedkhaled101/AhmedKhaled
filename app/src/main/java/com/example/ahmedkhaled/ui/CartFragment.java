package com.example.ahmedkhaled.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.adapter.CartAdapter;
import com.example.ahmedkhaled.adapter.ProductsAdapter;
import com.example.ahmedkhaled.api.RetrofitManger;
import com.example.ahmedkhaled.databinding.FragmentCartBinding;
import com.example.ahmedkhaled.model.cart.Product;
import com.example.ahmedkhaled.model.cart.ProductInCart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ahmedkhaled.ui.LoginFragment.USER_TOKEN;

public class CartFragment extends Fragment implements CartAdapter.CartInterface {

    private static final String TAG = "CartTag";
    private FragmentCartBinding binding;
    private NavController navController;
    private CartAdapter adapter;
    private ArrayList<Product> productInCarts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater);
        setupRecyclerView();
        getCartProducts();



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void getCartProducts(){
        RetrofitManger.getInstance().getProductInCart(getToken()).enqueue(new Callback<ProductInCart>() {
            @Override
            public void onResponse(Call<ProductInCart> call, Response<ProductInCart> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().getProducts().toString());
                    productInCarts = response.body().getProducts();
                    adapter.setList(productInCarts);
                }
            }

            @Override
            public void onFailure(Call<ProductInCart> call, Throwable t) {

            }
        });
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter(requireContext(), this, productInCarts);
        binding.cartRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        binding.cartRv.addItemDecoration(dividerItemDecoration);
        binding.cartRv.setAdapter(adapter);
    }

    public String getToken() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(USER_TOKEN, MODE_PRIVATE);
        String token = prefs.getString(USER_TOKEN, "Token is null");
        return token;
    }

    @Override
    public void getCartPosition(int position) {

    }
}
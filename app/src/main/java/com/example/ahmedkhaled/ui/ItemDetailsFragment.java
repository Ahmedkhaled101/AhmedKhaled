package com.example.ahmedkhaled.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.api.RetrofitManger;
import com.example.ahmedkhaled.databinding.FragmentItemDetailsBinding;
import com.example.ahmedkhaled.model.cart.AddToCart;
import com.example.ahmedkhaled.model.home.Product;
import com.example.ahmedkhaled.model.home.Products;
import com.example.ahmedkhaled.model.itemDetails.ProductDetails;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ahmedkhaled.ui.LoginFragment.USER_TOKEN;

public class ItemDetailsFragment extends Fragment {

    private FragmentItemDetailsBinding binding;
    private static final String TAG = "ItemDetailsTAG";
    private NavController navController;
    private ItemDetailsFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentItemDetailsBinding.inflate(inflater);

        binding.plusIconIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberInCounter = Integer.parseInt(binding.counterTv.getText().toString());
                numberInCounter += 1;
                binding.counterTv.setText(String.valueOf(numberInCounter));
            }
        });

        binding.minusIconIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numberInCounter = Integer.parseInt(binding.counterTv.getText().toString());
                if (numberInCounter < 2) {
                    return;
                }
                numberInCounter -= 1;
                binding.counterTv.setText(String.valueOf(numberInCounter));
            }
        });

        binding.itemAddToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToCart(args.getItemProductId(), binding.counterTv.getText().toString());
            }
        });


        if (getArguments() != null) {
            args = ItemDetailsFragmentArgs.fromBundle(getArguments());
            Log.i(TAG, "onCreateView: " + args.getItemProductId());
        }
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        getProductDetailsFromApi(args.getItemProductId());

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);


    }

    private void getProductDetailsFromApi(int productId) {
        RetrofitManger.getInstance().getProductDetails(getToken(), productId).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().toString());
                    Log.i(TAG, "onResponse: " + response.body().getProduct().toString());
                    Log.i(TAG, "onResponse: " + response.body().getProduct().getDescription());
                    Log.i(TAG, "onResponse: " + response.message());
                    Log.i(TAG, "onResponse: " + response.isSuccessful());

                    binding.itemTitleTv.setText(response.body().getProduct().getTitle());
                    binding.itemNameTv.setText(response.body().getProduct().getName());
                    binding.itemPriceTv.setText(response.body().getProduct().getPriceFinalText());
                    binding.itemDescriptionTv.setText(response.body().getProduct().getDescription());
                    Glide.with(requireActivity()).load(response.body().getProduct().getAvatar()).into(binding.itemAvatarIv);
                }

            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

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

    public String getToken() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(USER_TOKEN, MODE_PRIVATE);
        String token = prefs.getString(USER_TOKEN, "Token is null");
        return token;
    }
}
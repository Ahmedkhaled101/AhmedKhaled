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

import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.adapter.CategoriesAdapter;
import com.example.ahmedkhaled.adapter.ProductsAdapter;
import com.example.ahmedkhaled.api.RetrofitManger;
import com.example.ahmedkhaled.databinding.FragmentCategoriesBinding;
import com.example.ahmedkhaled.model.categories.Categories;
import com.example.ahmedkhaled.model.categories.Category;
import com.example.ahmedkhaled.model.categories.CategoryId;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ahmedkhaled.ui.LoginFragment.USER_TOKEN;

public class CategoriesFragment extends Fragment implements CategoriesAdapter.GetCategoryPositionInterface {

    private static final String TAG = "CategoriesTag";
    private FragmentCategoriesBinding binding;
    private NavController navController;
    private CategoriesAdapter categoriesAdapter;
    private ArrayList<Category> categoriesArrayList = new ArrayList<Category>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCategoriesBinding.inflate(inflater);
        setupRecyclerView();


        RetrofitManger.getInstance().getCategories(getToken()).enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body().toString());
                    categoriesArrayList.clear();
                    categoriesArrayList = response.body().getCategories();
                    categoriesAdapter.setList(categoriesArrayList);
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

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
        categoriesAdapter = new CategoriesAdapter(requireContext(), categoriesArrayList, this);
        binding.categoriesRv.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }

    @Override
    public void getCategoryPosition(int position) {
        int categoryId = categoriesArrayList.get(position).getId();

        CategoriesFragmentDirections.ActionCategoriesFragmentToCategoriesDetailsFragment action = CategoriesFragmentDirections.actionCategoriesFragmentToCategoriesDetailsFragment(categoryId);
        navController.navigate(action);
    }
}
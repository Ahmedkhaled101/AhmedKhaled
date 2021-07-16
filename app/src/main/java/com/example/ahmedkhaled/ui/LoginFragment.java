package com.example.ahmedkhaled.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ahmedkhaled.R;
import com.example.ahmedkhaled.api.RetrofitManger;
import com.example.ahmedkhaled.databinding.FragmentLoginBinding;
import com.example.ahmedkhaled.model.register.LoginResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {

    private static final String TAG = "LoginTest";
    public static final String USER_TOKEN = "User Token";
    private FragmentLoginBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmailLogin.getText().toString();
                String password = binding.etPasswordLogin.getText().toString();

                if (email.isEmpty()) {
                    binding.etEmailLogin.setError("Emile is required");
                    binding.etEmailLogin.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.etEmailLogin.setError("Enter a valid email");
                    binding.etEmailLogin.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    binding.etPasswordLogin.setError("Password is required");
                    binding.etPasswordLogin.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    binding.etPasswordLogin.setError("The password must be at least 6 characters.");
                    binding.etPasswordLogin.requestFocus();
                    return;
                }

                RetrofitManger.getInstance().login(email, password).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                            Toast.makeText(requireContext(), "Login is Done", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onResponse: " + response.body().getToken());
                            saveToken(response.body().getToken());
                            navController.navigate(R.id.action_loginFragment_to_homeFragment);
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(requireActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onFailure : " + t.getMessage());
                    }
                });
            }
        });

        binding.tvGoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!getToken().equals("Token is null")){
            Toast.makeText(requireContext(), "Login is Done", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_loginFragment_to_homeFragment);
        }
    }

    public String getToken() {
        SharedPreferences prefs = requireActivity().getSharedPreferences(USER_TOKEN, MODE_PRIVATE);
        String token = prefs.getString(USER_TOKEN, "Token is null");
        return token;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void saveToken(String token) {
        SharedPreferences.Editor editor = requireActivity().getSharedPreferences(USER_TOKEN, MODE_PRIVATE).edit();
        editor.putString(USER_TOKEN, "Bearer " +token);
        editor.apply();
    }
}
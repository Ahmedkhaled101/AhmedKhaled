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
import com.example.ahmedkhaled.databinding.FragmentRegisterBinding;
import com.example.ahmedkhaled.model.register.LoginResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class RegisterFragment extends Fragment {

    private static final String TAG = "LoginTest";
    public static final String USER_TOKEN = "User Token";
    private FragmentRegisterBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etNameRegister.getText().toString();
                String email = binding.etEmailRegister.getText().toString();
                String password = binding.etPasswordRegister.getText().toString();

                if (name.isEmpty()) {
                    binding.etNameRegister.setError("Name is required");
                    binding.etNameRegister.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    binding.etEmailRegister.setError("Emile is required");
                    binding.etEmailRegister.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.etEmailRegister.setError("Enter a valid email");
                    binding.etEmailRegister.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    binding.etPasswordRegister.setError("Password is required");
                    binding.etPasswordRegister.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    binding.etPasswordRegister.setError("The password must be at least 6 characters.");
                    binding.etPasswordRegister.requestFocus();
                    return;
                }
                RetrofitManger.getInstance().register(name, email, password).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(requireActivity(), response.message(), Toast.LENGTH_LONG).show();
                            Log.i(TAG, "onResponse: " + response.body().getToken());
                            saveToken(response.body().getToken());
                            navController.navigate(R.id.action_registerFragment_to_loginFragment);
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

        binding.tvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        return binding.getRoot();
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
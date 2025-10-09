package com.example.trabajo_practivo_inmobiliaria.login;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.trabajo_practivo_inmobiliaria.R;
import com.example.trabajo_practivo_inmobiliaria.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
private LoginViewModel mv;
private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=binding.etEmail.getText().toString();
                String pass=binding.etContrasenia.getText().toString();
                mv.login(user,pass);
            }
        });
    }
}
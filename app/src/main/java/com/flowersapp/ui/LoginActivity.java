package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;
import static com.flowersapp.utils.NavigationManager.goToForgotPassword;
import static com.flowersapp.utils.NavigationManager.goToUserRegister;
import static com.flowersapp.utils.Validator.isValidEmail;
import static com.flowersapp.utils.Validator.isValidPassword;

import android.os.Bundle;

import com.flowersapp.R;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.flowersapp.utils.AlertManager;
import com.flowersapp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin, btnRegister;
    private AlertManager alertManager;
    private EditText etEmail, etPassword;
    private TextView tvForgotPassword;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initViews();
        setupListener();
        setupObservers();

    }

    public void initViews() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister = findViewById(R.id.btnRegister);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        alertManager = new AlertManager();
    }

    private void setupObservers() {
        viewModel.getLoginResult().observe(this, user -> {
            if (user != null) {
                goToCatalog(this, user);
            } else {
                alertManager.mostrarAlerta("Usuario o contraseña incorrectos", this, false);
            }
        });
    }

    public void setupListener() {
        btnLogin.setOnClickListener(a -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (!isValidEmail(email) || !isValidPassword(password)) {
                alertManager.mostrarAlerta("Por favor, ingresa un correo válido y una contraseña (mín. 6 caracteres).", this, false);
            } else {
                viewModel.realizarLogin(email, password);
            }
        });

        btnRegister.setOnClickListener(a -> goToUserRegister(this));
        tvForgotPassword.setOnClickListener(a -> goToForgotPassword(this));
    }


}



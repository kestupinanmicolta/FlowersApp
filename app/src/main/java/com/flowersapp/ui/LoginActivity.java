package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;

import android.os.Bundle;

import com.flowersapp.R;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.flowersapp.databinding.ActivityLoginBinding;
import com.flowersapp.utils.AlertManager;
import com.flowersapp.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private AlertManager alertManager;
    private EditText etEmail, etPassword;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        initViews();
        setupListener();
        setupObservers();

    }
    public void initViews(){
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        alertManager = new AlertManager();
    }

    private void setupObservers() {
        viewModel.getLoginResult().observe(this, esExitoso -> {
            if (esExitoso != null) {
                if (esExitoso) {
                    goToCatalog(this);
                } else {
                    alertManager.mostrarAlerta("El usuario o contraseña son incorrectos", this);
                }
            }
        });
    }
    public void setupListener() {
        btnLogin.setOnClickListener(a -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                alertManager.mostrarAlerta("Por favor, completa todos los campos para continuar.", this);
            } else {
                // 3. Solo le pedimos al ViewModel que trabaje
                viewModel.realizarLogin(email, password);
            }
        });
    }


}



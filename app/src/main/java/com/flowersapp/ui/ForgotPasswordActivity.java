package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToLogin;
import static com.flowersapp.utils.Validator.isValidEmail;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.flowersapp.R;
import com.flowersapp.utils.AlertManager;
import com.flowersapp.viewmodel.ForgotPasswordViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private Button btnSendEmail;
    private AlertManager alertManager;
    private EditText etEmailForgot;
    private ForgotPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initViews();
        setupObserver();
        setupListener();
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnSendEmail = findViewById(R.id.btnSendEmail);
        etEmailForgot = findViewById(R.id.etEmailForgot);
        alertManager = new AlertManager();
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
    }

    public void setupObserver() {
        viewModel.getForgotResult().observe(this, existe -> {
            if (existe != null) {
                if (existe) {
                    String email = etEmailForgot.getText().toString().trim();
                    alertManager.mostrarAlerta("Se ha enviado un enlace de recuperación a: " + email, this, true);
                } else {
                    alertManager.mostrarAlerta("Este correo no se encuentra registrado en FlorAbby.", this, false);
                }
            }
        });
    }

    public void setupListener() {
        btnBack.setOnClickListener(a -> goToLogin(this));

        btnSendEmail.setOnClickListener(a -> {
            String email = etEmailForgot.getText().toString().trim();
            if (!isValidEmail(email)) {
                alertManager.mostrarAlerta("Por favor ingresa un correo electrónico válido", this, false);
            } else {
                viewModel.emailExist(email);
            }
        });
    }

}
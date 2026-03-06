package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;
import static com.flowersapp.utils.NavigationManager.goToLogin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.flowersapp.R;
import com.flowersapp.data.model.User;
import com.flowersapp.utils.AlertManager;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvProfileName, tvProfileEmail, tvProfilePhone,tvProfileAddress, tvProfileDeparment, tvProfileCity;
    private Button btnLogout;
    private ImageButton btnBack;
    private AlertManager alertManager;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        setupListener();
        loadData();
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        tvProfileName = findViewById(R.id.tvProfileName);
        tvProfileEmail = findViewById(R.id.tvProfileEmail);
        tvProfilePhone = findViewById(R.id.tvProfilePhone);
        tvProfileAddress = findViewById(R.id.tvProfileAddress);
        tvProfileCity = findViewById(R.id.tvProfileCity);
        tvProfileDeparment = findViewById(R.id.tvProfileDeparment);
        btnLogout = findViewById(R.id.btnLogout);
        alertManager = new AlertManager();
        user = (User) getIntent().getSerializableExtra("user");
    }

    public void setupListener() {
        btnBack.setOnClickListener(a -> goToCatalog(this, user));

        btnLogout.setOnClickListener(a -> {
            // Usamos el nuevo método con el Callback
            alertManager.mostrarConfirmacion("¿Realmente deseas cerrar sesión?", this, new AlertManager.AlertCallback() {
                @Override
                public void onConfirm() {
                    // Esto SOLO se ejecuta si el usuario presiona "SÍ"
                    goToLogin(ProfileActivity.this);
                    finish();
                }
            });
        });
    }

    public void loadData() {
        tvProfileName.setText(user.getName());
        tvProfileEmail.setText(user.getEmail());
        tvProfilePhone.setText(user.getPhoneNumber());
        tvProfileCity.setText(user.getCity().getName());
        tvProfileAddress.setText(user.getAdress());
        tvProfileDeparment.setText(user.getDeparment().getName());
    }
}
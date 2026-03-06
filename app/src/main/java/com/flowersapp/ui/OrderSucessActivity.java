package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.flowersapp.R;
import com.flowersapp.data.model.User;

public class OrderSucessActivity extends AppCompatActivity {

    private Button btnBackToCatalog;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        // 1. Inicializar vistas
        btnBackToCatalog = findViewById(R.id.btnBackToCatalog);

        // 2. Recuperar el usuario (que ahora tiene el carrito vacío)
        user = (User) getIntent().getSerializableExtra("user");

        // 3. Configurar el botón
        btnBackToCatalog.setOnClickListener(v -> {
            // Regresamos al catálogo pasando el usuario para mantener la sesión
            goToCatalog(this, user);

            // Finalizamos esta pantalla para que no quede en el historial de navegación
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Si el usuario presiona el botón físico de atrás, también lo mandamos al catálogo
        goToCatalog(this, user);
        finish();
    }
}
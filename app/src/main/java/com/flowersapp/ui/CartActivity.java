package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCatalog;
import static com.flowersapp.utils.NavigationManager.goToCheckout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flowersapp.R;
import com.flowersapp.adapters.CarAdapter;
import com.flowersapp.data.model.Cart;
import com.flowersapp.data.model.User;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private CarAdapter adapter;
    private TextView tvTotal;
    private RecyclerView rvCartItems;
    private User user;
    private Button btnContinue,btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        configurarRecyclerView();
        setupListener();
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        rvCartItems = findViewById(R.id.rvCartItems);
        tvTotal= findViewById(R.id.tvTotal);
        btnCheckout= findViewById(R.id.btnCheckout);
        btnContinue= findViewById(R.id.btnContinue);
        user = (User) getIntent().getSerializableExtra("user");
    }

    public void setupListener() {
        btnBack.setOnClickListener(a -> finish());
        btnContinue.setOnClickListener(a -> goToCatalog(this, user));
        btnCheckout.setOnClickListener(a -> {
            if (user != null) {
                // Quitamos el clear() de aquí
                goToCheckout(this, user);
                // Opcional: finish(); si no quieres que regrese al carrito
            }
        });
    }

    private void configurarRecyclerView() {

        double total = 0;
        for (Cart item : user.getCar()) {
            total += item.getPrice() * item.getCantidad();
        }

        DecimalFormat formatter = new DecimalFormat("$ #,###");
        tvTotal.setText("Total: " + formatter.format(total));

        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(user.getCar(), new CarAdapter.OnCartChangeListener() {
            @Override
            public void onUpdateTotal() {
                actualizarPrecioTotal(); // Este es el método que suma todo de nuevo
            }
        });
        rvCartItems.setAdapter(adapter);
    }
    private void actualizarPrecioTotal() {
        double total = 0;

        // Recorremos la lista actual del usuario (que ya fue modificada por el adapter)
        if (user != null && user.getCar() != null) {
            for (Cart item : user.getCar()) {
                total += item.getPrice() * item.getCantidad();
            }
        }

        // Aplicamos el formato de moneda
        DecimalFormat formatter = new DecimalFormat("$ #,###");
        tvTotal.setText("Total: " + formatter.format(total));

        // Opcional: Si el carrito queda vacío, podrías mostrar un mensaje o deshabilitar el botón de compra
        if (user.getCar().isEmpty()) {
            tvTotal.setText("Total: $ 0");
            btnCheckout.setEnabled(false);
            btnCheckout.setAlpha(0.5f); // Se ve grisáceo
        } else {
            btnCheckout.setEnabled(true);
            btnCheckout.setAlpha(1.0f);
        }
    }

}
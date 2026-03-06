package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCart;
import static com.flowersapp.utils.NavigationManager.goToCatalog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.flowersapp.R;
import com.flowersapp.data.model.Cart;
import com.flowersapp.data.model.Catalog;
import com.flowersapp.data.model.User;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView ivProductImage;
    private TextView tvProductNameHeader, tvProductPrice, tvProductDescription, tvQuantity;
    private Cart cart;
    private Button btnAddToCart, btnMinus, btnPlus;
    private Catalog catalog;
    private int cantidad = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();
        setupListener();
        loadData();
    }

    public void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        tvProductNameHeader = findViewById(R.id.tvProductNameHeader);
        ivProductImage = findViewById(R.id.ivProductImage);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        catalog = (Catalog) getIntent().getSerializableExtra("catalog");
        user = (User) getIntent().getSerializableExtra("user");
        tvQuantity.setText(String.valueOf(cantidad));
    }

    public void setupListener() {
        btnBack.setOnClickListener(a -> goToCatalog(this, user));

        btnAddToCart.setOnClickListener(a -> {
            if (user != null && catalog != null) {
                Cart nuevoItem = new Cart(catalog.getName(), catalog.getPrice(), cantidad, catalog.getImage());
                user.getCar().add(nuevoItem);
                goToCart(this, user);
            }
        });

        btnMinus.setOnClickListener(a -> {
            if (cantidad > 1) {
                cantidad--;
                tvQuantity.setText(String.valueOf(cantidad));
            }
        });

        btnPlus.setOnClickListener(a -> {
            cantidad++;
            tvQuantity.setText(String.valueOf(cantidad));
        });
    }

    public void loadData() {
        if (catalog != null) {
            tvProductNameHeader.setText(catalog.getName());
            ivProductImage.setImageResource(catalog.getImage());
            tvProductPrice.setText("$" + catalog.getPrice());
            tvProductDescription.setText(catalog.getDescription());
        }
    }
}

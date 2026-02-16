package com.flowersapp.ui;

import static com.flowersapp.utils.NavigationManager.goToCart;
import static com.flowersapp.utils.NavigationManager.goToCatalog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.flowersapp.R;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();
        setupListener();
    }
    public void initViews(){
        btnBack=findViewById(R.id.btnBack);
        btnAddToCart=findViewById(R.id.btnAddToCart);
    }
    public void setupListener(){
        btnBack.setOnClickListener(a->goToCatalog(this));
        btnAddToCart.setOnClickListener(a->goToCart(this));
    }
}
